package com.dw.filter;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String encoding = filterConfig.getServletContext().getInitParameter("encoding");
		
		request.setCharacterEncoding(encoding);

		chain.doFilter(request, response);
	}

	private FilterConfig filterConfig =null;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
		System.out.println(fConfig);
	}
}
