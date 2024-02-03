package com.food1.whateat.presentation.choice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.food1.whateat.R;
import com.food1.whateat.data.category.Categories;
import com.food1.whateat.data.category.Category;
import com.food1.whateat.data.category.CategoryVO;
import com.food1.whateat.data.food.DefaultFoodRepository;
import com.food1.whateat.data.food.Food;
import com.food1.whateat.data.food.FoodDAO;
import com.food1.whateat.data.food.FoodManager;
import com.food1.whateat.data.food.FoodVO;
import com.food1.whateat.db.FoodDatabase;
import com.food1.whateat.presentation.choice.adapter.ChoiceFoodAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChoiceFoodActivity extends AppCompatActivity {

    ChoiceFoodAdapter choiceFoodAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        MaterialToolbar tbChoiceFood = findViewById(R.id.tb_choice_food);
        tbChoiceFood.setNavigationOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        FoodDatabase foodDatabase = FoodDatabase.getInstance(this);
        FoodDAO foodDAO = foodDatabase.foodDAO();
        List<FoodVO> foodsBySelected = foodDAO.findFoodsBySelected();

        Intent intent = getIntent();
        Category category = Categories.findByName(intent.getStringExtra("category"));
        DefaultFoodRepository defaultFoodRepository = FoodManager.getInstance().getDefaultFoodRepository();
        List<Food> foods = defaultFoodRepository.getFoodsByCategory(category);

        RecyclerView recyclerView = findViewById(R.id.rv_choice_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ChoiceFoodModel> models = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            ChoiceFoodModel model = new ChoiceFoodModel(food);
            for (FoodVO foodVO : foodsBySelected) {
                if (food.getName().equals(foodVO.getName())) {
                    model.setSelected(true);
                }
            }
            models.add(model);
        }

        choiceFoodAdapter = new ChoiceFoodAdapter(this, models);
        recyclerView.setAdapter(choiceFoodAdapter);

    }

    public void on_Click_sub(View v){
        Intent intent = new Intent();

        List<ChoiceFoodModel> models = choiceFoodAdapter.getModels();
        int itemCount = choiceFoodAdapter.getItemCount();
        String[] selectedFoods = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            ChoiceFoodModel model = models.get(i);
            Food food = model.getFood();
            if (model.isSelected()) {
                selectedFoods[i] = "T|" + food.getName();
            } else {
                selectedFoods[i] = "F|" + food.getName();
            }
        }
        intent.putExtra("selected", selectedFoods);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
