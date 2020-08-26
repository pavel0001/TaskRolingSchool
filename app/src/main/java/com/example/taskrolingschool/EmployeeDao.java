package com.example.taskrolingschool;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM ListItem")
    List<ListItem> getAll();

    @Query("SELECT * FROM ListItem WHERE id = :id")
    ListItem getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListItem employee);

    @Update
    void update(ListItem employee);

    @Delete
    void delete(ListItem employee);

}
