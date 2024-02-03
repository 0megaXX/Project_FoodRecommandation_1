package com.food1.whateat.data.food;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    public static FoodManager INSTANCE;
    private final DefaultFoodRepository defaultFoodRepository = new DefaultFoodRepository();
    private String selectedFood = null;

    public FoodManager() {
        FoodImages.values();
    }

    public DefaultFoodRepository getDefaultFoodRepository() {
        return defaultFoodRepository;
    }

    public static FoodManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FoodManager();
        }
        return INSTANCE;
    }

    public void setSelectedFood(String selectedFood) {
        this.selectedFood = selectedFood;
    }

    public String getSelectedFood() {
        return selectedFood;
    }
}
