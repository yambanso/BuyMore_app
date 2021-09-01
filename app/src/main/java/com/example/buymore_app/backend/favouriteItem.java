package com.example.buymore_app.backend;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favourites" )

public class favouriteItem {

    @ColumnInfo(name = "ownerId")
     String ownerId;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "itemID")
    String itemID;

    @ColumnInfo(name = "jonObject")
        String jonObject;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getJonObject() {
        return jonObject;
    }

    public void setJonObject(String jonObject) {
        this.jonObject = jonObject;
    }
}

