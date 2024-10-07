package controller.itemServlets;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.dto.ItemDTO;
import model.services.itemServices;
import model.utils.sessionUtils;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/catalog")
public class catalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!sessionUtils.isLoggedIn(request)) {
            //response.sendRedirect("login.jsp");
            //return;
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        itemServices itemServices =new itemServices();
        List<ItemDTO> itemList = null;
        try {
            itemList = itemServices.getAllItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("items", itemList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request, response);
    }
}
