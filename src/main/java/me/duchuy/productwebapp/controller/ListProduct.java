package me.duchuy.productwebapp.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.duchuy.productwebapp.dao.ProductDAOImpl;
import me.duchuy.productwebapp.model.Product;

/**
 *
 * @author ADMIN
 */

@WebServlet(name = "list-product", urlPatterns = {"/list-product"})
public class ListProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String page = request.getParameter("page");
        int pageNum = 1;
        if (page != null && page.matches("\\d+")) {
            int temp = Integer.parseInt(page);
            if (temp > 1) {
                pageNum = temp;
            }
        }
        
        if (pageNum != 1) {
            pageNum -= 1;
            pageNum = pageNum*6 + 1;
        }
        
        ProductDAOImpl productDAO = new ProductDAOImpl();
        
        List<Product> listProduct = productDAO.getProducts(pageNum, 6);
        
        int total = productDAO.getTotalNumProduct();
        request.setAttribute("total", total);
        
        HttpSession session = request.getSession();
        session.setAttribute("listProduct", listProduct);
        
        request.setAttribute("act", "list");
        
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
