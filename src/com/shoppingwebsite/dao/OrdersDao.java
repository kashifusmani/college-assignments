package com.shoppingwebsite.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoppingwebsite.objects.Order;
import com.shoppingwebsite.objects.Product;
import com.shoppingwebsite.objects.ProductOrder;

public class OrdersDao extends DbConfig {

	/**
	 * 
	 * @param userName
	 */
	public void createOrder(Order order) {
		Connection con = getConnection();
		try {
			List<Product> products = order.getProducts();
			for (Product p : products) {
				String query = "insert into "  + ORDERS_TABLE	+ " (username, product_id, order_id) values  (?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, order.getUserName());
				pst.setInt(2, p.getId());
				pst.setString(3, order.getOrderId());
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(con);
		}		
	}
	
	/**
	 * get all orders for a given user
	 * @param userName
	 * @return
	 */
	public List<ProductOrder> getAllOrders(String userName) {
		List<ProductOrder> result = new ArrayList<>();
		Connection con = getConnection();
		try {
			String query = "select o.order_id, p.description, p.image, p.price  from "
					+ "shoppingwebsite.orders o join shoppingwebsite.products p on "
					+ "p.id = o.product_id where o.username=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userName);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				String orderId = rst.getString(1);
				String description = rst.getString(2);
				String image = rst.getString(3);
				BigDecimal price = rst.getBigDecimal(4);
				ProductOrder p = new ProductOrder(description, price, image, orderId);
				result.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(con);
		}
		return result;
	}
}
