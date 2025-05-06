package org.app.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // logic
        if ("luan@gmail.com".equals(email) && "1234".equals(password)) {
            // thay doi location -> 302
            response.sendRedirect("/hello");
        } else {
            response.sendRedirect("/auth/login");
        }
    }
}
