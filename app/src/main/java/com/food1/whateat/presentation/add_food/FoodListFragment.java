package com.food1.whateat.presentation.add_food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.food1.whateat.R;
import com.food1.whateat.data.food.DefaultFoodRepository;
import com.food1.whateat.data.food.FoodDAO;
import com.food1.whateat.data.food.FoodManager;
import com.food1.whateat.data.food.FoodVO;
import com.food1.whateat.db.FoodDatabase;
import com.food1.whateat.presentation.add_food.adapter.FoodListAdapter;
import com.food1.whateat.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class FoodListFragment extends Fragment {


    private FoodDatabase foodDatabase;
    FoodDAO foodDAO;

    public FoodListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_custom_food, container, false);
        foodDatabase = FoodDatabase.getInstance(view.getContext());

        EditText foodText = view.findViewById(R.id.food_text);
        ImageButton addFoodBtn = view.findViewById(R.id.add_food_btn);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        foodDAO = foodDatabase.foodDAO();

        List<FoodVO> foodVOList = new ArrayList<>();
        DefaultFoodRepository defaultFoodRepository = FoodManager.getInstance().getDefaultFoodRepository();
        foodDAO.getAll().forEach(foodVO -> {
            if (!defaultFoodRepository.containFoodByName(foodVO.getName())) {
                foodVOList.add(foodVO);
            }
        });

        FoodListAdapter foodListAdapter = new FoodListAdapter(view.getContext(), foodVOList, foodDAO);
        recyclerView.setAdapter(foodListAdapter);
        addFoodBtn.setOnClickListener(v -> {
            String foodName = foodText.getText().toString().trim();
            if (foodName.equals("")) {
                ViewUtils.showToast(view.getContext(), "추가하실 음식을 입력해주세요.");
                return;
            }

            if (defaultFoodRepository.containFoodByName(foodName)) {
                ViewUtils.showToast(view.getContext(), "기본으로 등록 되어있는 음식 입니다.");
                return;
            }

            FoodVO foodVO = new FoodVO(foodName);
            foodVO.setSelected(true);
            long result = foodDAO.insert(foodVO);
            if (result == -1) {
                ViewUtils.showToast(view.getContext(), "중복 등록은 안됩니다.");
                return;
            }
            foodText.setText("");
            foodVOList.clear();
            foodVOList.addAll(filter());
            foodListAdapter.notifyDataSetChanged();

        });
        return view;
    }

    private List<FoodVO> filter() {
        List<FoodVO> foodVOList = new ArrayList<>();
        DefaultFoodRepository defaultFoodRepository = FoodManager.getInstance().getDefaultFoodRepository();
        foodDAO.getAll().forEach(foodVO -> {
            if (!defaultFoodRepository.containFoodByName(foodVO.getName())) {
                foodVOList.add(foodVO);
            }
        });
        return foodVOList;
    }


}
