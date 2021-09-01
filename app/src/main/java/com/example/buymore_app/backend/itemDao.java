package com.example.buymore_app.backend;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface itemDao {

    @Insert
    void itemInsert(favouriteItem item);

    //checking if item is in favourites table
    @Query("SELECT * FROM Favourites WHERE itemID =(:itemid)")
    favouriteItem checkItem(String itemid);

    //getting all favourite items for user
    @Query("SELECT * FROM Favourites WHERE ownerId =(:ownerid)")
    List<favouriteItem> getFavourites(String ownerid);

    //Deleting from favourites table
    @Delete
    void  ItemDelete(favouriteItem item);
}
