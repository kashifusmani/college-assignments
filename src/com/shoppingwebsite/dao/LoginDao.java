package com.shoppingwebsite.dao;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDao { 
	public static final String LOGIN_TABLE = "login";

	/**
	 * Check if a user with given userName already exists 
	 * @param userName
	 * @return true if exists, false otherwise.
	 */
	public boolean userExists(String userName) {
		Connection con = getConnection();
		try {
			String query = "select * from " + LOGIN_TABLE + " where  username=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userName);
			ResultSet rst = pst.executeQuery();
			return rst.next();		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Authenticate a user with given username and password.
	 * @param userName
;	 * @param password
	 * @return true if the userName password pair is correct, false otherwise
	 */
	public boolean authenticate(String userName, String password) {
		Connection con = getConnection();
		try {
			String query = "select * from " + LOGIN_TABLE 
					+ " where  username=? and password=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userName);
			pst.setString(2, password);
			ResultSet rst = pst.executeQuery();
			return rst.next();		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			closeConnection(con);
		}
	}
	
	public void register(String userName, String password) {
		Connection con = getConnection();
		try {
			String query = "insert into "  + LOGIN_TABLE	+ " values  (?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userName);
			pst.setString(2, password);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(con);
		}
	}
	
	private Connection getConnection() {	
		Connection con = null;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup("java:/library");
			con = dataSource.getConnection();			
			} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	private void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			//do nothing
		}
	}


}
