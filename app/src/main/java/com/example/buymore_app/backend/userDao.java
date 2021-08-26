package com.example.buymore_app.backend;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface userDao {
    @Insert
    void userRegister(userEntity Userentity);

    //CHECKING IF PHONE NUMBER IS ALREADY REGISTERED
    @Query("SELECT * FROM users WHERE phoneNumber=(:phoneNumber)")
    userEntity phoneCheck(String phoneNumber);

    //getting user details for log in
    @Query("SELECT * FROM users WHERE phoneNumber=(:phoneNumber) AND password=(:password)")
    userEntity login(String phoneNumber, String password);

}
