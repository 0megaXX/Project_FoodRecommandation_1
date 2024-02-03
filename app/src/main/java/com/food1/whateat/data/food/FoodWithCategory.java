package com.food1.whateat.data.food;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.food1.whateat.data.category.CategoryVO;
import com.food1.whateat.data.category.CategoryFoodCrossRef;

import java.util.List;

public class FoodWithCategory {
    @Embedded
    private final FoodVO foodVO;
    @Relation(
            parentColumn = "food_id",
            entityColumn = "category_id",
            associateBy = @Junction(CategoryFoodCrossRef.class)
    )
    private final List<CategoryVO> categories;

    public FoodWithCategory(FoodVO foodVO, List<CategoryVO> categories) {
        this.foodVO = foodVO;
        this.categories = categories;
    }

    public FoodVO getFood() {
        return foodVO;
    }

    public List<CategoryVO> getCategories() {
        return categories;
    }
}
