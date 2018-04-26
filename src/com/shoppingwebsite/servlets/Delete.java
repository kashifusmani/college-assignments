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

@WebServlet("/Delete")
public class Delete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserValueValidation.checkUserLoggedIn(request, response);
		String action = request.getParameter("editaction");
		BooksDao dao = new BooksDao();
		int bookId =Integer.parseInt(request.getParameter("bookId"));
		if (action.equals("Delete")) {
			dao.delete(bookId);
			List<Book> books = dao.getAllBooks();
			request.setAttribute("booksList", books);
			request.getRequestDispatcher("/books.jsp").forward(request, response);
		} else if (action.equals("Edit")) {
			String description = request.getParameter("description");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String genre = request.getParameter("genre");
			String image = request.getParameter("image");
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			request.setAttribute("description",description);
			request.setAttribute("author", author);
			request.setAttribute("isbn", isbn);
			request.setAttribute("genre", genre);
			request.setAttribute("image", image);
			request.setAttribute("quantity", quantity);
			request.setAttribute("bookId", bookId);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}
		
		
		
	}

}
