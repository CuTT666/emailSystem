package com.team1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.team1.dao.EmailDao;
import com.team1.dao.UserDao;
import com.team1.model.Email;
import com.team1.model.User;
import com.team1.utils.ConMysql;
import com.team1.utils.PrimaryKeyUtil;

public class EmailService {
	Connection connection = ConMysql.getConMysql().getConnection();
	Connection connection2 = ConMysql.getConMysql().getConnection();
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;
	ResultSet resultSet = null;
	public static EmailDao emailDao = new EmailDao();
	public static UserDao userDao = new UserDao();

	// 收信箱
	public List<Email> getEmailList(HttpServletRequest req) {
		// 分页
		int pageSize = 3;
		int pageNow = 1;
		//总记录数
		int rowCount = 0;
		//总页数
		int pageCount = 0;

		String number = req.getParameter("pageNow");
		if (number != null) {
			pageNow = Integer.parseInt(number);
		}

		try {
			preparedStatement = connection
					.prepareStatement("select count(*) from email where account = ? and receiver_user_id = ?");
			preparedStatement.setString(1, req.getParameter("account"));
			preparedStatement.setString(2, req.getParameter("account"));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}

		req.setAttribute("pageNow", pageNow);
		req.setAttribute("pageCount", pageCount);

		List<Email> emails = new ArrayList<Email>();
		try {
			// 查询收件人id为登录用户的所有记录
			preparedStatement = connection
					.prepareStatement("select * from email where receiver_user_id = ? and account = ? order by create_date desc limit "
							+ (pageNow - 1) * pageSize + "," + pageSize);
			preparedStatement.setString(1, req.getParameter("account"));
			preparedStatement.setString(2, req.getParameter("account"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Email email = new Email(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11));
				emails.add(email);
			}
			return emails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// 删除收信箱记录
	public String delReceiveEmail(HttpServletRequest req) {
		try {
			preparedStatement = connection.prepareStatement("delete from email where email_id = ?");
			preparedStatement.setString(1, req.getParameter("emailId"));
			int i = preparedStatement.executeUpdate();
			if (i != 0) {
				return "YES";
			} else {
				return "NO";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 草稿箱展示
	public List<Email> getDraftEmailList(HttpServletRequest req) {
		List<Email> emails = new ArrayList<Email>();
		try {
			// 查询收件人id为登录用户的所有记录
			preparedStatement = connection.prepareStatement("select * from email where account = ? and draft = 1 order by create_date desc");
			preparedStatement.setString(1, req.getParameter("account"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Email email = new Email(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11));
				emails.add(email);
			}
			return emails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 取得选中的草稿箱中的值
	public Email updateUIDraft(HttpServletRequest req) {
		try {
			preparedStatement = connection.prepareStatement("select * from email where email_id = ?");
			preparedStatement.setString(1, req.getParameter("emailId"));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Email email = new Email(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11));
				return email;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 修改草稿箱
	public int updateDraft(HttpServletRequest req) {
		try {
			preparedStatement = connection.prepareStatement("update email set title=?,content=? where email_id=?");
			preparedStatement.setString(1, req.getParameter("title")); // getParameter()里面的属性是name=""中的
			preparedStatement.setString(2, req.getParameter("content"));
			preparedStatement.setString(3, req.getParameter("email_id"));
			int i = preparedStatement.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 删草稿
	public String delDraftEmail(HttpServletRequest req) {
		try {
			preparedStatement = connection.prepareStatement("delete from email where email_id = ?");
			preparedStatement.setString(1, req.getParameter("emailId"));
			int i = preparedStatement.executeUpdate();
			if (i != 0) {
				return "YES";
			} else {
				return "NO";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 发邮件
	public String sendEmail(HttpServletRequest req) {

		String account = req.getParameter("account");
		String account2 = req.getParameter("receive_user_id");
		User user = new User();
		User user2 = new User();
		user = userDao.checkLogin2(account);
		user2 = userDao.checkLogin2(account2);

		if (user != null && user2 != null) {
			
			// 插入两条数据，这是接收者的
			String sql2 = "insert into email values(?,?,?,?,?,?,0,now(),0,?,?)";
			try {
				preparedStatement2 = connection2.prepareStatement(sql2);
				preparedStatement2.setString(1, PrimaryKeyUtil.getPrimaryKey());
				preparedStatement2.setString(2, user2.getUser_id());
				preparedStatement2.setString(3, user2.getAccount());
				preparedStatement2.setString(4, user2.getUsername());
				preparedStatement2.setString(5, req.getParameter("title"));
				preparedStatement2.setString(6, req.getParameter("content"));
				preparedStatement2.setString(7, user.getAccount());
				preparedStatement2.setString(8, req.getParameter("receive_user_id"));
				preparedStatement2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "insert into email values(?,?,?,?,?,?,0,now(),0,?,?)";
			// 插入两条数据，这条是发送者的
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, PrimaryKeyUtil.getPrimaryKey());
				preparedStatement.setString(2, user.getUser_id());
				preparedStatement.setString(3, user.getAccount());
				preparedStatement.setString(4, user.getUsername());
				preparedStatement.setString(5, req.getParameter("title"));
				preparedStatement.setString(6, req.getParameter("content"));
				preparedStatement.setString(7, user.getAccount());
				preparedStatement.setString(8, req.getParameter("receive_user_id"));
				int i = preparedStatement.executeUpdate();
				if (i != 0) {
					return "YES";
				} else {
					return "NO";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return "NO";
		}

		return null;
	}

	// 发件箱
	public List<Email> getOutboxEmailList(HttpServletRequest req) {
		List<Email> emails = new ArrayList<Email>();
		try {
			// 查询用户id和发件人id都为登录用户的所有记录
			preparedStatement = connection
					.prepareStatement("select * from email where account = ? and sender_user_id = ? order by create_date desc");
			preparedStatement.setString(1, req.getParameter("account"));
			preparedStatement.setString(2, req.getParameter("account"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Email email = new Email(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11));
				emails.add(email);
			}
			return emails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 发件箱的删除
	public String delOutboxEmail(HttpServletRequest req) {
		try {
			preparedStatement = connection.prepareStatement("delete from email where email_id = ?");
			preparedStatement.setString(1, req.getParameter("emailId"));
			int i = preparedStatement.executeUpdate();
			if (i != 0) {
				return "YES";
			} else {
				return "NO";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Email> getSearchEmailList(HttpServletRequest req) {
		List<Email> emails = new ArrayList<Email>();
		try {
			// 查询用户id和发件人id都为登录用户的所有记录
			preparedStatement = connection.prepareStatement("select * from email where title=? and account =?");
			preparedStatement.setString(1, req.getParameter("search"));
			preparedStatement.setString(2, req.getParameter("account"));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Email email = new Email(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11));
				emails.add(email);
			}
			return emails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
