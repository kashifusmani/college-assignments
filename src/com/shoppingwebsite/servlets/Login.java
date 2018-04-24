package com.shoppingwebsite.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingwebsite.dao.LoginDao;
import com.shoppingwebsite.dao.ProductsDao;
import com.shoppingwebsite.objects.Product;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao dao = new LoginDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
//TODO: Add Price for Product
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("loginButtonPressed") != null) {
			String userName =  request.getParameter("userName");
			String password = request.getParameter("password");
			
			HttpSession session = request.getSession(false);  	
			session.setAttribute("userName", userName);
			 
			 
			if (dao.authenticate(userName, password)) {
				request.getSession().setAttribute("userName", userName);
				ProductsDao dao = new ProductsDao();
				List<Product> products = dao.getAllProducts();
				request.setAttribute("productsList", products);
				request.getRequestDispatcher("/products.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("UserNameOrPasswordError", "Username and password do not match, try again");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}			
		}
	}


}
