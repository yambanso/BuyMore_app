package com.example.buymore_app;

import android.net.Uri;

public class Items {

    private String ownerId;
    private int quantity;
    private int imageUrl;
    private String itemName;
    private String Category;
    private int price;
    private String description;
    private String  uri;


    public Items() {
    }

    public Items(String ownerId,String uri, int quantity, String itemName, String category, int price, String description) {
        this.ownerId = ownerId;
        this.quantity = quantity;
        this.itemName = itemName;
        Category = category;
        this.price = price;
        this.uri =uri;
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
