package model.dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Distributedlabs/webshop/databas/webshop.db";


    public boolean addUser(User user) throws SQLException {
        boolean succes=false;
        try {
            Class.forName("org.sqlite.JDBC");
            if (getUserByUsername(user.getUsername())==null){
                String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                try (Connection connection = DriverManager.getConnection(DB_URL);
                     PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, user.getUsername());
                    statement.setString(2, user.getPassword());
                    statement.setString(3, user.getRole());

                    int rowsAffected = statement.executeUpdate();
                    succes = rowsAffected > 0;
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return succes;
    }


    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String password = resultSet.getString("password");
                        String role = resultSet.getString("role");
                        return new User(username, password, role);
                    } else {
                        return null;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(username, password, role);
                userList.add(user);
            }
        }
        return userList;
    }


    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE users SET password = ?, role = ? WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getRole());
            statement.setString(3, user.getUsername());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
