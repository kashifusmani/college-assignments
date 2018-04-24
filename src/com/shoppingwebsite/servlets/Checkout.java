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

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//We can add some other stuff here like adding a new order entry in orders table
		if (request.getSession().getAttribute("userName") == null) {
			request.getSession().setAttribute("UserNameOrPasswordError", "Please login first");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		ProductsDao dao = new ProductsDao();
		List<Product> products = dao.getAllProducts();
		request.getSession().setAttribute("productsList", products);
		request.getRequestDispatcher("/products.jsp").forward(request, response);
	}
	

}
