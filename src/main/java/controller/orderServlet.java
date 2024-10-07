package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.services.orderServices;

import java.io.IOException;
import java.sql.*;

@WebServlet("/order")
public class orderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("cart") == null) {
            response.sendRedirect("cart.jsp");
        }

        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.jsp");
        }
        orderServices orderServices = new orderServices();

        String[] itemIds = request.getParameterValues("itemId");
        String[] amounts = request.getParameterValues("amount");

        if (itemIds != null && amounts != null) {
            try {
                int newOrderId = orderServices.getNextOrderId();
                orderServices.placeOrder(newOrderId, username, itemIds, amounts);

                session.removeAttribute("cart");
                response.sendRedirect("catalog");

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Order submission failed"+e);
            }
        } else {
            response.sendRedirect("cart.jsp");
        }
    }
}


