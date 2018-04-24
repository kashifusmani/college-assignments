package com.shoppingwebsite.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoppingwebsite.objects.Cart;
import com.shoppingwebsite.objects.Product;

public class CartDao extends DbConfig {

	/**
	 * Adds a cart product to the cart table
	 * @param c
	 * @return
	 */
	public boolean addToCart(Cart c) {
		Connection con = getConnection();
		try {
			String query = "insert into " + CART_TABLE	
					+ " (username, product_id) values  (?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, c.getUsername());
			pst.setInt(2, c.getProduct_id());
			pst.executeUpdate();
			
			//reduce the quantity by 1.
			query = "update " + PRODUCTS_TABLE + " set quantity = quantity - 1 where id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1,  c.getProduct_id());
			pst.executeUpdate();			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			closeConnection(con);
		}
	}
	
	/**
	 * Fetch all the products from cart fora giver user
	 * @param userName
	 * @return
	 */
	public List<Product> getAllProducts(String userName) {
		List<Product> products = new ArrayList<Product>();
    	Connection con = getConnection();
		try {
			String query = "select products.id, description, image, price from " 
					+ CART_TABLE + " join "  + PRODUCTS_TABLE
					+ " on "
					+ CART_TABLE + ".product_id = " +  PRODUCTS_TABLE + ".id"
					+ " where " + CART_TABLE + ".username = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,  userName);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				int id = rst.getInt(1);
				String description = rst.getString(2);
				String image = rst.getString(3);
				BigDecimal price = rst.getBigDecimal(4);
				Product p = new Product(id, description, image, price);
				products.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return products;
		} finally {
			closeConnection(con);
		}
		return products;
	}
	
	/**
	 * Remove all items from user's cart once the order has been placed
	 * @param userName
	 */
	public void clearCart(String userName) {
		Connection con = getConnection();
		try {
			String query = "delete from  " + CART_TABLE	
					+ " where username = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userName);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(con);
		}
	}

}
