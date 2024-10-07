package model.services;

import model.Item;
import model.Order;
import model.dao.orderDAO;
import model.dto.ItemDTO;
import model.dto.orderDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderServices {
    private orderDAO orderDao = new orderDAO();

    // Place an order
    public void placeOrder(int orderId, String username, String[] itemIds, String[] amounts) throws SQLException {
        if (itemIds == null || amounts == null || itemIds.length != amounts.length) {
            throw new IllegalArgumentException("Item IDs and amounts must be non-null and of equal length");
        }
        orderDao.placeOrder(orderId, username, itemIds, amounts);

    }

    public List<orderDTO> getAllOrders() throws SQLException {
        List<Order> orders = orderDao.getAllOrders();
        List<orderDTO> ordersDTO = new ArrayList<>();
        for (Order order : orders){
            ordersDTO.add(convertToDTO(order));
        }

        return ordersDTO;
    }

    public void updateOrderStatus(int orderId, String newStatus) throws SQLException {
        orderDao.updateOrderStatus(orderId, newStatus);
    }

    private orderDTO convertToDTO(Order order) {
        orderDTO dto = new orderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderBy(order.getOrderBy());
        dto.setStatus(order.getStatus());

        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : order.getItems()) {
            ItemDTO itemDTO = convertItemToDTO(item);
            itemDTOs.add(itemDTO);
        }
        dto.setItems(itemDTOs);
        return dto;
    }

    private Order convertToEntity(orderDTO dto) {
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setOrderBy(dto.getOrderBy());
        order.setStatus(dto.getStatus());

        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : dto.getItems()) {
            Item item = convertItemDTOToEntity(itemDTO);
            items.add(item);
        }
        order.setItems(items);
        return order;
    }

    private Item convertItemDTOToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCategory(itemDTO.getCategory());
        item.setAmount(itemDTO.getAmount());

        return item;
    }

    private ItemDTO convertItemToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setCategory(item.getCategory());
        itemDTO.setAmount(item.getAmount());
        return itemDTO;
    }

    public int getNextOrderId() throws SQLException {
        return orderDao.getNextOrderId();
    }
}
