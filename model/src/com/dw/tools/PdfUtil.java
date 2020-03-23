package com.dw.tools;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by SuperChen on 2016/12/2.
 */
public class PdfUtil {

    private static Font headfont ;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小

    static{
        BaseFont bfChinese;
        try {
        	bfChinese = BaseFont.createFont("Font/SIMSUN.TTC,1", 
	        		BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 14, Font.BOLD,BaseColor.BLACK);// 设置字体大小
            keyfont = new Font(bfChinese, 10, Font.NORMAL,BaseColor.BLACK);// 设置字体大小
            textfont = new Font(bfChinese, 8, Font.NORMAL,BaseColor.BLACK);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //表格标题
    public static PdfPCell createHeadCell(String value){
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(15);
       // cell.setHorizontalAlignment(15);
        cell.setColspan(15);
        cell.setPhrase(new Phrase(value,headfont));
        cell.setPadding(10.0f);
        cell.setBorder(0);;
        return cell;
    }

    //表格表头
    public static  PdfPCell createTitleCell(String value){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8.0f);
        cell.setPhrase(new Phrase(value, keyfont));
        return cell;
    }

    //表格内容
    public static PdfPCell createCell(String value){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(6.0f);
        cell.setPhrase(new Phrase(value,textfont));
        return cell;
    }


    //生成表格
    public static PdfPTable createTable(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            table.setLockedWidth(true);
            table.setTotalWidth(550);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }

    public static  void addImage(String input,String output) throws Exception{
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(output)));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, out);
        addWatermark(stamper,"我是测试水印文字");
        int total = reader.getNumberOfPages();
        try {
            Image image = Image.getInstance("d:/1.png");
            image.setAbsolutePosition(350, 100); // set the first background image of the absolute
            image.scaleToFit(120, 120);
            PdfContentByte content= stamper.getOverContent(total);// 在内容上方加水印
            content.addImage(image);
        }catch (Exception e){
            System.out.println("123");
        }

        stamper.close();
        reader.close();
    }

    public static void addWatermark(PdfStamper pdfStamper, String waterMarkName) throws Exception {
        PdfContentByte content = null;
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Rectangle pageRect = null;
        PdfGState gs = new PdfGState();
        try {
            if (base == null || pdfStamper == null) {
                return;
            }
            // 设置透明度为0.4
            gs.setFillOpacity(0.4f);
            gs.setStrokeOpacity(0.4f);
            int toPage = pdfStamper.getReader().getNumberOfPages();
            for (int i = 1; i <= toPage; i++) {
                pageRect = pdfStamper.getReader().getPageSizeWithRotation(i);
                // 计算水印X,Y坐标
                float x = pageRect.getWidth() / 2;
                float y = pageRect.getHeight() / 2;
                // 获得PDF最顶层
                content = pdfStamper.getOverContent(i);
                content.saveState();
                // set Transparency
                content.setGState(gs);
                content.beginText();
                content.setColorFill(BaseColor.GRAY);
                content.setFontAndSize(base, 30);
                // 水印文字成45度角倾斜
                content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x,
                        y, 45);
                content.endText();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
