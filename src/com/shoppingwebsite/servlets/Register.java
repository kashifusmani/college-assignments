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
import com.shoppingwebsite.objects.Book;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginObj = new LoginDao();
       
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
		if (request.getParameter("registerButton") != null) {
			// Getting password
			String password = request.getParameter("password");
			
			// Getting user name 
			String userName =  request.getParameter("userName");
			
			// If the user already exists, then throw the error
			if (loginObj.isAUser(userName)) {
				// Setting the error string in NamePassErr object and redirect to register.jsp page
				request.getSession().setAttribute("NamePassErr", "Username already exists, please try again");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else {// If the user does not exist
				// Registering the user 
				loginObj.register(userName, password);
				// Setting the user name in Session
				request.getSession().setAttribute("userName", userName);
			
				BooksDao dao = new BooksDao();
				List<Book> books = dao.getAllBooks();
				request.setAttribute("booksList", books);
				request.getRequestDispatcher("/books.jsp").forward(request, response);
			}
		}
	}

}
