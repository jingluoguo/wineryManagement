package com.dw.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dw.domain.BonusRecord;
import com.dw.domain.Consume;
import com.dw.domain.Order;
import com.dw.domain.Settlement;
import com.dw.domain.WineInfo;
import com.dw.domain.bonus;
import com.dw.service.BonusService;
import com.dw.service.ConsumeService;
import com.dw.service.LevelService;
import com.dw.service.OrderService;
import com.dw.service.SettlementService;
import com.dw.service.ShopCartService;
import com.dw.service.UserService;
import com.dw.service.WineInfoService;


/**
 * Servlet implementation class ConsumeServlet
 */
@WebServlet("/consumeServlet")
public class ConsumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConsumeService consumeService = new ConsumeService();
    private WineInfoService wineInfoService = new WineInfoService();
    private ShopCartService shopCartService = new ShopCartService();
    private OrderService orderService = new OrderService();
    private BonusService bonusService = new BonusService();
    private LevelService levelService = new LevelService();
    private UserService userService = new UserService();
    private SettlementService settlementService = new SettlementService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsumeServlet() {
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
	 * 保存消费信息的处理方法，支付成功以后可以转到这里
	 * 小区分红
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */ 
	public void saveConsumeRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String msg = "";
		String cDate = "";
		String level = (String) request.getSession().getAttribute("level");
		double deposit = Double.parseDouble(request.getSession().getAttribute("deposit").toString());
		//System.out.println(level);
		/*
		if("业务员".equals(level)){
			AG = 0.1;
		}
		if("代理商".equals(level)){
			AG = 0.12;
		}
		if("区域代理商".equals(level)){
			AG = 0.14;
		}*/
		String shopCartIds = request.getParameter("shopCartIds");
		double total = Double.parseDouble(request.getParameter("total"));
		//System.out.println("total:" + total);
		String payway = request.getParameter("payway");
		double useracount = 0.0;
		String username = "";
		String userId = (String) request.getSession().getAttribute("userID");
		//获取上上级的ID
		String preUserID = levelService.getPreUserID(userId);
		Consume consume = new Consume();
		//获取地址、手机号
		String userAddress = (String) userService.selectUserAddress(userId);
		String userPhone = userService.selectUserPhone(userId);
		List<Settlement> acounts = settlementService.acounts(userId);
		for(Settlement acount:acounts){
			useracount = acount.getS_total();
			username = acount.getS_name();
		}
		if("平台支付".equals(payway) && useracount < total){
			msg = "false";
		}
		else{
			String[] strIds = shopCartIds.split("-");
			for(int i = 0;i<strIds.length;i++) {
				int cartId = Integer.parseInt(strIds[i]);
				//得到酒品的id
				int wineId = shopCartService.getWineIdByCartId(cartId);
				//得到酒水的信息
				WineInfo wineInfo = wineInfoService.getwineInfo(wineId);
				//得到酒品在购物车中的数量
				int wineAmount = shopCartService.getWineAmountByCartId(cartId);
				//获得系统时间
				cDate = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
				//保存消费记录
				if(wineInfo != null) {
					consume = new Consume(null, wineInfo.getWineName(), userId, wineInfo.getWinePrice()*wineAmount, 0, 0, cDate,payway);
					consumeService.saveConsumeRecord(consume,wineInfo,false,0);
				}
				//可以添加一个收货信息表，这里可以根据用户的身份证号查询出收货信息，加入到订单信息中
				Order order = new Order(null, wineInfo.getWinePrice()*wineAmount, wineAmount, cDate, userAddress, userPhone, "注意运输",false);
				//保存订单信息
				orderService.saveOrderRecord(order,wineId);
				//修改用户金额
				if(deposit==0.0){
					settlementService.updateacounts(userId,useracount,consumeService.count,level);
				}else
					settlementService.updateacounts(userId,useracount,consumeService.count-deposit,level);
				//清除购物车中的数据
				shopCartService.deleteWine(cartId);
				if(!(deposit==0.0)){
					consumeService.saveConsumeRecord(consume,wineInfo,true,deposit);
				}
				msg = "true";
			}
			if(deposit!=0.0){
				bonus bon = new bonus(null,userId, preUserID, cDate.substring(0,4), cDate.substring(5, 7), deposit);
				bonusService.insertbonus(bon);
				BonusRecord bonusRecord = new BonusRecord(null, username, userId, preUserID, cDate, deposit);
				bonusService.insertBonusRecord(bonusRecord);
				request.getSession().setAttribute("deposit", 0.0);
			}
			request.getSession().setAttribute("deposit", 0.0);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
	}
}
