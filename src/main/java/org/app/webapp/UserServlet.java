package org.app.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.webapp.databases.DBConnect;
import org.app.webapp.entities.User;
import org.app.webapp.services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// users/
@WebServlet(name = "UserServlet", urlPatterns = {"/users/*"})
public class UserServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        if (uri == null) {
            uri = "";
        }
        switch (uri) {
            case "/create":
                renderPageCreateUser(req, resp);
                break;
            case "/update":
                renderPageUpdateUser(req, resp);
                break;
            case "/delete":
                deleteUser(req, resp);
                break;
            case "/search":
                searchUser(req, resp);
                break;
            default:
                renderPageListUser(req, resp);
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
            case "/create":
                createUser(req, resp);
                break;
            case "/update":
                updateUser(req, resp);
                break;
        }
    }

    public void renderPageListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse data xuong jspServlet
        try {
            List<User> listUser = UserService.getAllUsers();
            request.setAttribute("listUser", listUser);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/list.jsp");
            requestDispatcher.forward(request, response);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderPageCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse data xuong jspServlet
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User userUpdate = UserService.findUserById(request, response);
            if (userUpdate != null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/update.jsp");
                request.setAttribute("userUpdate", userUpdate);
                requestDispatcher.forward(request, response);
            }
        }catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserService.deleteUser(request, response);
            response.sendRedirect("/users");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserService.create(request, response);
            response.sendRedirect("/users");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserService.updateUser(request, response);
            response.sendRedirect("/users");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> listUser = UserService.searchUserByName(request, response);
            request.setAttribute("listUser", listUser);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/list.jsp");
            requestDispatcher.forward(request, response);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        DBConnect.closeConnection();
    }
}
