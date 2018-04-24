package com.shoppingwebsite.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingwebsite.dao.ProductsDao;
import com.shoppingwebsite.objects.Product;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("userName") == null) {
			request.getSession().setAttribute("UserNameOrPasswordError", "Please login first");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
			request.getSession().setAttribute("userName", null);
			request.setAttribute("productsList", null);
			request.getSession().setAttribute("logoutError", "User logged out");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
