package org.app.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.webapp.entities.User;
import org.app.webapp.services.UserService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth/*"})
public class AuthServlet extends HttpServlet {
    public AuthServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lay uri tru req
        String uri = req.getPathInfo();
        System.out.println(uri);
        if (uri == null) {
            uri = "";
        }
        switch (uri) {
            case "/login":
                renderPageLogin(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        System.out.println(uri);
        if (uri == null) {
            uri = "";
        }
        switch (uri) {
            case "/login":
                login(req, resp);
                break;
        }
    }

    public void renderPageLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // goi jsp servlet co url: /view/auth/login.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/auth/login.jsp");
        requestDispatcher.forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // code logic
        // b1: lay data tu request
        try {
            User user = UserService.findUserByEmailAndPassword(request, response);
            if (user != null) {
                response.sendRedirect("/users");
            } else {
                response.sendRedirect("/auth/login?error=1");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
