package controller.userServlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.userDAO;
import model.dto.UserDTO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;

@WebServlet("/updateUser")
public class updateUserServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:sqlite:C:/Distributedlabs/databas/webshop.db";



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sessionUtils.isLoggedIn(request) || !("admin".equals(sessionUtils.getUserRole(request)))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.");
            return;
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }


        String usernametoedit = request.getParameter("usertoedit");
        String newPassword = request.getParameter("newpassword");
        String newRole = request.getParameter("newrole");

        userDAO userDao = new userDAO();
        boolean succes = false;
        try {
            succes=userDao.updateUser(new UserDTO(usernametoedit,newPassword,newRole));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (succes) {
            response.sendRedirect("getUsers");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }
}
