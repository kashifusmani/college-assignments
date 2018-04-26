package com.shoppingwebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserValueValidation {
	
	// checking whether user is authenticated or not. 
	public static void authenticateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Getting value from session and checking if the user name is null or not.
		if (request.getSession().getAttribute("userName") == null) {
			// If the user name is null, then asking user to login first
			request.getSession().setAttribute("NamePassErr", "Please login first");
			// Redirecting to index page
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}
