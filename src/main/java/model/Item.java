package model;

public class Item {
    private String name,description,category;
    private int price,id,amount;

    public Item(String name, String description, String category, int price, int id, int amount) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.id = id;
        this.amount = amount;
    }


    public Item(String name, String description, String category, int price, int id) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.id = id;
        this.amount = 0;
    }

    public Item(String name, String category, int price, int id, int amount) {
        this.name = name;
        this.description = "";
        this.category = category;
        this.price = price;
        this.id = id;
        this.amount = amount;
    }

    public Item() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void incrementAmount() {
        this.amount++;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
