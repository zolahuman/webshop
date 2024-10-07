package model.services;

import model.Item;
import model.dao.itemDAO;
import model.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class itemServices {
    private itemDAO itemDao = new itemDAO();

    // Get all items
    public List<ItemDTO> getAllItems() throws SQLException {
        List<Item> items = itemDao.getAllItems();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(convertToDTO(item));
        }
        return itemDTOs;
    }

    // Get item by ID
    public ItemDTO getItemById(int itemId) throws SQLException {
        Item item = itemDao.getItemById(itemId);
        return convertToDTO(item);
    }

    // Add a new item
    public void addItem(ItemDTO itemDTO) throws SQLException {
        Item item = convertToEntity(itemDTO);
        itemDao.addItem(item);
    }

    // Update an item
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        Item item = convertToEntity(itemDTO);
        return itemDao.updateItem(item);

    }

    // Convert Entity to DTO
    private ItemDTO convertToDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setPrice(item.getPrice());
        dto.setDescription(item.getDescription());
        dto.setCategory(item.getCategory());
        dto.setAmount(item.getAmount());
        return dto;
    }

    // Convert DTO to Entity
    private Item convertToEntity(ItemDTO dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setDescription(dto.getDescription());
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setCategory(dto.getCategory());
        item.setAmount(dto.getAmount());
        return item;
    }
}
