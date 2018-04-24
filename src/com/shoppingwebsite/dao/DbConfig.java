package com.shoppingwebsite.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConfig {
	public static final String USERNAME = "root";
	public static final String PASSWORD = "password";
	public static final String CART_TABLE = "cart";
	public static final String LOGIN_TABLE = "login";
	public static final String PRODUCTS_TABLE = "Products";

	
	Connection getConnection() {	
		Connection con = null;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup("java:/MySqlDS");
			con = dataSource.getConnection();
			
			//Class.forName("com.mysql.jdbc.Driver");  
			//DriverManager.registerDriver (new com.mysql.jdbc.Driver());
			//con=DriverManager.getConnection(  
				//	"jdbc:mysql://localhost:3306/" + DbConfig.DATABASE_NAME ,DbConfig.USERNAME,DbConfig.PASSWORD);  
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	 void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			//do nothing
		}
	}

}
