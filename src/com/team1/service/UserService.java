package com.team1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.dao.UserDao;
import com.team1.model.User;
import com.team1.utils.ConMysql;
import com.team1.utils.PrimaryKeyUtil;

import net.sf.json.JSONObject;



public class UserService {

	Connection connection = ConMysql.getConMysql().getConnection();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	public static UserDao userDao = new UserDao();
	
	public String chechLogin(String account, String password, HttpServletRequest req, HttpServletResponse resp) {
		User user;
		user = userDao.checkLogin(account, password);
		if(user!=null) {
			HttpSession session = req.getSession();
			session.setAttribute("ACCOUNT", user.getAccount());       //保存用户账号在session里面
			session.setAttribute("USERNAME", user.getUsername());      //保存用户名在session里面
			if (req.getParameter("checkState").equals("YES")) {
				Cookie cookie = new Cookie("USERCOOKIEID", user.getUser_id());
				cookie.setMaxAge(30 * 24 * 60 * 60);
				//回写给浏览器
				resp.addCookie(cookie);
			} else {
				// 得到所有Cookie
				Cookie[] cookies = req.getCookies();

				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("USERCOOKIEID")) {
						cookies[i].setMaxAge(0);
						resp.addCookie(cookies[i]);
					}
				}
			}
			return "YES";
		}else {
			return "NO";
		}
	}
	public String searchCookie(HttpServletRequest req) {
		// 得到所有Cookie
				Cookie[] cookies = req.getCookies();
//				System.out.println(cookies);
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("USERCOOKIEID")) {
							// 说明有指定的cookie，将该用户查出来（通过id）；
							String userId = cookies[i].getValue();
							try {
								preparedStatement = connection.prepareStatement("select * from user where user_id=?");
								preparedStatement.setString(1, userId);
								resultSet = preparedStatement.executeQuery();
								resultSet.next();
								User user = new User(userId, resultSet.getString(2), resultSet.getString(3));
								// 将对象转化成JSON字符串
								JSONObject jsonObject = JSONObject.fromObject(user);
								String userJSon = jsonObject.toString();
								return userJSon;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (cookies.length == i) {
							return "";
						}
					}

					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("USERCOOKIEID")) {
							//说明有指定的cookie，将该用户查出来（通过id）；
							String userId = cookie.getValue();
							try {
								preparedStatement = connection.prepareStatement("select * from user where user_id=?");
								preparedStatement.setString(1, userId);
								resultSet = preparedStatement.executeQuery();
								resultSet.next();
								User user = new User(userId, resultSet.getString(2), resultSet.getString(3));
								JSONObject jsonObject = JSONObject.fromObject(user);
								String userJSon = jsonObject.toString();
								return userJSon;
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else {
							return "";
						}
					}
				}
				return null;
	}
	
	//注册
	public String registAccount(HttpServletRequest req) {
		String userAccount=req.getParameter("account");
		String password1=req.getParameter("password");
		String password2=req.getParameter("password2");
		
		if(password1.equals(password2)) {
			String sql="insert into user values(?,?,?,?,null,null,now())";
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, PrimaryKeyUtil.getPrimaryKey());
				preparedStatement.setString(2, userAccount);
				preparedStatement.setString(3, password1);
				preparedStatement.setString(4, req.getParameter("username"));
				int i=preparedStatement.executeUpdate();
				if(i!=0) {
					return "YES";
				}else {
					return "NO";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			return "NO";
		}
		return null;
	}
}
