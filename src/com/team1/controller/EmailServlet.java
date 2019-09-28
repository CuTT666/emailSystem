package com.team1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.model.Email;
import com.team1.service.EmailService;

import net.sf.json.JSONArray;


@WebServlet("/emailServlet")
public class EmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = resp.getWriter();
		HttpSession session = req.getSession();     //用session拿到保存的用户账号
		String account = (String) session.getAttribute("ACCOUNT");
		String op = req.getParameter("op");
		if(op.equals("showReceiveEmail")) {      //收信箱展示
			String rw = req.getParameter("rw");
			if(rw!=null){
				if(rw.equals("ajax")){
					List<Email> emails = new EmailService().getEmailList(req);
					JSONArray jsonArray = JSONArray.fromObject(emails);
					String userJSON = jsonArray.toString();
					printWriter.write(userJSON);
				}
			}else {
				List<Email> emails = new EmailService().getEmailList(req);
				req.setAttribute("emailList", emails);
				req.getRequestDispatcher("receiveEmail.jsp").forward(req, resp);
			}
		}else if(op.equals("delReceiveEmail")) {      //删收信箱
			String info = new EmailService().delReceiveEmail(req);
			if(info=="YES"){
				resp.sendRedirect("emailServlet?op=showReceiveEmail&account="+account+"");
			}else {
				resp.sendRedirect("LoginPage.jsp");
			}
		}else if(op.equals("showDraftEmail")) {      //草稿箱
			List<Email> emails = new EmailService().getDraftEmailList(req);
			req.setAttribute("emailList", emails);
			req.getRequestDispatcher("draftEmail.jsp").forward(req, resp);
		}else if(op.equals("updateUIDraftEmail")){   //草稿箱返回值到修改页面
			Email emails = new EmailService().updateUIDraft(req);
			req.setAttribute("email", emails);
			req.getRequestDispatcher("/draft/updateDraft.jsp").forward(req, resp);
		}else if(op.equals("updateDraft")){           //草稿箱修改
			int info = new EmailService().updateDraft(req);
			if(info!=0){
				resp.sendRedirect("emailServlet?op=showDraftEmail&account="+account+"");
			}else {
				resp.sendRedirect("LoginPage.jsp");
			}
		}else if(op.equals("delDraftEmail")) {      //删草稿
			String info = new EmailService().delDraftEmail(req);
			if(info=="YES"){
				resp.sendRedirect("emailServlet?op=showDraftEmail&account="+account+"");
			}else {
				resp.sendRedirect("LoginPage.jsp");
			}
		}else if(op.equals("sendEmail")) {//发邮件
			String receive_user_id = req.getParameter("receive_user_id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String account2 = req.getParameter("account2");
			if (receive_user_id == "" || title == "" || content == "") {
				printWriter.write("NULL");
			}
			String info = new EmailService().sendEmail(req);
			printWriter.write(info);
			printWriter.close();
//			if(info=="YES") {
//				resp.sendRedirect("index.jsp");
//			}else {
//				resp.sendRedirect("LoginPage.jsp");
//			}
		}else if(op.equals("sendUIEmail")) {
			req.getRequestDispatcher("sendEmail.jsp").forward(req, resp);

		}else if(op.equals("showOutboxEmail")) {      //发件箱
			List<Email> emails = new EmailService().getOutboxEmailList(req);
			req.setAttribute("emailList", emails);
			req.getRequestDispatcher("outbox.jsp").forward(req, resp);
		}else if(op.equals("delOutboxEmail")) {      //删草稿
			String info = new EmailService().delOutboxEmail(req);
			if(info=="YES"){
				resp.sendRedirect("emailServlet?op=showOutboxEmail&account="+account+"");
			}else {
				resp.sendRedirect("LoginPage.jsp");
			}
		}else if(op.equals("searchEmail")) {      //搜索
			List<Email> emails = new EmailService().getSearchEmailList(req);
			if(emails.size()!=0) {
				req.setAttribute("emailList", emails);
				req.getRequestDispatcher("searchEmail.jsp").forward(req, resp);
			}else {
				resp.sendRedirect("null.jsp");
			}
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
