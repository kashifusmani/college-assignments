package com.shoppingwebsite.objects;

import java.math.BigDecimal;

public class ProductOrder {

	private String description;
	private BigDecimal price;
	private String image;
	private String orderId;
	
	public ProductOrder(String description, BigDecimal price, String image, String orderId) {
		this.description = description;
		this.price = price;
		this.image = image;
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public String getOrderId() {
		return orderId;
	}
	
	

}
