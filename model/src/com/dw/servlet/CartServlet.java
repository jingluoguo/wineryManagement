package com.dw.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dw.domain.ShopCart;
import com.dw.domain.WineInfo;
import com.dw.service.ShopCartService;
import com.dw.service.WineInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShopCartService shopCartService = new ShopCartService();
	private WineInfoService wineInfoService = new WineInfoService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		doGet(request, response);
	}
	
	/**
	 * 按照条件查询商城物品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectBycondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		//System.out.println(category + maxPriceStr + minPriceStr);
		Double minPrice = null,maxPrice = null;
		if(minPriceStr != null && !minPriceStr.trim().equals("")) {
			minPrice = Double.parseDouble(minPriceStr);
		}
		if(maxPriceStr != null && !maxPriceStr.trim().equals("")) {
			maxPrice = Double.parseDouble(maxPriceStr);
		}
		List<WineInfo> list = wineInfoService.selectByCondition(category,minPrice,maxPrice);
		request.setAttribute("wineInfos", list);
		//System.out.println(list);
		request.getRequestDispatcher("/WEB-INF/page/content.jsp").forward(request, response);
	}
	
	
	/**
	 * 用于显示商品详情的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void wineDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wineIdStr = request.getParameter("wineId");
		Integer wineId = Integer.parseInt(wineIdStr);
		WineInfo wine = wineInfoService.getWine(wineId);
		
		//创建Json对象
		ObjectMapper mapper = new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		String result = mapper.writeValueAsString(wine);
		//System.out.println(result);
	    response.getWriter().print(result);
	    return;
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addShopCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Msg = "";
		String strId = request.getParameter("wineId");
		try {
			int wineId = Integer.parseInt(strId);
			if(wineId>0) {
				if(!shopCartService.checkWineInCart(wineId,(String)request.getSession().getAttribute("userID"))) {
					Msg = "false";
				}
				shopCartService.addShopCart((String)request.getSession().getAttribute("userID"), wineId);
			}
			HttpSession session = request.getSession();
			session.setAttribute("goodAmount", (Integer.parseInt(session.getAttribute("goodAmount").toString()))+1+"");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//创建JSON对象
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(Msg);
	    return;
	}
	
	/**
	 * 修改购物车中的商品数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void modifyAmount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("shopCartId");
		String amount = request.getParameter("wineAmount");
		//原来的数量
		String amount2 = request.getParameter("wineAmount2");
		int  id = Integer.parseInt(strId);
		int wineAmount = Integer.parseInt(amount);
		int wineAmount2 = Integer.parseInt(amount2);
		//修改数量时，可能会出现超出库存量的情况,超出库存返回false
		boolean b = shopCartService.modifyWineAmount(id,wineAmount,wineAmount2);
		String Msg = "true";
		if(!b) {
			Msg = "false";
		}
		//创建JSON对象
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
	    response.getWriter().print(Msg);
	    return;
	}
	
	/**
	 * 从购物车中移除酒品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteWine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("shopCartId");
		int id = Integer.parseInt(strId);
		String Msg = "";
		if(id > 0) {
			ShopCart cart = shopCartService.getWineAmount(id);
			shopCartService.deleteWine(id);
			shopCartService.updateWineAmount(cart.getAmount(),cart.getWineId());
			Msg = "true";
		}else {
			Msg = "false";
		}
		response.sendRedirect( request.getContextPath() + "/userServlet?method=myShopCart");
		return;
	}
	
	

}
