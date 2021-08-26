package com.example.buymore_app.backend;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


//using a singleton to create a database architecture
@Database(entities = {userEntity.class}, version = 2)
public abstract class appDatabase extends RoomDatabase {

    private static final String databaseName = "Buymore";
    private  static  appDatabase buymoreDatabase;

    public static synchronized appDatabase getBuymoreDatabase(Context context){
        if(buymoreDatabase == null){
            buymoreDatabase = Room.databaseBuilder(context,appDatabase.class, databaseName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return buymoreDatabase;
    }

    public abstract userDao UserDao();

}
