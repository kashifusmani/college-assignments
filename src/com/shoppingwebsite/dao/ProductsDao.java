package com.shoppingwebsite.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.shoppingwebsite.objects.Product;

public class ProductsDao extends DbConfig {

    /**
     * Read all the Products.
     * 
     * @return a List of Products
     */
    public List<Product> getAllProducts() {
    	List<Product> products = new ArrayList<Product>();
    	Connection con = getConnection();
		try {
			String query = "select id, description, image, price from " + PRODUCTS_TABLE + " where quantity>0";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				int id = rst.getInt(1);
				String description = rst.getString(2);
				String image = rst.getString(3);
				BigDecimal price = rst.getBigDecimal(4);
				Product p = new Product(id, description, image, price);
				products.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return products;
		} finally {
			closeConnection(con);
		}
		return products;
        /*List<Product> products = null;
        // Create an EntityManager
        EntityTransaction transaction = null;
        EntityManager em = null;
        try {
        	InitialContext initialContext = new InitialContext();
        	EntityManagerFactory emf = (EntityManagerFactory) initialContext.lookup("java:/MySqlDS");
    		em = emf.createEntityManager();
    		
            // Get a transaction
            transaction = em.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Products
            products = em.createQuery("SELECT * FROM products",
                    Product.class).getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            em.close();
        }
        return products;*/
    	
    }


}