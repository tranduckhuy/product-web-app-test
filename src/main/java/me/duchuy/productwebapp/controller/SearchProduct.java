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
@WebServlet(name = "search-product", urlPatterns = {"/search-product"})
public class SearchProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchName = request.getParameter("search");
        System.out.println("searchName: " + searchName);

        ProductDAOImpl productDAO = new ProductDAOImpl();

        List<Product> listProduct = productDAO.getProductsByName(searchName);

        request.setAttribute("total", listProduct != null ? listProduct.size() : 0);
        
        if (listProduct != null) {
            HttpSession session = request.getSession();
            
            session.setAttribute("listProduct", listProduct);
            request.getRequestDispatcher("views/index.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
