package model.dto;

public class ItemDTO {
    private String name;
    private String description;
    private String category;
    private int price;
    private int id;
    private int amount;

    public ItemDTO() {}

    public ItemDTO(String name, String description, String category, int price, int id,  int amount) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.id = id;
        this.amount = amount;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
