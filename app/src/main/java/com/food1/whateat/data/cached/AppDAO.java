package com.food1.whateat.data.cached;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.food1.whateat.data.calendar.FoodCalendar;
import com.food1.whateat.data.food.FoodVO;

import java.util.List;

@Dao
public interface AppDAO {

    @Insert
    void insert(AppData appData);

    @Delete
    void delete(AppData appData);

    @Query("SELECT * FROM app_data WHERE id = :key")
    AppData findAppData(String key);
}
