package com.dw.tools;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;


import com.dw.domain.Settlement;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFOut {
	
	
	public PDFOut(List<Settlement> list,
			OutputStream outputStream, String sheetName) {
		
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4); // Step 1—Create a Document.
		@SuppressWarnings("unused")
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, ba);
			document.open();
			document.setMargins(5, 5, 5, 5);
		    PdfPTable table = this.generatePDF(list);
		    document.add(table);//将表格加入到document中
	        document.close();
	        ba.writeTo(outputStream);
	        outputStream.flush();
			outputStream.close();
	        ba.close(); // 导出pdf注解    
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public PdfPTable generatePDF(List<Settlement> list) throws Exception{
	    PdfPTable table = PdfUtil.createTable(16);
	    if(list!=null) {
	    int size=list.size();
        if(size>0) {
        	table.addCell(PdfUtil.createHeadCell("山东五六酒业有限公司"));
            table.addCell(PdfUtil.createTitleCell("用户编号"));
            table.addCell(PdfUtil.createTitleCell("用户姓名"));
            table.addCell(PdfUtil.createTitleCell("身份证"));
            table.addCell(PdfUtil.createTitleCell("酒水消费"));
            table.addCell(PdfUtil.createTitleCell("A区业绩总消费"));
            table.addCell(PdfUtil.createTitleCell("B区业绩总消费"));
            table.addCell(PdfUtil.createTitleCell("直接销售奖励"));
            table.addCell(PdfUtil.createTitleCell("小区奖励"));
            table.addCell(PdfUtil.createTitleCell("奖励总额"));
            table.addCell(PdfUtil.createTitleCell("复投基金"));
            table.addCell(PdfUtil.createTitleCell("扣除5%平台费"));
            table.addCell(PdfUtil.createTitleCell("账户余额"));
            table.addCell(PdfUtil.createTitleCell("现金已提取"));
            table.addCell(PdfUtil.createTitleCell("注册日期"));
            table.addCell(PdfUtil.createTitleCell("注册资金"));
            table.addCell(PdfUtil.createTitleCell("本周结余"));
        	for (int i = 0; i < size; i++) {
                table.addCell(PdfUtil.createCell(list.get(i).getS_id()));
                table.addCell(PdfUtil.createCell(list.get(i).getS_name()));
                table.addCell(PdfUtil.createCell(list.get(i).getS_ID()));
                table.addCell(PdfUtil.createCell(list.get(i).getS_Winconsume()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_big()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_small()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_sale()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_achievement()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_balance()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_fund()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_tax()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_total()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_Deconsume()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_date()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_as_deposit()+""));
                table.addCell(PdfUtil.createCell(list.get(i).getS_cba()+""));

    		}
        }
	 }else {
	     table.addCell(PdfUtil.createHeadCell("没有数据！！！"));
	 }
     return table;
	}
}
