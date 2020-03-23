package com.dw.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dw.domain.BonusRecord;
import com.dw.domain.Cash;
import com.dw.domain.ConsumeExample;
import com.dw.domain.Order;
import com.dw.domain.Page;
import com.dw.domain.PageBean;
import com.dw.domain.Recharge;
import com.dw.domain.Settlement;
import com.dw.domain.User;
import com.dw.service.BonusService;
import com.dw.service.ConsumeService;
import com.dw.service.OrderService;
import com.dw.service.SettlementService;
import com.dw.service.UserService;
import com.dw.tools.ExportExcelUtil;
import com.dw.tools.PDFOut;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	private ConsumeService consumeService = new ConsumeService();
	private SettlementService settlement = new SettlementService();
	private OrderService orderService = new OrderService();
    private BonusService bonusService = new BonusService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * 用户信息管理
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void userMassage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<User> users = null;
		String currPage = request.getParameter("currPage");
		//获取总数量
		int pageCount = userService.getUserCount("");
		int pagesize=20;

        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount != 0){
			users = userService.getUserInfoAll("",page);
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/admin/massage.jsp").forward(request, response);
		return;
	}
	/**
	 * 新用户管理
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void bonusMassage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<BonusRecord> bonusRecords = null;
		String currPage = request.getParameter("currPage");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		System.out.println(startTime + " " + endTime);
		if(startTime == null){
			startTime = "";
		}
		if(endTime == null){
			endTime = "";
		}
		System.out.println(startTime + " " + endTime);
		//获取总数量
		int pageCount = bonusService.selectRecords(startTime, endTime);
		System.out.println(pageCount);
		int pagesize=20;
        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount != 0){
			bonusRecords = bonusService.selectBonusRecords(startTime, endTime,page);
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("bonusRecords", bonusRecords);
		request.getRequestDispatcher("/WEB-INF/admin/showBonus.jsp").forward(request, response);
		return;
	}
	/**
	 * 查询用户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void userSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userNo是主键，用户编号，区分userID身份证号
		String userNo = request.getParameter("userId");
		String phone = request.getParameter("regigterPhone");
		String userName = request.getParameter("regigterUsername");
		String userID = request.getParameter("regigteruserID");
		String date = request.getParameter("date");
		List<User> users = null;
		String currPage = request.getParameter("currPage");
		//获取总数量
		int pageCount = userService.SerachUserCount(userNo, phone, userName, userID, date);
		int pagesize=20;

        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount != 0){
			users = userService.SerachUserByCondition(userNo, phone, userName, userID, date,page);
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/admin/massage.jsp").forward(request, response);
	}
	/**
	 * 查询消费信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void consumeSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sql = new StringBuilder("SELECT u.u_name,u.u_ID,u.u_level,c.c_win,c.c_price,c.c_date,c.c_agio FROM consume c LEFT JOIN user u ON u.u_ID=c.c_ID where 1=1");
		String uID = (String) request.getSession().getAttribute("userID");
		//userNo是主键，用户编号，区分userID身份证号
		String userNo = request.getParameter("userId");
		String phone = request.getParameter("regigterPhone");
		String userName = request.getParameter("regigterUsername");
		String userID = request.getParameter("regigteruserID");
		String date = request.getParameter("date");
		List<ConsumeExample> list = null;
		String currPage = request.getParameter("currPage");
		int pagesize=20;

        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		if(!"371522199904018483".equals(uID)){
			userID = uID;
		}
		if(userNo!=null && !userNo.trim().equals("")) {
			sql.append(" and u.id like '%" + userNo +"%'");
			
		}
		if(phone!=null && !phone.trim().equals("")) {
			sql.append(" and u.u_phone like '%" + phone +"%'");
		}
		if(userName!=null && !userName.trim().equals("")) {
			sql.append(" and u.u_name like '%" + userName +"%'");
		}
		if(userID!=null && !userID.trim().equals("")) {
			sql.append(" and u.u_ID like '%" + userID +"%'");
		}
		if(date!=null && !date.trim().equals("")) {
			sql.append(" and c.c_date like '%" + date + "%'");
		}
		//获取总数量
		int pageCount = consumeService.ConsumeCount(sql);;
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount != 0){
			sql.append(" limit "+page.getStart() +","+page.getSize());
			list = consumeService.selectConsume(sql);
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		if(!"371522199904018483".equals(uID)){
			request.getRequestDispatcher("/WEB-INF/page/showWinerecord.jsp").forward(request, response);
		}else
			request.getRequestDispatcher("/WEB-INF/admin/WineConsume.jsp").forward(request, response);
	}
	/**
	 * 跳转settlement页面
	 */
	protected void showSettlement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		String currPage = request.getParameter("currPage");
		//获取总数量
		int pageCount = settlement.getsettlementCount();
		int pagesize=20;

        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }

		Page page=new Page(pageCount, iCurrPage, pagesize);
        List<Settlement> settlementss = settlement.getSettleInfoAll("", "", "", "", "",page);
		List<Settlement> settlements = settlement.getSettleInfoAll("", "", "", "", "");
		float balance = 0;
		float deposit = 0;
		for(int i=0;i<settlements.size();i++){
			balance += settlements.get(i).getS_balance();
			deposit += Float.parseFloat(settlements.get(i).getS_as_deposit());
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("balance", balance);
		request.setAttribute("deposit", deposit);
		request.setAttribute("settlements", settlementss);
		request.getRequestDispatcher("/WEB-INF/admin/showSettlement.jsp").forward(request, response);
		return;
	}
	/**
	 * 查询settlement信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void settlementSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userNo是主键，用户编号，区分userID身份证号
		String userNo = request.getParameter("userId");
		String phone = request.getParameter("regigterPhone");
		String userID = request.getParameter("regigteruserID");
		String date = request.getParameter("date");
		String currPage = request.getParameter("currPage");
		List<Settlement> settlements = null;
		List<Settlement> settlementss = null;
		int pagesize=20;
        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		float sales = 0;
		float deposit = 0;
		//获取总数量
		int pageCount = settlement.getSettleInfoAll(userNo, phone, "", userID, date).size();
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount != 0){
			settlementss = settlement.getSettleInfoAll(userNo, phone, "", userID, date,page);
			settlements = settlement.getSettleInfoAll(userNo, phone, "", userID, date);
			for(int i=0;i<settlements.size();i++){
				sales += settlements.get(i).getS_sale();
				deposit += Float.parseFloat(settlements.get(i).getS_as_deposit());
			}
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("userNo", userNo);
		request.setAttribute("phone", phone);
		request.setAttribute("userID", userID);
		request.setAttribute("date", date);
		request.setAttribute("page", page);
		request.setAttribute("settlements", settlementss);
		request.setAttribute("sales", sales);
		request.setAttribute("deposit", deposit);
		request.getRequestDispatcher("/WEB-INF/admin/showSettlement.jsp").forward(request, response);
	}
	/**
	 * 更新用户状态
	 */
	protected void updateStatu(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		// 得到该用户的账户id
		String userID = request.getParameter("uID");
		String userStatus = request.getParameter("uStatus");
		if("0".equals(userStatus)){
			userService.updateStatus(userID,"1");
		}
		if("1".equals(userStatus)){
			userService.updateStatus(userID,"0");
		}
		//List<User> users = userService.getUserInfoAll("1");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print("");
	    return;
	}
	/**
	 * 更新提现处理状态
	 */
	protected void dealCashStatus(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		// 得到该用户的账户id
		String id = request.getParameter("id");
		String cashID = request.getParameter("cID");
		String cashStatus = request.getParameter("cStatus");
		if("0".equals(cashStatus)){
			
			userService.updateCashStatus(id,cashID,"1");
		}
		//List<User> users = userService.getUserInfoAll("1");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print("");
	    return;
	}
	/**
	 * 跳转到提现处理界面
	 */
	protected void showCashback(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Cash> cash = userService.getAllCash("");
		String currPage = request.getParameter("currPage");
		List<Cash> cashs = null;
		//获取总数量
		int pageCount = cash.size();
		int pagesize=20;

        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount!=0){
			cashs = userService.selectCash("","","","",page);
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("cashs", cashs);
		request.getRequestDispatcher("/WEB-INF/admin/showCard.jsp").forward(request, response);
		return;
	}
	/**
	 * 提现记录查询
	 */
	protected void cashSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userNo是主键，用户编号，区分userID身份证号
		String uID = (String) request.getSession().getAttribute("userID");
		String phone = request.getParameter("phone");
		String userID = request.getParameter("uID");
		String card = request.getParameter("card");
		String date = request.getParameter("date");
		String currPage = request.getParameter("currPage");
		List<Cash> cashs = null;
		if(!"371522199904018483".equals(uID)){
			userID = uID;
		}
		//获取总数量
		int pageCount = userService.selectCash(phone,userID,card);
		int pagesize=20;

        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
		Page page=new Page(pageCount, iCurrPage, pagesize);
		if(pageCount!=0){
			cashs = userService.selectCash(phone,userID,card,date,page);
		}
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
		request.setAttribute("page", page);
		request.setAttribute("cashs", cashs);
		if(!"371522199904018483".equals(uID)){
			request.getRequestDispatcher("/WEB-INF/page/showCard.jsp").forward(request, response);
		}else
			request.getRequestDispatcher("/WEB-INF/admin/showCard.jsp").forward(request, response);
	}
	/**
	 * 导出Order的Excel
	 * @param request
	 * @param response
	 */
	protected void exportCashExcel(HttpServletRequest request,HttpServletResponse response) {
		String phone = request.getParameter("phone");
		String userID = request.getParameter("uID");
		String card = request.getParameter("card");
		String date = request.getParameter("date");
		List<Cash> cashs = userService.selectCash(phone,userID,card,date);
		ExportExcelUtil ex = new ExportExcelUtil();
	    String title = "山东五六酒业提现信息记录"; 
	    String[] headers = { "提现编号","姓名","身份证号","银行卡号","开户行地址","实际提现金额","日期","状态"};
	    List<String[]> dataset = new ArrayList<String[]>(); 
	    for(int i=0;i<cashs.size();i++) {
	    Cash cash = cashs.get(i); 
	    	dataset.add(new String[]{cash.getId()+"",cash.getC_name(),cash.getC_ID(),cash.getC_card(),cash.getC_address(),cash.getC_total()+"",cash.getC_date(),cash.getC_status()});
	    }
	    OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Employee.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
		try{
			if(out!=null){ 
				out.close(); 
			}
		}catch(IOException e){ 
			e.printStackTrace(); 
		}
	}
	}
	/**
	 * 跳转到order界面
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void showOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String currPage = request.getParameter("currPage");
		List<Order> orders = null;
		//获取总数量
		int pageCount = orderService.getOrderCount("","","");
		int pagesize=20;
        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
        Page page=new Page(pageCount, iCurrPage, pagesize);
        if(pageCount != 0){
        	orders = orderService.getOrderInfo("","","",page);
        }
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
        request.setAttribute("page", page);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/WEB-INF/admin/showOrder.jsp").forward(request, response);
		return;
	}
	/**
	 * 更新订单状态
	 */
	protected void updateOrderStatu(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		// 得到该用户的账户id
		String wineID = request.getParameter("wID");
		String wStatus = request.getParameter("wStatus");
		if("false".equals(wStatus)){
			orderService.updateStatus(wineID,"1");
		}
		//List<User> users = userService.getUserInfoAll("1");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print("");
	    return;
	}
	/**
	 * 查询订单信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void wineSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userNo是主键，用户编号，区分userID身份证号
		String wineId = request.getParameter("wineId");
		String phone = request.getParameter("Phone");
		String date = request.getParameter("Date");
		List<Order> orders = null;
		String currPage = request.getParameter("currPage");
		//获取总数量
		int pageCount = orderService.getOrderCount(wineId,phone,date);
		int pagesize=20;
        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
        Page page=new Page(pageCount, iCurrPage, pagesize);
        if(pageCount != 0){
        	orders = orderService.getOrderInfo(wineId,phone,date,page);
        }
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
        request.setAttribute("page", page);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/WEB-INF/admin/showOrder.jsp").forward(request, response);
	}
	/**
	 * 导出Order的Excel
	 * @param request
	 * @param response
	 */
	protected void exportOrderExcel(HttpServletRequest request,HttpServletResponse response) {
		String wineId = request.getParameter("wineId");
		String phone = request.getParameter("Phone");
		String date = request.getParameter("Date");
		List<Order> orders = orderService.getOrderInfo(wineId,phone,date);
		ExportExcelUtil ex = new ExportExcelUtil();
	    String title = "山东五六酒业订单信息记录"; 
	    String[] headers = { "订单号","姓名","手机号","酒水名称","酒水单价","酒水数量","总消费","收货地址","订单日期","备注","状态"};
	    List<String[]> dataset = new ArrayList<String[]>(); 
	    for(int i=0;i<orders.size();i++) {
	    Order order = orders.get(i); 
	    dataset.add(new String[]{order.getOrderId()+"",order.getOrderName(),order.getOrderPhone(),order.getOrderWineName(),order.getOrderSimple()+"",order.getOrderNumber()+"",order.getOrderPrice()+"",order.getOrderAddress(),order.getOrderDate(),order.getOrderStatus(),order.getOrderCope()+""});
	    }
	    OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Employee.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
		try{
			if(out!=null){ 
				out.close(); 
			}
		}catch(IOException e){ 
			e.printStackTrace(); 
		}
	}
	}
	/**
	 * 导出Settlement的Excel
	 * @param request
	 * @param response
	 */
	protected void exportExcel(HttpServletRequest request,HttpServletResponse response) {
		String userNo = request.getParameter("userNo");
		String phone = request.getParameter("phone");
		String userID = request.getParameter("userID");
		String date = request.getParameter("date");
		List<Settlement> settlements = settlement.getSettleInfoAll(userNo, phone, "", userID, date);
		  ExportExcelUtil ex = new ExportExcelUtil();
	      String title = "山东五六酒业信息记录"; 
	      String[] headers = { "用户编号","用户姓名","身份证","酒水消费","A区业绩总消费","B区业绩总消费","直接销售奖励","小区奖励","奖励总额","复投基金","扣除5%平台费","账户余额","现金已提取","注册日期","注册资金","本周结余"};
	      List<String[]> dataset = new ArrayList<String[]>(); 
	      for(int i=0;i<settlements.size();i++) {
	    	  Settlement settlement = settlements.get(i); 
	        	dataset.add(new String[]{settlement.getS_id(),settlement.getS_name(),settlement.getS_ID()
	        			,settlement.getS_Winconsume()+"",settlement.getS_big()+"",settlement.getS_small()+"",settlement.getS_sale()+"",settlement.getS_achievement()+"",settlement.getS_balance()+"",settlement.getS_fund()+"",settlement.getS_tax()+"",settlement.getS_total()+"",settlement.getS_Deconsume()+"",settlement.getS_date()+"",settlement.getS_as_deposit()+"",settlement.getS_cba()+""});
	        }
	      OutputStream out = null;//创建一个输出流对象 
			try { 
				out = response.getOutputStream();//
				response.setHeader("Content-disposition","attachment; filename="+"Employee.xls");//filename是下载的xls的名，建议最好用英文 
				response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
				response.setHeader("Pragma","No-cache");//设置头 
				response.setHeader("Cache-Control","no-cache");//设置头 
				response.setDateHeader("Expires", 0);//设置日期头  
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				ex.exportExcel(rootPath,title,headers, dataset, out);
				out.flush();
			} catch (IOException e) { 
				e.printStackTrace(); 
			}finally{
				try{
					if(out!=null){ 
						out.close(); 
					}
				}catch(IOException e){ 
					e.printStackTrace(); 
				}
			}
	}
	/**
	 * 导出PDF
	 * @param request
	 * @param response
	 * @param outputStream
	 */
	public void outPdf(HttpServletRequest request, HttpServletResponse response) {
		String userNo = request.getParameter("userNo");
		String phone = request.getParameter("phone");
		String userID = request.getParameter("userID");
		String date = request.getParameter("date");
		List<Settlement> settlements = settlement.getSettleInfoAll(userNo, phone, "", userID, date);
		response.setContentType("application/pdf;charset=ISO8859_1");  
		String sheetName ="";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		sheetName=df.format(new Date());// new Date()为获取当前系统时间
    	response.setHeader("Content-disposition", "attachment;filename="+sheetName+".pdf");
    	OutputStream outputStream=null;
    	try {
			outputStream = response.getOutputStream();
			new PDFOut(settlements, outputStream, sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到充值处理界面
	 */
	protected void showRecharge(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Recharge> recharges = null;
		String currPage = request.getParameter("currPage");
		//获取总数量
		int pageCount = userService.selectRechargeCount("","");
		int pagesize=20;
        // 当前页变量
        Integer iCurrPage = null;
        if (currPage != null) {
            iCurrPage = Integer.parseInt(currPage);
        } else {
            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
        }
        Page page=new Page(pageCount, iCurrPage, pagesize);
        if(pageCount != 0){
        	recharges = userService.selectRecharge("","",page);
        }
		request.setAttribute("iCurrPage", "当前页为第：" + iCurrPage + "页");
        request.setAttribute("page", page);
		request.setAttribute("recharges", recharges);
		request.getRequestDispatcher("/WEB-INF/admin/showRecharge.jsp").forward(request, response);
		return;
	}
	/**
	 * 充值处理
	 */
	protected void dealRecharge(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = request.getParameter("id");
		String sID = request.getParameter("sID");
		String account = request.getParameter("account");
		String sStatus = request.getParameter("sStatus");
		userService.Recharge(sID,account,"",id,"");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print("");
	    return;
	}
}
