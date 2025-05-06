package org.app.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.webapp.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// users/
@WebServlet(name = "UserServlet", urlPatterns = {"/users/*"})
public class UserServlet extends HttpServlet {
    private List<User> listUser;
    @Override
    public void init() throws ServletException {
        this.listUser = new ArrayList<>();
        initData(20);
    }

    protected void initData(int number) {
        for (int i = 0; i < number; i++) {
            User u = new User(i+ 1, "u-" + i, "u@gmail", "232323");
            listUser.add(u);
        }
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
        request.setAttribute("listUser", listUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse data xuong jspServlet
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lay id tru url
        int id = Integer.parseInt(request.getParameter("id"));
        User userUpdate = getUserById(id);
        // parse userUpdate xuong jspServlet
        request.setAttribute("userUpdate", userUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/users/update.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User userRemove = getUserById(id);
        listUser.remove(userRemove);
        response.sendRedirect("/users");
    }

    private User getUserById(int id) {
        User userRemove = null;
        for (User item: listUser) {
            if (item.getId() == id) {
                userRemove = item;
                break;
            }
        }
        return userRemove;
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // lay data tu req
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        int maxID = listUser.get(listUser.size() - 1).getId();
        User newUser = new User(maxID + 1, name, email, pass);
        listUser.add(newUser);
        response.sendRedirect("/users");
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User userUpdate = getUserById(id);
        String name = request.getParameter("name");
        userUpdate.setName(name);
        response.sendRedirect("/users");
    }
}
