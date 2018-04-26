package com.shoppingwebsite.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingwebsite.dao.BooksDao;
import com.shoppingwebsite.dao.LoginDao;
import com.shoppingwebsite.objects.Book;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginObj = new LoginDao();
       

	/**
	*Log in a user
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// If the user pressed the loging button
		if (request.getParameter("loginButton") != null) {
			
			// Getting user name and password
			String userName =  request.getParameter("userName");
			String password = request.getParameter("password");
			
			// Creating a session and setting user name in session
			HttpSession session = request.getSession(false);  	
			session.setAttribute("userName", userName);
			 
			 
			// Authenticating user
			if (loginObj.checkUserNameAndPassword(userName, password)) {
				// Setting the user name in the session
				request.getSession().setAttribute("userName", userName);
				BooksDao dao = new BooksDao();
				List<Book> books = dao.getAllBooks();
				request.setAttribute("booksList", books);
				request.getRequestDispatcher("/books.jsp").forward(request, response);
			} else {
				// If the user is not authenticated, then redirecting to index page with error message.
				request.getSession().setAttribute("NamePassErr", "Invalid username and password");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}			
		}
	}


}