package model.dao;

import model.Item; // Import your Item class

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class itemDAO {

    private static final String DB_URL = "jdbc:sqlite:C:/Distributedlabs/webshop/databas/webshop.db";

    public void addItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (name, description, category, price, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getDescription());
                preparedStatement.setString(3, item.getCategory());
                preparedStatement.setInt(4, item.getPrice());
                preparedStatement.setInt(5, item.getAmount());
                preparedStatement.executeUpdate();
        }
    }

    public Item getItemById(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE id = ?";
        Item item = null;

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String category = resultSet.getString("category");
                    int price = resultSet.getInt("price");
                    int amount = resultSet.getInt("amount");
                    item = new Item(name, description, category, price, id, amount);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return item;
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String category = resultSet.getString("category");
                    int price = resultSet.getInt("price");
                    int id = resultSet.getInt("id");
                    int amount = resultSet.getInt("amount");
                    items.add(new Item(name, description, category, price, id, amount));
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }


    public boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET name = ?, description = ?, category = ?, price = ?, amount = ? WHERE id = ?";
        boolean succes=false;
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getDescription());
                preparedStatement.setString(3, item.getCategory());
                preparedStatement.setInt(4, item.getPrice());
                preparedStatement.setInt(5, item.getAmount());
                preparedStatement.setInt(6, item.getId());
                preparedStatement.executeUpdate();
                int rowsUpdated = preparedStatement.executeUpdate();


                if (rowsUpdated > 0) {
                    succes=true;
                } else {
                    succes=false;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return succes;
    }

    public void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
