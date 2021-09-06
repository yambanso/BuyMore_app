package com.example.buymore_app.backend;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface orderItemDao {

    @Insert
    void orderItemInsert(itemOrderEntity order);

    //getting all ordered items
    @Query("SELECT * FROM ItemOrder WHERE OrderOwner = (:ownerId)")
    List<itemOrderEntity> getOrderItems(String ownerId);

    //check if item exist
    @Query("SELECT * FROM ItemOrder WHERE ItemID = (:itemid)")
        itemOrderEntity checkInCart(String itemid);

    //deleting an item
    @Delete
    void itemDelete(itemOrderEntity order);
}
