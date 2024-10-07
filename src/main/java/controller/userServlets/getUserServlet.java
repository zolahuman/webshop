package controller.userServlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import model.dao.userDAO;
import model.dto.UserDTO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getUser")
public class getUserServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:sqlite:C:/Distributedlabs/databas/webshop.db";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sessionUtils.isLoggedIn(request) || !("admin".equals(sessionUtils.getUserRole(request)))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.");
            return;
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        String usertoedit = request.getParameter("usertoedit");
        if (usertoedit == null || usertoedit.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
            return;
        }

        UserDTO user = null;
        userDAO userDao = new userDAO();
        try {
             user = userDao.getUserByUsername(usertoedit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user != null) {
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/user.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }
}
