package org.app.webapp.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.webapp.databases.DBConnect;
import org.app.webapp.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static Connection conn = DBConnect.getConnection();

    public static List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            User u = new User();
            u.setId(id);
            u.setName(name);
            u.setEmail(email);
            u.setPhone(phone);
            u.setAddress(address);
            users.add(u);
        }
        return users;
    }

    public static void create(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sql = "INSERT INTO users (name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, phone);
        preparedStatement.setString(5, address);
        preparedStatement.execute();
    }

    public static void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        // chuan bi cau lenh sql
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // set gia tri cho cac tham so tuong ung voi vi tri ?
        preparedStatement.setInt(1, Integer.parseInt(id));
        // thuc thi cau lenh
        preparedStatement.execute();
    }

    public static List<User> searchUserByName(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String keyword = request.getParameter("keyword");
        // thuc thi lay data tu database
        String sql = "SELECT * FROM users where name like ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + keyword + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        // Xu ly data tu database
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            User u = new User();
            u.setId(id);
            u.setName(name);
            u.setEmail(email);
            u.setPhone(phone);
            u.setAddress(address);
            users.add(u);
        }
        return users;
    }

    public static User findUserById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            int uId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            user = new User();
            user.setId(uId);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
        }
        return user;
    }

    public static User findUserByEmailAndPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            user = new User();
            user.setId(id);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
        }
        return user;
    }

    public static void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        // chuan bi cau lenh sql
        String sql = "UPDATE users SET name = ?, phone = ?, address = ? WHERE id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // set gia tri cho cac tham so tuong ung voi vi tri ?
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, phone);
        preparedStatement.setString(3, address);
        preparedStatement.setInt(4, Integer.parseInt(id));
        // thuc thi cau lenh
        preparedStatement.execute();

    }
}
