package com.shoppingwebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {

	/**
	 * Check if user is already authenticated, if not, then rediret back to home.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void authenticateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("userName") == null) {
			request.getSession().setAttribute("UserNameOrPasswordError", "Please login first");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}
