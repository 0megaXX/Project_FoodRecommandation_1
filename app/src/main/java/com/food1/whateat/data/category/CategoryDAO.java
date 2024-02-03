package com.food1.whateat.data.category;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface CategoryDAO {

    @Insert
    void insert(CategoryVO categoryVO);

    @Insert
    void addCategoryWithFood(CategoryFoodCrossRef categoryFoodCrossRef);

    @Transaction
    @Query("SELECT * FROM food_categories WHERE name = :categoryName")
    CategoryWithFood getCategoryWithFood(String categoryName);
}