package com.shoppingwebsite.objects;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;

	@Column(name="product_id")
	private int product_id;
		public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	
	
	public Cart (String userName, int product_id) {
		this.username = userName;
		this.product_id = product_id;
	}
	
	

}
