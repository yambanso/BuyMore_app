package com.example.buymore_app.backend;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ItemOrder" )
public class itemOrderEntity {

    @ColumnInfo(name = "OrderOwner")
    String OrderOwner;

    @ColumnInfo(name = "ItemOwner")
    String ItemOwner;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ItemID")
    String ItemID;

    @ColumnInfo(name = "ItemName")
    String ItemName;

    @ColumnInfo(name = "ItemPrice")
    int ItemPrice;

    @ColumnInfo(name = "orderOwnerContact")
    String orderOwnerContact;

    @NonNull
    public String getOrderOwner() {
        return OrderOwner;
    }

    public void setOrderOwner(@NonNull String orderOwner) {
        OrderOwner = orderOwner;
    }

    public String getItemOwner() {
        return ItemOwner;
    }

    public void setItemOwner(String itemOwner) {
        ItemOwner = itemOwner;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getOrderOwnerContact() {
        return orderOwnerContact;
    }

    public void setOrderOwnerContact(String orderOwnerContact) {
        this.orderOwnerContact = orderOwnerContact;
    }
}
