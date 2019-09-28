package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.model.User;
import com.team1.utils.ConMysql;


public class UserDao {
	Connection connection = ConMysql.getConMysql().getConnection();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public User checkLogin(String account,String password) {
		try {
			User user = new User();
			preparedStatement = connection.prepareStatement("select * from user where account = ? and password = ?");
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user.setAccount(resultSet.getString("account"));
				user.setAddress(resultSet.getString("address"));
				user.setPassword(resultSet.getString("password"));
				user.setUser_id(resultSet.getString("user_id"));
				user.setTel(resultSet.getString("tel"));
				user.setUsername(resultSet.getString("username"));
				user.setTime(resultSet.getString("time"));
			}
			if(user.getAccount() == null || user.getPassword() == null) {
				return null;
			}else {
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User checkLogin2(String account) {
		try {
			User user = new User();
			preparedStatement = connection.prepareStatement("select * from user where account = ?");
			preparedStatement.setString(1, account);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user.setAccount(resultSet.getString("account"));
				user.setAddress(resultSet.getString("address"));
				user.setPassword(resultSet.getString("password"));
				user.setUser_id(resultSet.getString("user_id"));
				user.setTel(resultSet.getString("tel"));
				user.setUsername(resultSet.getString("username"));
				user.setTime(resultSet.getString("time"));
			}
			if(user.getAccount() == null) {
				return null;
			}else {
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
