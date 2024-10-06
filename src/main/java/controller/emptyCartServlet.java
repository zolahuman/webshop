package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;
import model.utils.sessionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/emptyCart")
public class emptyCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sessionUtils.isLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page. Need to login.");
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        sessionUtils.removeSessionAttribute(request,"cart");
        sessionUtils.removeSessionAttribute(request,"totalprice");

        List<Item> cart = new ArrayList<>();
        sessionUtils.setSessionAttribute(request,"cart",cart);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}
