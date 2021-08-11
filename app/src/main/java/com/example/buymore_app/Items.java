package com.example.buymore_app;

public class Items {

    private int id;
    private int quantity;
    private int imageUrl;
    private String itemName;
    private String Category;
    private String price;

    public Items(int id, int quantity, Integer imageUrl, String itemName, String category, String price) {
        this.id = id;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.itemName = itemName;
        Category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
