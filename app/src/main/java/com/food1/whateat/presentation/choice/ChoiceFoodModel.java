package com.food1.whateat.presentation.choice;

import com.food1.whateat.data.food.Food;

public class ChoiceFoodModel {

    private final Food food;
    private boolean selected = false;

    public ChoiceFoodModel(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
