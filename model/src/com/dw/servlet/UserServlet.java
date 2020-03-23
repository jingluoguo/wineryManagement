package com.dw.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dw.domain.Level;
import com.dw.domain.Page;
import com.dw.domain.Promotion;
import com.dw.domain.Settlement;
import com.dw.domain.ShopCart;
import com.dw.domain.User;
import com.dw.domain.WineInfo;
import com.dw.dao.CheckDao;
import com.dw.domain.Card;
import com.dw.domain.Check;
import com.dw.service.BonusService;
import com.dw.service.CheckService;
import com.dw.service.LevelService;
import com.dw.service.PromotionService;
import com.dw.service.SettlementService;
import com.dw.service.ShopCartService;
import com.dw.service.UserService;
import com.dw.service.WineInfoService;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	public UserServlet() {
		super();

	}
	private static final long serialVersionUID = 1L;
	private static String ChangedFID = "";


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 注册页面跳转
	 * @author jingluo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void zhuceye(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		request.setAttribute("userID", userId);
		request.getRequestDispatcher("/WEB-INF/page/register.jsp").forward(request, response);
		return;
	}
	/**
	 * 登出功能
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	    Enumeration em = request.getSession().getAttributeNames();
	    while(em.hasMoreElements()){
	        request.getSession().removeAttribute(em.nextElement().toString());
	    }
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");

		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			// 获取私有成员变量
			//System.out.println("method方法:" + method);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	UserService userService = new UserService();
	SettlementService settlementService = new SettlementService();
	PromotionService promotionService = new PromotionService();
	ShopCartService shopCartService = new ShopCartService();
	WineInfoService wineInfoService = new WineInfoService();
	LevelService levelService = new LevelService();
	BonusService bonusService = new BonusService();
	CheckService checkService = new CheckService();
	/**
	 * 检查手机号是否重复注册
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void checkPhone(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String phone = request.getParameter("phone");
		String Msg = "";
		if(!userService.checkPhone(phone)){
			Msg = "true";
		}else{
			Msg = "false";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(Msg);
	    return;
	}
	
	/**
	 * 检查是否为左右分支
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void checkNID(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userID = request.getParameter("userID");
		//System.out.println("****userID:" + userID);
		String msg = "";
		if(levelService.checkNID(userID)){
			msg = "true";
		}else{
			msg = "false";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
		
	}
	/**
	 *
	 * 改变分支
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 *
	protected void exchangeFID(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userID = request.getParameter("userId");
		String d = request.getParameter("d");
		String organID = request.getParameter("organID");
		//System.out.println("****userID:" + userID);
		String msg = "";
		if(levelService.exchangeFID(userID,d,organID)){
			msg = "true";
		}else{
			msg = "false";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
		
	}
	*/
	/**
	 * 检查是否存在该身份证号
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void checkID(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String ID = request.getParameter("ID");
		//System.out.println("test.......................");
		String msg = "";
		if(userService.checkID(ID)){
			msg = "false";
		}else{
			msg = "true";
		}
		//System.out.println(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
		
	}
	
	/**
	 * 注册功能
	 * @author jingluo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void zhuce(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	    SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");//设置日期格式
		
		// 获取用户输入的值
	    String userFID = (String) request.getSession().getAttribute("userID");
	    Integer nextAmount = levelService.getNextAmount(userFID);
		//System.out.println("这个父级目前有" + nextAmount + "个下级");
	    if(nextAmount == 0) {
	    	userFID = userFID + "&L";
	    }else if(nextAmount == 1) {
	    	userFID = userFID + "&R";
	    }else if(nextAmount >= 2){
	    	userFID = userFID + "&O";
	    }
		String userName = request.getParameter("regigterUsername");
		String userLevel = new String(request.getParameter("regigterLevel"));
		String userSex=new String(request.getParameter("regigterSex"));
		//String userSex = request.getParameter("regigterSex");
		String userAdress = new String(request.getParameter("regigterAdress"));
		String userPhone = request.getParameter("regigterPhone");
		String userID = request.getParameter("regigteruserID");
		String userPassword = request.getParameter("regigterPassword");
		if(userName != ""&&userSex != ""&&userPassword != ""){
			Level level = new Level(null, null, request.getParameter("regigterPhone"), userFID, null,null);
			levelService.insertRecord(level);
			User user = new User();
			user.setuName(userName);
			user.setuPhone(userPhone);
			user.setuPassword("123456");
			user.setuSex(userSex);
			user.setuID(userID);
			user.setuLevel(userLevel);
			user.setuAdress(userAdress);
			user.setuRegistTime(df.format(new Date()));
			//System.out.println(user);
			userService.insertUser(user,userFID);
			userCount(request,response);
			//request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		}
	}
	
	/**
	 * 跳转到主页
	 */
	protected void zhuye(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<WineInfo> wineInfos = wineInfoService.getWineInfoAll();
		request.setAttribute("wineInfos", wineInfos);
		request.getRequestDispatcher("/WEB-INF/page/content.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 验证用户登录
	 * @author jingluo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void denglu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Integer i = null;
		String userID = "";
		String userStatus = "";
		String userphone = request.getParameter("username");
		String userPassword = request.getParameter("password").trim();
		String selectWay = request.getParameter("selectWay");
		if("".equals(userphone.trim()) || "".equals(userPassword.trim())){
			request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		}
		if("手机号".equals(selectWay)){
			userID = (String) userService.selectUserID(userphone);
			List<User> userInfo = userService.getUserAll(userphone,userID);
			if(!userInfo.isEmpty()){
				userStatus = userInfo.get(0).getuStatus();
			}
			if("1".equals(userStatus)){
				User user = new User();
				user.setuPhone(userphone);
				user.setuID(userPassword);
				i = userService.login(user);
			}
		}
		if("身份证".equals(selectWay)){
			userID = userphone;
			List<User> userInfo = userService.getUserAll(userphone,userID);
			if(!userInfo.isEmpty()){
				userStatus = userInfo.get(0).getuStatus();
			}
			if("1".equals(userStatus)){
				User user = new User();
				user.setuID(userID);
				user.setuPassword(userPassword);
				userphone = userInfo.get(0).getuPhone();
				i = userService.logins(user);
			}
		}
		if (i != null) {
			if (null == String.valueOf(userphone) || "".equals(userPassword.trim())) {
				request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
				return;
			}
			List<WineInfo> wineInfos = wineInfoService.getWineInfoAll();
			request.setAttribute("wineInfos", wineInfos);
			int goodAmount = shopCartService.getGoodAmount(userID);		
			String level = (String) userService.selectUserInforLevel(userphone);
			request.getSession().setAttribute("level", level);
			request.getSession().setAttribute("goodAmount", goodAmount);
			request.getSession().setAttribute("userName", userService.selectUserInfor(userphone));
			// 登陆成功后将用户名保存到session中，在整个会话期间都可以访问到
			request.getSession().setAttribute("userID", userID);
			request.getSession().setAttribute("userphone", userphone);
			List<Settlement> list = settlementService.getSettleInfoAll("", "", "", userID, "");
			double deposit = 0;
			if(list.size()>0) {
				deposit = list.get(0).getS_deposit();
			}
			request.getSession().setAttribute("deposit", deposit);
			if("18315969275".equals(userphone)){
				String currPage = request.getParameter("currPage");
				//获取总数量
				int pageCount = userService.getUserCount("");
				int pagesize=10;

		        // 当前页变量
		        Integer iCurrPage = null;
		        if (currPage != null) {
		            iCurrPage = Integer.parseInt(currPage);
		        } else {
		            iCurrPage = 1;  // 没有指定显示哪页时,默认显示第1页
		        }
				Page page=new Page(pageCount, iCurrPage, pagesize);
				List<User> users = userService.getUserInfoAll("1",page);
				//System.out.println(users);
		        request.setAttribute("page", page);
				request.setAttribute("users", users);
				request.getRequestDispatcher("/WEB-INF/admin/massage.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/page/content.jsp").forward(request, response);
			}
			return;
		}
		request.setAttribute("error", "请检查账号是否正确，或者是否激活");
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		return;
	}
	/**
	 * 跳转到账户页面
	 * @author jingluo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void userCount(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String next1 = "";
		String next2 ="";
		// 得到该用户的账户id
		String userphone = (String) request.getSession().getAttribute("userphone");
		String userId = (String) request.getSession().getAttribute("userID");
		//获得下级数量
		Integer nextAmount = levelService.getNextAmount(userId);
		//获得上级的身份证号
		String preUserID = levelService.getPreUserID(userId);
		if("".equals(preUserID)){
			preUserID="无";
		}
		if(nextAmount != 0){
			//获取下级身份证号
			List<String> nextID = levelService.getNextIds(userId);
			if(!nextID.isEmpty()){
				request.setAttribute("nextIds", nextID);
				request.setAttribute("nName1", userService.selectUserInfors(nextID.get(0)));
			}
			else
				request.setAttribute("nextIds", "无");
			//获取下下级身份证
			if(nextID != null && nextID.size() >= 2){
				next1 = nextID.get(0);
				next2 = nextID.get(1);
			}
			//第一个下级用户的下级
			List<String> nnextID1 = levelService.getNextIds(next1);
			request.setAttribute("nnextID1", nnextID1);
			if(!nnextID1.isEmpty()){
				request.setAttribute("nnextID1", nnextID1);
			}
			else
				request.setAttribute("nnextID1", "无");
			//第二个下级用户的下级
			List<String> nnextID2 = levelService.getNextIds(next2);
			if(!nnextID2.isEmpty()){
				request.setAttribute("nnextID2", nnextID2);
			}
			else
				request.setAttribute("nnextID2", "无");
		}else
		{
			request.setAttribute("nextIds", "无");
			request.setAttribute("nnextID1", "无");
			request.setAttribute("nnextID2", "无");
		}
		// 查询该用户的账户余额
		List<Settlement> acounts = settlementService.acounts(userId);
		//System.out.println(userId + "........" + acounts);
//		// 将查询结果显示在前端页面
		request.getSession().setAttribute("userName", userService.selectUserInfor(userphone));
		request.setAttribute("userTime", userService.selectUserTime(userphone));
		//System.out.println(userService.selectUserTime(userphone));
		for(Settlement acount:acounts){
			request.setAttribute("acount", acount);
		}
		// 转发到我的账户页面
		request.setAttribute("preUserID", preUserID);
		request.setAttribute("nextAmount", nextAmount);
		request.getRequestDispatcher("/WEB-INF/page/userAcount.jsp").forward(request, response);
	}
	/**
	 * 跳转到金额明细
	 */
	
	protected void showAcount(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		List<Settlement> acounts = settlementService.acounts(userId);
		for(Settlement acount:acounts){
			request.setAttribute("acount", acount);
		}
		request.getRequestDispatcher("/WEB-INF/page/showAcount.jsp").forward(request, response);
		return;
	}
	/**
	 * 跳转到等级页面
	 */
	protected void showLevel(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		String userName = (String) userService.selectUserInfors(userId);
		request.setAttribute("username", userName);
		request.setAttribute("userID", userId);
		Integer lNumber = 0;
		Integer rNumber = 0;
		float lAccount = 0;
		float rAccount = 0;
		//获取一级下级
		List<String> nextID = levelService.getNextIds(userId);
		//System.out.println("test........" + nextID.get(0));
		if(nextID.size() > 0 && (nextID.get(0) != null)){
			//lNumber += 1;
			lNumber = selectChildAmount(nextID.get(0));
			lAccount = selectChildAccount(nextID.get(0));
			request.setAttribute("nextID1", nextID.get(0));
			request.setAttribute("nName1", userService.selectUserInfors(nextID.get(0)));
//			//二级左侧
			List<String> nnextIDl = levelService.getNextIds(nextID.get(0));
			if(nnextIDl != null && nnextIDl.size() != 0){
				//lNumber += nnextIDl.size();
				for(String lID:nnextIDl){
					List<String> lIDl = levelService.getNextIds(lID);
					if(lIDl.size() != 0 && lIDl != null){
						//lNumber += lIDl.size();
						/**
						 * 递归算法
						 */
					}
				}
				if(nnextIDl.size() > 0&&nnextIDl.get(0) != null){
					request.setAttribute("nnextID1", nnextIDl.get(0));
					request.setAttribute("nnName1", userService.selectUserInfors(nnextIDl.get(0)));
				}
				else{
					request.setAttribute("nnextID1", "无");
					request.setAttribute("nnName1", "无");
				}
				if(nnextIDl.size() > 1) {
					if(nnextIDl.get(1) != null){
						request.setAttribute("nnextID2", nnextIDl.get(1));
						request.setAttribute("nnName2", userService.selectUserInfors(nnextIDl.get(1)));
					}
					else{
						request.setAttribute("nnextID2", "无");
						request.setAttribute("nnName2", "无");
					}
				}
			}
			else{
				request.setAttribute("nnextID1", "无");
				request.setAttribute("nnName1", "无");
				request.setAttribute("nnextID2", "无");
				request.setAttribute("nnName2", "无");
			}
		}
		else{
			request.setAttribute("nextID1", "无");
			request.setAttribute("nName1", "无");
		}
		if(nextID.size() > 1 && nextID.get(1) != null){
			//rNumber += 1;
			rNumber = selectChildAmount(nextID.get(nextID.size()-1));
			rAccount = selectChildAccount(nextID.get(nextID.size()-1));
			request.setAttribute("nextID2", nextID.get(nextID.size()-1));
			request.setAttribute("nName2", userService.selectUserInfors(nextID.get(nextID.size()-1)));
			//获取二级下级右
			List<String> nnextIDr = levelService.getNextIds(nextID.get(nextID.size()-1));
			if(nnextIDr.size() != 0){
				//rNumber += nnextIDr.size();
				for(String lID:nnextIDr){
					List<String> lIDl = levelService.getNextIds(lID);
					if(lIDl.size() != 0 && lIDl != null){
						//rNumber += lIDl.size();
						
					}
				}
				if(nextID.get(0) != null){
					request.setAttribute("nnextID3", nnextIDr.get(0));
					request.setAttribute("nnName3", userService.selectUserInfors(nnextIDr.get(0)));
				}
				else{
					request.setAttribute("nnextID3", "无");
					request.setAttribute("nnName3", "无");
				}
				if(nnextIDr.size() > 1) {
					if(nextID.get(1) != null){
						request.setAttribute("nnextID4", nnextIDr.get(nnextIDr.size()-1));
						request.setAttribute("nnName4", userService.selectUserInfors(nnextIDr.get(nnextIDr.size()-1)));
					}
					else{
						request.setAttribute("nnextID4", "无");
						request.setAttribute("nnName4", "无");
					}
				}
			}
			else{
				request.setAttribute("nnextID3", "无");
				request.setAttribute("nnName3", "无");
				request.setAttribute("nnextID4", "无");
				request.setAttribute("nnName4", "无");
			}
		}
		else{
			request.setAttribute("nextID2", "无");
			request.setAttribute("nName2", "无");
		}
		List<User> nextIDAlls = levelService.getNextIdAll(userId);
		request.setAttribute("nextIDAlls", nextIDAlls);
		request.setAttribute("lNumber", lNumber);
		request.setAttribute("rNumber", rNumber);
		request.setAttribute("lAccount", lAccount);
		request.setAttribute("rAccount", rAccount);
		request.getRequestDispatcher("/WEB-INF/page/showLevel.jsp").forward(request, response);
		return;
	}
	//递归找到孩子数量
	public Integer selectChildAmount(String userId){
		Integer amount = f(userId);
		return amount;
	}
	public Integer f(String userId){
		if(userId == null || (userId.trim().equals(""))){
			return 0;
		}
		Integer l = f(levelService.getNextLeftId(userId));
		Integer r = f(levelService.getNextRightId(userId));
		return l+r+1;
	}
	//递归查询孩子等级
	public float selectChildAccount(String userId){
		float account = findAcount(userId);
		return account;
	}
	public float findAcount(String userId){
		if(userId == null || (userId.trim().equals(""))){
			return 0;
		}
		float account = 0;
		String level = levelService.getLevelById(userId);
		account = AccountLevel(level);
		float l = findAcount(levelService.getNextLeftId(userId));
		float r = findAcount(levelService.getNextRightId(userId));
		return l+r+account;
	}
	public float AccountLevel(String userLevel){
		float a=0;
		if(userLevel=="业务员"||"业务员".equals(userLevel)){
			a =2000;
		}
		if(userLevel=="代理商"||"代理商".equals(userLevel)){
			a =10000;
		}
		if(userLevel=="销售经理"||"销售经理".equals(userLevel)){
			a =30000;
		}
		if(userLevel=="销售总监"||"销售总监".equals(userLevel)){
			a =50000;
		}
		return a;
	}
	//递归找到下级
	public Integer checkALIDs(String userId,String ChangeID){
		ChangedFID = ChangeID;
		Integer amount = checkIDs(userId);
		return amount;
	}
	private static Integer i = 0;
	public Integer checkIDs(String userId){
		if(userId == null || (userId.trim().equals(""))){
			return 0;
		}
		checkIDs(levelService.getNextLeftId(userId));
		checkIDs(levelService.getNextRightId(userId));
		if(ChangedFID.equals(userId)||ChangedFID == userId){
			i=1;
		}
		return i;
	}
	/**
	 * 查询是否属于本人下级
	 */
	protected void checkALID(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		String ChangeID = request.getParameter("ChangeFID");
		String msg = "false";
		if(!"".equals(userId)&&!"".equals(ChangeID)){
			if(checkALIDs(userId,ChangeID)!=0){
				msg="true";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
	}
	/**
	 * 更改父级
	 */
	protected void updateFID(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String userID = request.getParameter("userID");
		String ChargefID = request.getParameter("ChangeFID");
		String ChargeFID = null;
		String msg = "false";
		Check check = new Check();
		check.setFID(ChargefID);
		Integer i = checkService.check(check);
		if(i==0&&(ChargefID!=null||!"".equals(ChargefID))){
			if("".equals(levelService.getNextLeftId(ChargefID))||levelService.getNextLeftId(ChargefID)==null){
				msg="true";
				ChargeFID = ChargefID + "&L";
			}
			else if("".equals(levelService.getNextRightId(ChargefID))||levelService.getNextRightId(ChargefID)==null){
				msg="true";
				ChargeFID = ChargefID + "&R";
			}
			if("true".equals(msg)){
				checkService.insertCheck(check);
				bonusService.updateRecord(userID, ChargefID);
				levelService.updateNID(ChargeFID, userID);
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
	}
	/**
	 * 实现晋级功能
	 * @author jingluo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void promotion(String userId){
	    SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");//设置日期格式
		// 得到该用户的账户id
		String level = "";
		// 查询该用户的账户余额
		List<Settlement> acounts = settlementService.acounts(userId);
		for(Settlement acount:acounts){
			boolean i =false;
			Promotion promotion = new Promotion();
			level = acount.getS_level();
			if("业务员".equals(acount.getS_level()) && acount.getS_balance() >= 40000 )
			{
				System.out.println("晋级成功,您已成功晋级代理商");
				level = "代理商";
				i = true;
			}
			else if("代理商".equals(acount.getS_level()) && acount.getS_balance() >= 120000.0)
			{
				System.out.println("晋级成功,您已成功晋级销售经理");
				level = "销售经理";
				i = true;
			}
			else if("销售经理".equals(acount.getS_level()) && acount.getS_balance() >= 200000.0)
			{
				System.out.println("晋级成功,您已成功晋级销售总监");
				level = "销售总监";
				i = true;
			}
			else if("销售总监".equals(acount.getS_level())){
				System.out.println("您已为最高级别");
			}
			else{
				//System.out.println(acount.getS_level()+"  "+acount.getS_total()+"  "+acount.getS_Winconsume()+" "+acount.getS_Deconsume());
				System.out.println("晋级条件不符合！！！");
			}
			if(i){
				promotion.setP_ID(userId);
				promotion.setP_level(level);
				promotion.setP_olevel(acount.getS_level());
				promotion.setP_date(df.format(new Date()));
				promotionService.update(promotion);
				settlementService.UpdateLevel(level, userId);
			}
		}
	}
	
	/**
	 * 实现更新身份证功能
	 * @author jingluo
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
//	protected void updateID(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		// 得到该用户的账户id
//		String userphone = (String) request.getSession().getAttribute("userphone");
//		String userID = request.getParameter("acountUserID");
//		String userLevel = request.getParameter("acountLevel");
//		System.out.println("级别" + userLevel);
//		if(userID==null){
//			System.out.println(request.getParameter("acountUserID"));
//		}
//		else{
//			boolean i = userService.updateID(userphone,userID,userLevel,userID);
//			if(i){
//				System.out.println(request.getParameter("acountUserID"));
//				request.getSession().setAttribute("userID", userID);
//				List<WineInfo> wineInfos = wineInfoService.getWineInfoAll();
//				request.setAttribute("wineInfos", wineInfos);
//				request.getRequestDispatcher("/WEB-INF/page/content.jsp").forward(request, response);
//			}
//		}
//	}
	/**
	 * 我的购物车跳转方法，跳转到购物车页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void myShopCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodAmount = shopCartService.getGoodAmount((String)request.getSession().getAttribute("userID"));
		request.getSession().setAttribute("goodAmount", goodAmount);
		List<ShopCart> list = shopCartService.getMyShopCart((String)request.getSession().getAttribute("userID"));
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/page/shopCart.jsp").forward(request, response);
	}
	/**
	 * 跳转到添加银行卡页面
	 */
	protected void addCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute("userID");
		Card card = null;
		if((card = userService.selectCard(userId)) != null){
			request.setAttribute("cardNumber", card.getCard_number());
			request.setAttribute("cardAddress", card.getCard_address());
		}
		request.getRequestDispatcher("WEB-INF/page/addCard.jsp").forward(request, response);
	}
	/**
	 * 更新用户密码
	 */
	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		String newPassword = request.getParameter("newPassword");
		//更新密码
		userService.updatePassword(userId,newPassword);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print("");
	    return;
	}
	/**
	 * 充值
	 */
	protected void Recharge(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		String userName = (String) request.getSession().getAttribute("userName");
		String Recharge = request.getParameter("Recharge");
		String Ordernumber = request.getParameter("Ordernumber");
		String msg = "false";
		if(!"".equals(Recharge)&&!"".equals(userId)&&!"".equals(userName)&&!"".equals(Ordernumber)){
			userService.Recharge(userId,Recharge,userName,"",Ordernumber);
			msg = "true";
		}
		else
			msg = "false";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(msg);
	    return;
	}
	/**
	 * 添加或更新银行卡号
	 */
	protected void updateCard(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String userId = (String) request.getSession().getAttribute("userID");
		String cardNumber = request.getParameter("cardNumber");
		String cardAddress = new String(request.getParameter("cardAddress"));
		Card card = new Card(userId, cardNumber, cardAddress);
		if(userService.selectCard(userId) == null){
			//没有记录，插入
			userService.insertCard(card);
		}else{
			//更新
			userService.updateCard(card);
		}
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardAddress", cardAddress);
		request.getRequestDispatcher("WEB-INF/page/addCard.jsp").forward(request, response);
}
	/**
	 * 提现
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void myCash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double useracount = 0.0;
		double limit = 0.0;
		double deconsume = 0.0;
		String userId = (String) request.getSession().getAttribute("userID");
		String userName = (String) request.getSession().getAttribute("userName");
		Card card = userService.selectCard(userId);
		String Msg = "";
		if(card != null){
		try {
			String level = (String) request.getSession().getAttribute("level");
			//判断提现限制
			switch(level){
			case "业务员":
				limit = 30000;
			case "代理商":
				limit = 70000;
			}
			
			List<Settlement> acounts = settlementService.acounts(userId);
			for(Settlement acount:acounts){
				useracount = acount.getS_total();
				deconsume = acount.getS_Deconsume();
			}
			double cash = Integer.valueOf(request.getParameter("userCash")) ;
			if(useracount > cash){
				userService.insertCash(card,cash,userName);
				settlementService.updatecash(userId, useracount,cash);
				Msg = "true";
			}
			else
				Msg = "false";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			Msg = "false";
		}
//		List<WineInfo> wineInfos = wineInfoService.getWineInfoAll();
//		request.setAttribute("wineInfos", wineInfos);		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(Msg);
	    return;
	}
}
