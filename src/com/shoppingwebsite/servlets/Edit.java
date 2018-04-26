package com.shoppingwebsite.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingwebsite.dao.BooksDao;
import com.shoppingwebsite.objects.Book;

@WebServlet("/Edit")
public class Edit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserValueValidation.authenticateUser(request, response);
		String description = request.getParameter("description");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String genre = request.getParameter("genre");
		String image = request.getParameter("image");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int bookId =Integer.parseInt(request.getParameter("bookId"));
		BooksDao dao = new BooksDao();
		Book b = new Book(bookId, description, image, isbn, genre, author, quantity);
		dao.update(b);
		List<Book> books = dao.getAllBooks();
		request.setAttribute("booksList", books);
		request.getSession().setAttribute("bookEdited", "book edited successfully");
		request.getRequestDispatcher("/books.jsp").forward(request, response);
				
		
		
	}

}