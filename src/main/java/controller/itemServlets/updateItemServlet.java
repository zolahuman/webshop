package controller.itemServlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.dao.itemDAO;
import model.dto.ItemDTO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;

@WebServlet("/updateItem")
public class updateItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!sessionUtils.isLoggedIn(request) || !("admin".equals(sessionUtils.getUserRole(request)))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.");
            return;
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }


        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("price"));
        int itemId = Integer.parseInt(request.getParameter("id"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        boolean succes=false;

        if (name == null || description == null || category == null || price < 0 || amount < 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
            return;
        }else {
            itemDAO itemDao = new itemDAO();
            try {
                succes=itemDao.updateItem(new ItemDTO(name, description, category, price, itemId, amount));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (succes) {
            response.sendRedirect("catalog");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item with ID " + itemId + " not found.");
        }
    }
}