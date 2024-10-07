package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;
    private int orderId;
    private String orderBy,status;

    public Order(List<Item> items, int orderId, String orderby, String status) {
        this.items = items;
        this.orderId = orderId;
        this.orderBy = orderby;
        this.status = status;
    }

    public Order(int orderId, String orderBy, String status) {
        this.orderId = orderId;
        this.orderBy = orderBy;
        this.status = status;
        this.items = new ArrayList<>();
    }

    public Order() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

}
