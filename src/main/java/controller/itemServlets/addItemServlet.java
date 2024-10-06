package controller.itemServlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Item;
import model.dao.itemDAO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;

@WebServlet("/additem")
public class addItemServlet extends HttpServlet {
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

        itemDAO itemDao = new itemDAO();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        try {
            itemDao.addItem(new Item(name, description, category, price,0 , amount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("catalog");
    }

}
