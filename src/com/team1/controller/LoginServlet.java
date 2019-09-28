package com.team1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.service.UserService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		PrintWriter printWriter = resp.getWriter();
		PrintWriter printWriter2 = resp.getWriter();
		String op = req.getParameter("op");
		if (op.equals("checkLogin")) {
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			if (account == "" || password == "") {
				printWriter.write("NULL");
			}

			String info = new UserService().chechLogin(account, password, req, resp);

			printWriter.write(info);

			printWriter.close();
		} else if (op.equals("searchCookie")) {
			// userJson是一个对象JSON字符串
			String userJson = new UserService().searchCookie(req);

			printWriter.write(userJson);
		} else if (op.equals("index")) {

			req.getRequestDispatcher("index.jsp").forward(req, resp); // 跳转到主页
		} else if (op.equals("registAccount")) {
			req.getRequestDispatcher("registAccount.jsp").forward(req, resp);
			
		}else if (op.equals("regist1")) {
			String username = req.getParameter("username");
			String account1 = req.getParameter("account");
			String password1 = req.getParameter("password");
			String password2 = req.getParameter("password2");
			if(username == "" || account1 =="" || password1 =="" || password2 =="" ) {
				printWriter2.write("NULL");
			}
			String info = new UserService().registAccount(req);
			printWriter2.write(info);
			printWriter2.close();
		} else if(op.equals("loginDo")) {
			req.getRequestDispatcher("login.do").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
