package com.dw.admin.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dw.domain.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServletAdmin")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
	
//	AdminUserService userService = new AdminUserService();
//	//跳转到管理用户表
//	protected void userMassage(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		List<User> users = userService.getUserInfoAll("1");
//		//System.out.println(users);
//		request.setAttribute("users", users);
//		request.getRequestDispatcher("/WEB-INF/admin/massage.jsp").forward(request, response);
//		return;
//	}
}
