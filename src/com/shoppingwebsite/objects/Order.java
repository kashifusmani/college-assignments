package com.shoppingwebsite.objects;

import java.util.List;

public class Order {
	private String orderId;
	private String userName;
	private List<Product> products;
	public Order(String orderId, String userName, List<Product> products) {
		super();
		this.orderId = orderId;
		this.userName = userName;
		this.products = products;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getUserName() {
		return userName;
	}
	public List<Product> getProducts() {
		return products;
	}
	
	

}
