package com.example.buymore_app.backend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users",indices = @Index(value = {"phoneNumber"},unique = true))
public class userEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "phoneNumber")
    String phoneNumber;

    @ColumnInfo(name = "address")
    String address;

    @ColumnInfo(name = "password")
    String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
