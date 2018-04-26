package com.library.objects;

public class Book {

	private int id;
	private String description;
	private String image;
	private String isbn;
	private String genre;
	private String author;
	private int quantity;
	public Book(int id, String description, String image, String isbn, String genre, String author, int quantity) {
		this.id = id;
		this.description = description;
		this.image = image;
		this.isbn = isbn;
		this.genre = genre;
		this.author = author;
		this.quantity = quantity;
	}
	public Book(String description, String image, String isbn, String genre, String author, int quantity) {
		this.description = description;
		this.image = image;
		this.isbn = isbn;
		this.genre = genre;
		this.author = author;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getImage() {
		return image;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getGenre() {
		return genre;
	}
	public String getAuthor() {
		return author;
	}
	public int getQuantity() {
		return quantity;
	}
	

}
