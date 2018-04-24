package com.shoppingwebsite.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingwebsite.dao.OrdersDao;
import com.shoppingwebsite.objects.ProductOrder;

@WebServlet("/Orders")
public class Orders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Fetch all orders made by a user
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Util.authenticateUser(request, response);
		String userName = (String) request.getSession().getAttribute("userName");
		OrdersDao dao = new OrdersDao();
		List<ProductOrder> orders =dao.getAllOrders(userName);
		request.getSession().setAttribute("ordersList", orders);
		request.getRequestDispatcher("/orders.jsp").forward(request, response);
	}
}
