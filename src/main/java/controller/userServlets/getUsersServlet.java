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

@WebServlet("/getUsers")
public class getUsersServlet extends HttpServlet {

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

        List<UserDTO> userList = new ArrayList<>();
        userDAO userDao = new userDAO();
        try {
            userList = userDao.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/users.jsp");
        dispatcher.forward(request, response);
    }
}
