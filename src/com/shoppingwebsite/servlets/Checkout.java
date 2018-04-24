package com.shoppingwebsite.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingwebsite.dao.CartDao;
import com.shoppingwebsite.dao.OrdersDao;
import com.shoppingwebsite.dao.ProductsDao;
import com.shoppingwebsite.objects.Order;
import com.shoppingwebsite.objects.Product;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create order and checkout
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//We can add some other stuff here like adding a new order entry in orders table
		Util.authenticateUser(request, response);
		//create order 
		Random rand = new Random();
		String orderId = "ABC-" + rand.nextInt() + "-XYZ";
		String userName = (String) request.getSession().getAttribute("userName");
		
		CartDao cartDao = new CartDao();
		List<Product> cartPoducts = cartDao.getAllProducts(userName);
		Order order = new Order (orderId, userName, cartPoducts);
		OrdersDao ordersDao = new OrdersDao();
		ordersDao.createOrder(order);
		//clear user's cart
		cartDao.clearCart(userName);
		
		
		String message = "Your order has been placed, the order number is " + orderId + " . Continue to browse our products!";
		
		request.getSession().setAttribute("cartConfirmationMessage", message);
		ProductsDao dao = new ProductsDao();
		List<Product> products = dao.getAllProducts();
		request.getSession().setAttribute("productsList", products);
		request.getRequestDispatcher("/products.jsp").forward(request, response);
	}
	

}
