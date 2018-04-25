package com.shoppingwebsite.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingwebsite.dao.BooksDao;
import com.shoppingwebsite.dao.LoginDao;
import com.shoppingwebsite.dao.ProductsDao;
import com.shoppingwebsite.objects.Book;
import com.shoppingwebsite.objects.Product;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao dao = new LoginDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * Register a new user
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("registerButtonPressed") != null) {
			String userName =  request.getParameter("userName");
			String password = request.getParameter("password");
			if (dao.userExists(userName)) {
				request.getSession().setAttribute("UserNameOrPasswordError", "Username already exists, try again");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else {
				dao.register(userName, password);
				request.getSession().setAttribute("userName", userName);
				BooksDao dao = new BooksDao();
				List<Book> books = dao.getAllBooks();
				request.setAttribute("booksList", books);
				request.getRequestDispatcher("/books.jsp").forward(request, response);
			}
		}
	}

}
