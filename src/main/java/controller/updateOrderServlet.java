package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.services.orderServices;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateOrder")
public class updateOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("orderStatus");
        orderServices orderServices = new orderServices();

        try {
            orderServices.updateOrderStatus(orderId, newStatus);
            response.sendRedirect("getAllOrders");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
