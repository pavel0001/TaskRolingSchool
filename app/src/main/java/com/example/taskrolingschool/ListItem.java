package com.example.taskrolingschool;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Entity
public class ListItem {

    @PrimaryKey
    public long id;
    public String name;
    public String breed;
    int age;
    ListItem(long id, String name, int age, String breed){
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
}
