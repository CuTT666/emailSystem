package com.team1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConMysql {
	static Properties properties = null;

	public final static ConMysql cm = new ConMysql();

	public static ConMysql getConMysql() {
		return cm;
	}

	private ConMysql() {
	}

	static {
		InputStream inputStream = ConMysql.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public Connection getConnection() {
		try {
			Class.forName(ConMysql.getConMysql().properties.getProperty("driverClass"));
			return DriverManager.getConnection(ConMysql.getConMysql().properties.getProperty("url"),
					ConMysql.getConMysql().properties.getProperty("username"),
					ConMysql.getConMysql().properties.getProperty("password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	public static void main(String[] args) {
		ConMysql.getConMysql().getConnection();
	}

	public void conClose(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void psClose(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void rsClose(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stClose(Statement st){
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
