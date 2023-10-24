package me.duchuy.productwebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.duchuy.productwebapp.dao.UserDAOImpl;
import me.duchuy.productwebapp.util.Encode;

/**
 *
 * @author ADMIN
 */

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uId = (String) session.getAttribute("uId");
        
        if (uId != null) {
            response.sendRedirect("list-product?page=1");
        } else {
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        password = Encode.toSHA256(password);
        
        UserDAOImpl userDao = new UserDAOImpl();
        String name = userDao.isValidUser(email, password);

        if (name != null) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            response.sendRedirect("list-product?page=1");
        } else {
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
        
    }


}
