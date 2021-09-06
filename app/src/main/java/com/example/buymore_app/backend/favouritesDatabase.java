package com.example.buymore_app.backend;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {favouriteItem.class,itemOrderEntity.class}, version = 2)
public abstract class favouritesDatabase extends RoomDatabase {


    private static  final String databaseName = "favouriteDatabase";
    public static favouritesDatabase db;

        public static synchronized favouritesDatabase getDb(Context context){
            if(db == null){
                db = Room.databaseBuilder(context, favouritesDatabase.class, databaseName )
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return db;
        }
    public abstract itemDao Item();
    public abstract orderItemDao order();
}
