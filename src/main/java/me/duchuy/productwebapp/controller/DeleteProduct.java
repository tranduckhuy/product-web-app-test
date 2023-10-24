package me.duchuy.productwebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.duchuy.productwebapp.dao.ProductDAOImpl;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteArticle", urlPatterns = {"/delete-product"})
public class DeleteProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productId");

        if (productId != null) {
            ProductDAOImpl productDAO = new ProductDAOImpl();

            int result = productDAO.delete(productId);
            if (result > 0) {
                System.out.println("Delete successfully! ProductId = " + productId);
            }
        }
        response.sendRedirect("list-product?page=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
