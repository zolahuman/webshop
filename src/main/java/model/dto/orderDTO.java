package model.dto;

import java.util.List;

public class orderDTO {
    private List<ItemDTO> items;
    private int orderId;
    private String orderBy;
    private String status;

    public orderDTO() {}

    public orderDTO(List<ItemDTO> items, int orderId, String orderBy, String status) {
        this.orderId = orderId;
        this.orderBy = orderBy;
        this.status = status;
        this.items = items;
    }


    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getOrderBy() { return orderBy; }
    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<ItemDTO> getItems() { return items; }
    public void setItems(List<ItemDTO> items) { this.items = items; }
}
