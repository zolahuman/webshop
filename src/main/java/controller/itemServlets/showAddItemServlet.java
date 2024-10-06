package controller.itemServlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utils.sessionUtils;
import java.io.IOException;


@WebServlet("/showAddItemForm")
public class showAddItemServlet extends HttpServlet {
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/additem.jsp");
        dispatcher.forward(request, response);
    }
}
