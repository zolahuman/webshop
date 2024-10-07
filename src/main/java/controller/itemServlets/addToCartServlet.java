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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCart")
public class addToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = sessionUtils.getSession(request);
        if (!sessionUtils.isLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page. Need to login.");
            return;
        }else {
            String username = sessionUtils.getUsername(request);
            String role = sessionUtils.getUserRole(request);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        itemServices itemServices = new itemServices();

        ItemDTO item = null;
        try {
            item = itemServices.getItemById(itemId);
            item.setAmount(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (item != null) {
            int i;
            for( i=0; i<cart.size() ; i++){
                if (cart.get(i).getId()==itemId){
                    cart.get(i).setAmount(cart.get(i).getAmount()+1);
                    break;
                }
            }
            if (i >= cart.size()){
                cart.add(item);
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() +"/catalog");
    }


}
