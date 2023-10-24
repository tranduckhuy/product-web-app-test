package me.duchuy.productwebapp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.duchuy.productwebapp.dao.ProductDAOImpl;
import me.duchuy.productwebapp.model.Product;

/**
 *
 * @author ADMIN
 */

@WebServlet(name = "form-product", urlPatterns = {"/form-product"})
@MultipartConfig(maxFileSize = 16177215)
public class FormProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String proName = request.getParameter("proName");
        String proDescription = request.getParameter("proDescription");
        String proCategory = request.getParameter("proCategory");

        // Time
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        long currentTime = System.currentTimeMillis();
        Timestamp timeCreated = new Timestamp(currentTime);
        sdf.format(timeCreated);

        // Image
        String imageURL = request.getParameter("proImage");
        System.out.println("imageURL: " + imageURL);
        
        //
        Product product = new Product((currentTime+1610) + "", proName, proDescription, imageURL, proCategory, timeCreated);

        ProductDAOImpl productDAO = new ProductDAOImpl();

        int result = productDAO.add(product);
        
        if (result > 0) {
            System.out.println("1");
            response.sendRedirect("list-product?page=1");
        } else {
            System.out.println("2");
            response.sendRedirect("form-product");
        }
    }

}
