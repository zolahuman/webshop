package model.dao;

import model.dto.ItemDTO;
import model.dto.orderDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class orderDAO {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/zola_/Documents/GitHub/webshop/databas/webshop.db";


    public void placeOrder(int orderId, String username, String[] itemIds, String[] amounts) throws SQLException {
        String sqlOrderInsert = "INSERT INTO orders (orderid, itemid, amount, status, orderby) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlOrderInsert)) {
                for (int i = 0; i < itemIds.length; i++) {
                    int itemId = Integer.parseInt(itemIds[i]);
                    int amount = Integer.parseInt(amounts[i]);

                    if (!isStockAvailable(connection, itemId, amount)) {
                        connection.rollback();
                        throw new SQLException("Not enough stock for item ID: " + itemId);
                    }

                    preparedStatement.setInt(1, orderId);
                    preparedStatement.setInt(2, itemId);
                    preparedStatement.setInt(3, amount);
                    preparedStatement.setString(4, "Not Sent");
                    preparedStatement.setString(5, username);
                    preparedStatement.addBatch();

                    decrementStock(connection, itemId, amount);
                }

                preparedStatement.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Error placing the order: " + e.getMessage(), e);

        }
    }

    private boolean isStockAvailable(Connection connection, int itemId, int requestedAmount) throws SQLException {
        String sql = "SELECT amount FROM items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int availableStock = resultSet.getInt("amount");
                    return availableStock >= requestedAmount;
                }
            }
        }
        return false;
    }

    private void decrementStock(Connection connection, int itemId, int decrementAmount) throws SQLException {
        String sql = "UPDATE items SET amount = amount - ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, decrementAmount);
            statement.setInt(2, itemId);
            statement.executeUpdate();
        }
    }

    public int getNextOrderId() throws SQLException {
        String sql = "SELECT MAX(orderid) FROM orders";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            } else {
                return 1;
            }
        }
    }

    public List<orderDTO> getAllOrders() throws SQLException {
        List<orderDTO> orders = new ArrayList<>();
        String sql = "SELECT o.orderid, o.orderby, o.status, o.itemid, o.amount, i.name, i.price, i.category " +
                "FROM orders o " +
                "JOIN items i ON o.itemid = i.id " +
                "ORDER BY o.orderid";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            orderDTO currentOrder = null;

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderid");
                String orderBy = resultSet.getString("orderby");
                String status = resultSet.getString("status");

                if (currentOrder == null || currentOrder.getOrderId() != orderId) {
                    if (currentOrder != null) {
                        orders.add(currentOrder);
                    }
                    currentOrder = new orderDTO(new ArrayList<>() ,orderId, orderBy, status);
                }


                int itemId = resultSet.getInt("itemid");
                String itemName = resultSet.getString("name");
                int itemPrice = resultSet.getInt("price");
                String itemCategory = resultSet.getString("category");
                int itemAmount = resultSet.getInt("amount");

                ItemDTO item = new ItemDTO(itemName,"", itemCategory, itemPrice, itemId,itemAmount);
                List<ItemDTO> itemsCpy = currentOrder.getItems();
                itemsCpy.add(item);
                currentOrder.setItems(itemsCpy);
            }

            if (currentOrder != null) {
                orders.add(currentOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving orders: " + e.getMessage());
        }

        return orders;
    }

    public void updateOrderStatus(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE orderid = ?";

        if (!newStatus.equalsIgnoreCase("not sent")){
            newStatus="not sent";
        }else {
            newStatus="sent";
        }


        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating order status failed, no rows affected.");
            }
        }
    }


}

