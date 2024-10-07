package controller.itemServlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.dto.ItemDTO;
import model.utils.sessionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getcart")
public class getCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int totalprice = getTotalPrice(cart);

        request.setAttribute("cart", cart);
        request.setAttribute("totalprice", totalprice);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }

    private int getTotalPrice(List<ItemDTO> cart){
        int totalprice=0;
        for (ItemDTO c: cart){
            totalprice+=c.getPrice()*c.getAmount();
        }
        return totalprice;
    }

}
