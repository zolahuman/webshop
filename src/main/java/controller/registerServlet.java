package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.userDAO;
import model.dto.UserDTO;

import java.io.IOException;
import java.sql.*;


@WebServlet("/register")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (username == null || password == null || role == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }

        userDAO userDao = new userDAO();
        boolean succes=false;
        try {
            succes=userDao.addUser(new UserDTO(username,password,role));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (succes) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Registration failed");
        }
    }
}
