package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.dao.orderDAO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getAllOrders")
public class getAllOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sessionUtils.isLoggedIn(request) || !("worker".equals(sessionUtils.getUserRole(request)))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.");
            return;
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        try {
            orderDAO orderDao = new orderDAO();

            List<Order> orders = orderDao.getAllOrders();

            request.setAttribute("orders", orders);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/allorders.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve orders.");
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
