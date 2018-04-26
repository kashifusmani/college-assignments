package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.library.objects.Book;

public class BooksDao {
	private static final String BOOKS_TABLE = "books";

	/**
	 * Add a new book to database
	 * @param b
	 */
	public void add(Book b) {
		Connection con = connectWithDataBase();
		try {
			String query = "insert into " + BOOKS_TABLE + 
					" (description, image, quantity, author, genre, isbn) values (?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, b.getDescription());
			pst.setString(2, b.getImage());
			pst.setInt(3,  b.getQuantity());
			pst.setString(4, b.getAuthor());
			pst.setString(5, b.getGenre());
			pst.setString(6, b.getIsbn());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Get all books from database
	 * @return
	 */
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		Connection con = connectWithDataBase();
		try {
			String query = "select * from " + BOOKS_TABLE ;
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				int id = rst.getInt(1);
				String description = rst.getString(2);
				String image = rst.getString(3);
				int quantity = rst.getInt(4);
				String author = rst.getString(5);
				String genre = rst.getString(6);
				String isbn = rst.getString(7);
				Book b = new Book(id, description, image, isbn, genre, author, quantity);
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return books;
		} 
		return books;
	}
	
	/**
	 * Delete a book with given id
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		Connection con = connectWithDataBase();
		try {
			String query = "delete from " + BOOKS_TABLE + 
					" where id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * Update the given book in database with new values
	 * @param b
	 */
	public boolean update(Book b) {
		Connection con = connectWithDataBase();
		try {
			String query = "update " + BOOKS_TABLE 
					+ " set description = ?, image = ?, quantity = ?, author =?, isbn =?, genre = ?" 
					+ " where id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, b.getDescription());
			pst.setString(2, b.getImage());
			pst.setInt(3, b.getQuantity());
			pst.setString(4, b.getAuthor());
			pst.setString(5, b.getIsbn());
			pst.setString(6, b.getGenre());
			pst.setInt(7, b.getId());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
