package com.shoppingwebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Authenticating user
		UserValueValidation.authenticateUser(request, response);
		// Setting user name and product list to null
		request.getSession().setAttribute("userName", null);
		request.setAttribute("productsList", null);
		// Redirecting to index.jsp page, and passing a message
		request.getSession().setAttribute("logoutError", "User logged out");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
