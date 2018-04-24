package com.shoppingwebsite.objects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="products")
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="description", nullable=false)
	private String description;

	@Column(name="image", nullable=false)
	private String image;
	
	@Column(name="price", nullable=false)
	private BigDecimal price;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Product (int id, String description, String image, BigDecimal price) {
		this.id = id;
		this.description = description;
		this.image = image;
		this.price = price;
	}
	
}