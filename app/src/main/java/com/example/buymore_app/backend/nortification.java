package com.example.buymore_app.backend;

public class nortification {
    public String orderOwnerID;
    public String itemName;
    public String OrderContact;
    public int Price;

    public nortification(String orderOwnerID, String itemName, String orderContact, int price) {
        this.orderOwnerID = orderOwnerID;
        this.itemName = itemName;
        OrderContact = orderContact;
        Price = price;
    }
}
