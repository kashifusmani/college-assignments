package com.shoppingwebsite.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingwebsite.dao.CartDao;
import com.shoppingwebsite.dao.ProductsDao;
import com.shoppingwebsite.objects.Cart;
import com.shoppingwebsite.objects.Product;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CartDao dao = new CartDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("userName") == null) {
			request.getSession().setAttribute("UserNameOrPasswordError", "Please login first");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			List<Product> products = dao.getAllProducts((String) request.getSession().getAttribute("userName"));
			BigDecimal cartTotal = new BigDecimal(0);
			for (Product p : products) {
				cartTotal = cartTotal.add(p.getPrice());
			}
			request.setAttribute("cartList", products);
			request.setAttribute("cartTotal", cartTotal);
			request.getRequestDispatcher("/cart.jsp").forward(request, response);

		}
		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("userName") == null) {
			request.getSession().setAttribute("UserNameOrPasswordError", "Please login first");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		int id = Integer.parseInt(request.getParameter("productId"));
		String userName = (String)request.getSession().getAttribute("userName");
		Cart cart = new Cart(userName, id);
		boolean addToCartSuccess = dao.addToCart(cart);
		
		ProductsDao dao = new ProductsDao();
		List<Product> products = dao.getAllProducts();
		request.setAttribute("productsList", products);
		
		String msg = "";
		if (addToCartSuccess) {
			msg = "Product " + request.getParameter("productDescr") 
			+ " successfully added to cart";
		} else {
			msg = "Failed to add to cart, try again";
		}
		
		request.getSession().setAttribute("productAddedToCart", msg);
		request.getRequestDispatcher("/products.jsp").forward(request, response);

		
	}

}
