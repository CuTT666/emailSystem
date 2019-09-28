package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.model.Email;
import com.team1.model.User;

public class EmailDao {
	 Connection conn = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
	
	// 查询邮件
	public  Email selectEmail(String account) {
		Email email = new Email();
		System.out.println(account);
		String sql = "select * from email where account=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				email.setContent(rs.getString("contend"));
				email.setCreate_date(rs.getString("create_date"));
				email.setDraft(rs.getString("draft"));
				email.setEmail_id(rs.getString("email_id"));
				email.setMailbox(rs.getString("mailbox"));
				email.setTitle(rs.getString("title"));
				email.setEmail_id(rs.getString("email_id"));
				email.setUsername(rs.getString("username"));
				
			}
			System.out.println(email.getContent());
			if(email.getAccount()==null) {
				return null;
			}else {
				return email;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//发邮件
	/*public int sendEmail(String account) {
		Email email = new Email();
		String sql="insert into email(email_id,user_id,account,username,title,"
				+ "content,draft,create_date,mailbox,sender_user_id,receive_user_id)"
				+ "values(?,null,?,?,?,?,false,now(),false,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,"email_id");
			ps.setString(2,account);
			ps.setString(3,"username");
			ps.setString(4,"title");
			ps.setString(5,"content");
			ps.setString(6,"sender_user_id");
			ps.setString(7,"receive_user_id");
			int i=ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}*/
}
