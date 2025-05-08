package org.app.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "StaticServlet", urlPatterns = {"/statics/*"})
public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        if (uri == null) {
            uri = "";
        }

        if (uri.equals("/css/style.css") || uri.equals("/js/my.js")) {
            handleStaticFile(req, resp);
        }
    }

    protected void handleStaticFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathCss = request.getPathInfo(); // css/style.css
        String fullPath = getServletContext().getRealPath("/statics"  + pathCss);
        File file = new File(fullPath);
        if (!file.exists()) {
            throw new FileNotFoundException("File css not found");
        }

        Files.copy(file.toPath(), response.getOutputStream());
    }
}
