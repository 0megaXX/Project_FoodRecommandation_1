package com.food1.whateat.data.category;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.food1.whateat.data.food.FoodVO;

import java.util.List;

public class CategoryWithFood {
    @Embedded
    private final CategoryVO categoryVO;
    @Relation(
            parentColumn = "category_id",
            entityColumn = "food_id",
            associateBy = @Junction(CategoryFoodCrossRef.class)
    )
    private final List<FoodVO> foodVOS;

    public CategoryWithFood(CategoryVO categoryVO, List<FoodVO> foodVOS) {
        this.categoryVO = categoryVO;
        this.foodVOS = foodVOS;
    }

    public CategoryVO getCategory() {
        return categoryVO;
    }

    public List<FoodVO> getFoods() {
        return foodVOS;
    }
}