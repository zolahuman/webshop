package controller.itemServlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dto.ItemDTO;
import model.services.itemServices;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;


@WebServlet("/getItem")
public class getItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

        int itemId;
        try {
            itemId = Integer.parseInt(request.getParameter("itemId"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID");
            return;
        }
        itemServices itemServices = new itemServices();

        ItemDTO item = null;
        try {
            item = itemServices.getItemById(itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (item == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
            return;
        }

        request.setAttribute("item", item);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/updateproduct.jsp");
        dispatcher.forward(request, response);
    }
}
