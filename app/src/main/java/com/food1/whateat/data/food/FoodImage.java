package com.food1.whateat.data.food;

import java.util.HashMap;
import java.util.Map;

public class FoodImage {

    private static final Map<String, FoodImage> foodImages = new HashMap<>();

    private static void set(FoodImage foodImage) {
        foodImages.put(foodImage.getName(), foodImage);
    }

    public static FoodImage findByName(String name) {
        return foodImages.get(name);
    }

    private final String name;
    private final int id;

    public FoodImage(String name, int id) {
        this.name = name;
        this.id = id;
        set(this);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
