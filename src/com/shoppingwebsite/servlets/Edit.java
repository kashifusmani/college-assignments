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
		Util.authenticateUser(request, response);
		String action = request.getParameter("editaction");
		if (action.equals("Delete")) {
			BooksDao dao = new BooksDao();
			int bookId =Integer.parseInt(request.getParameter("bookId"));
			dao.delete(bookId);
			//TODO: Use return value??
			List<Book> books = dao.getAllBooks();
			request.setAttribute("booksList", books);
			request.getRequestDispatcher("/books.jsp").forward(request, response);
		} else if (action.equals("Edit")) {
			
		}
		
		String description = request.getParameter("description");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String genre = request.getParameter("genre");
		String image = request.getParameter("image");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Book b = new Book(description, image, isbn, genre, author, quantity);
		BooksDao dao = new BooksDao();
		dao.add(b);
		List<Book> books = dao.getAllBooks();
		request.setAttribute("booksList", books);
		request.getRequestDispatcher("/books.jsp").forward(request, response);
	}

}
