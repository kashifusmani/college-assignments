package com.shoppingwebsite.dao;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDao { 
	public static final String LOGIN_TABLE = "login";

	// Checking if the user exists or not.
	public boolean isAUser(String userName) {
		// Creating a connection object
		Connection con = connectWithDataBase();
		try {
			// Query to fetch all the values of the user
			String query = "select * from " + LOGIN_TABLE + " where  username='" + userName + "'";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			return rst.next();		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Authenticating user
	public boolean checkUserNameAndPassword(String userName, String password) {
		Connection con = connectWithDataBase();
		try {
			String query = "select * from " + LOGIN_TABLE 
					+ " where  username='" + userName + "' and password='" + password + "'";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			return rst.next();		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Registering the user into the database.
	public void register(String userName, String password) {
		Connection con = connectWithDataBase();
		try {
			String query = "insert into "  + LOGIN_TABLE	+ " values  ('" + userName + "','" + password + "')";
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection connectWithDataBase() {	
		Connection con = null;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup("java:/library");
			con = dataSource.getConnection();			
			} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


}
