package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.services.userServices;
import model.dto.ItemDTO;
import model.dto.UserDTO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/login")
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
        }

        UserDTO user = null;
        userServices userServices = new userServices();
        try {
            user = userServices.getUserByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user.getPassword().equals(password)) {

            HttpSession session = sessionUtils.getSession(request);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            List<ItemDTO> cart = new ArrayList<>();
            session.setAttribute("cart", cart);
            response.sendRedirect("catalog");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Login failed");
        }
    }

}
