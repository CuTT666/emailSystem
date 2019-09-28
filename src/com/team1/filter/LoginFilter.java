package com.team1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	String a = "utf-8";
	String b = "text/html;charset=utf-8";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		String requstURL = request2.getRequestURI();
		request2.setCharacterEncoding(a);
		response.setContentType(b);
		
		if(requstURL.equals("/Team01/login.do") || requstURL.equals("/Team01/loginServlet")) {
			chain.doFilter(request2, response);
		}else {
			if(request2.getSession().getAttribute("ACCOUNT")!=null) {
				chain.doFilter(request2, response);
			}else {
				HttpServletResponse response2 = (HttpServletResponse) response;
				response2.sendRedirect("login.do");
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
