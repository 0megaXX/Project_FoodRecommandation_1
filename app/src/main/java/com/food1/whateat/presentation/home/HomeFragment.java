package com.food1.whateat.presentation.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.food1.whateat.R;
import com.food1.whateat.data.cached.AppDAO;
import com.food1.whateat.data.cached.AppData;
import com.food1.whateat.data.category.Categories;
import com.food1.whateat.data.category.Category;
import com.food1.whateat.data.food.DefaultFoodRepository;
import com.food1.whateat.data.food.Food;
import com.food1.whateat.data.food.FoodDAO;
import com.food1.whateat.data.food.FoodImage;
import com.food1.whateat.data.food.FoodImages;
import com.food1.whateat.data.food.FoodManager;
import com.food1.whateat.data.food.FoodVO;
import com.food1.whateat.db.FoodDatabase;
import com.food1.whateat.presentation.choice.ChoiceFoodActivity;
import com.food1.whateat.presentation.question.FoodQuestionActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {


    TextView selectedFoodTextView;
    private FoodDatabase foodDatabase;

    FoodDAO foodDAO;

    DefaultFoodRepository foodRepository;

    Map<String, Boolean> foodSelectMap;
    ViewDataBinding binding;
    View layoutKr, layoutAsian;
    CheckBox cbKo, cbCh, cbJp, cbWe, cbFast, cbAsian;

    public ArrayList<String> getSelectedCheckBox() {
        ArrayList<String> a = new ArrayList<>();
        if (cbKo.isChecked()) a.add("한식");
        if (cbCh.isChecked()) a.add("중식");
        if (cbJp.isChecked()) a.add("일식");
        if (cbWe.isChecked()) a.add("양식");
        if (cbFast.isChecked()) a.add("패스트푸드");
        if (cbAsian.isChecked()) a.add("아시안음식");
        return a;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View root = binding.getRoot();

        foodDatabase = FoodDatabase.getInstance(getContext());
        boolean image = true;
//        AppDAO appDAO = foodDatabase.appDAO();
//        AppData appData = appDAO.findAppData("home_food_image");
//        if (appData == null) {
//            appData = new AppData("home_food_image", LocalDateTime.now());
//            appDAO.insert(appData);
//        } else {
//            if (appData.getDate().getDayOfYear() != LocalDateTime.now().getDayOfYear()) {
//                appDAO.delete(appData);
//                appData = new AppData("home_food_image", LocalDateTime.now());
//                appDAO.insert(appData);
//            } else {
//                image = false;
//            }
//        }
        if (image) {
            List<FoodImage> randomFoodImages = FoodImages.getRandomFoodImages(3);
            for (int i = 0; i < randomFoodImages.size(); i++) {
                FoodImage foodImage = randomFoodImages.get(i);
                ImageView iv = null;
                if (i == 0) {
                    iv = root.findViewById(R.id.iv_main_1);
                } else if (i == 1) {
                    iv = root.findViewById(R.id.iv_main_2);
                } else if (i == 2) {
                    iv = root.findViewById(R.id.iv_main_3);
                    iv.setImageResource(foodImage.getId());
                }
                iv.setImageResource(foodImage.getId());
                iv.setClipToOutline(true);
            }
        }
        selectedFoodTextView = root.findViewById(R.id.tv_home_selected_food);
        updateSelectFood();
        layoutKr = root.findViewById(R.id.layout_food_category_korea);
        cbKo = layoutKr.findViewById(R.id.cb_food_category);

        layoutAsian = root.findViewById(R.id.layout_food_category_asian);
        cbAsian = layoutAsian.findViewById(R.id.cb_food_category);

        ImageView ivFoodQuestion = root.findViewById(R.id.iv_main_food_question);
        ivFoodQuestion.setClipToOutline(true);


        return root;
    }

    public void updateSelectFood() {
        FoodManager foodManager = FoodManager.getInstance();
        if (foodManager.getSelectedFood() != null) {
            selectedFoodTextView.setText("결정한 음식 : " + foodManager.getSelectedFood());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodDAO = foodDatabase.foodDAO();
        foodRepository = FoodManager.getInstance().getDefaultFoodRepository();

        List<FoodVO> foodsBySelected = foodDAO.findFoodsBySelected();
        foodSelectMap = new HashMap<>();
        foodsBySelected.forEach(foodVO -> {
            foodSelectMap.put(foodVO.getName(), true);
        });

        View root = binding.getRoot();

        root.findViewById(R.id.cl_main_food_question).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FoodQuestionActivity.class);
            startActivity(intent);
        });

        setCheckBox(cbKo, "koreaFood", foodSelectMap);
        layoutKr.setOnClickListener(v -> {
            clickChoiceActivity(layoutKr);
        });

        View layoutCh = root.findViewById(R.id.layout_food_category_china);
        cbCh = layoutCh.findViewById(R.id.cb_food_category);
        setCheckBox(cbCh, "chinaFood", foodSelectMap);
        layoutCh.setOnClickListener(v -> {
            clickChoiceActivity(layoutCh);
        });

        View layoutJp = root.findViewById(R.id.layout_food_category_japan);
        cbJp = layoutJp.findViewById(R.id.cb_food_category);
        setCheckBox(cbJp, "japanFood", foodSelectMap);
        layoutJp.setOnClickListener(v -> {
            clickChoiceActivity(layoutJp);
        });

        View layoutWest = root.findViewById(R.id.layout_food_category_west);
        cbWe = layoutWest.findViewById(R.id.cb_food_category);
        setCheckBox(cbWe, "westFood", foodSelectMap);
        layoutWest.setOnClickListener(v -> {
            clickChoiceActivity(layoutWest);
        });

        View layoutFast = root.findViewById(R.id.layout_food_category_fast);
        cbFast = layoutFast.findViewById(R.id.cb_food_category);
        setCheckBox(cbFast, "fastFood", foodSelectMap);
        layoutFast.setOnClickListener(v -> {
            clickChoiceActivity(layoutFast);
        });

        setCheckBox(cbAsian, "asianFood", foodSelectMap);
        layoutAsian.setOnClickListener(v -> {
            clickChoiceActivity(layoutAsian);
        });

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public void setCheckBoxState(String tag, Map<String, Boolean> foodSelectMap) {
        if (tag.equals("koreaFood")) {
            for (Food food : foodRepository.getFoodsByCategory(Categories.KOREA)) {
                if (foodSelectMap.containsKey(food.getName())) {
                    cbKo.setChecked(true);
                    return;
                }
            }
        } else if (tag.equals("chinaFood")) {
            for (Food food : foodRepository.getFoodsByCategory(Categories.CHINA)) {
                if (foodSelectMap.containsKey(food.getName())) {
                    cbCh.setChecked(true);
                    return;
                }
            }
        } else if (tag.equals("japanFood")) {
            for (Food food : foodRepository.getFoodsByCategory(Categories.JAPAN)) {
                if (foodSelectMap.containsKey(food.getName())) {
                    cbJp.setChecked(true);
                    return;
                }
            }
        } else if (tag.equals("westFood")) {
            for (Food food : foodRepository.getFoodsByCategory(Categories.WESTERN)) {
                if (foodSelectMap.containsKey(food.getName())) {
                    cbWe.setChecked(true);
                    return;
                }
            }
        } else if (tag.equals("fastFood")) {
            for (Food food : foodRepository.getFoodsByCategory(Categories.FAST)) {
                if (foodSelectMap.containsKey(food.getName())) {
                    cbFast.setChecked(true);
                    return;
                }
            }
        } else if (tag.equals("asianFood")) {
            for (Food food : foodRepository.getFoodsByCategory(Categories.ASIAN)) {
                if (foodSelectMap.containsKey(food.getName())) {
                    cbAsian.setChecked(true);
                    return;
                }
            }
        }
    }
    public void setCheckBox(CheckBox checkBox, String tag, Map<String, Boolean> foodSelectMap) {
        checkBox.setOnClickListener(v -> {
            if (tag.equals("koreaFood")) {
                selectCategoryFoods(Categories.KOREA, cbKo.isChecked());
            } else if (tag.equals("chinaFood")) {
                selectCategoryFoods(Categories.CHINA, cbCh.isChecked());
            } else if (tag.equals("japanFood")) {
                selectCategoryFoods(Categories.JAPAN, cbJp.isChecked());
            } else if (tag.equals("westFood")) {
                selectCategoryFoods(Categories.WESTERN, cbWe.isChecked());
            } else if (tag.equals("fastFood")) {
                selectCategoryFoods(Categories.FAST, cbFast.isChecked());
            } else if (tag.equals("asianFood")) {
                selectCategoryFoods(Categories.ASIAN, cbAsian.isChecked());
            }
        });
        setCheckBoxState(tag, foodSelectMap);
    }

    public void selectCategoryFoods(Category category, boolean select) {
        for (Food food : foodRepository.getFoodsByCategory(category)) {
            FoodVO foodVO = new FoodVO(food.getName());
            foodVO.setSelected(select);
            foodDAO.insert(foodVO);
        }
    }

    private static final int REQUEST_CODE = 100;
    public void clickChoiceActivity(View layout)
    {
        ImageView imageView = layout.findViewById(R.id.ib_food);
        int menuNum;
        Intent intent = new Intent(getContext(), ChoiceFoodActivity.class);
        String tag = (String) imageView.getTag();
        if (tag.equals("koreaFood")) {
            menuNum = 1;
            intent.putExtra("category", "한식");
        } else if (tag.equals("chinaFood")) {
            menuNum = 2;
            intent.putExtra("category", "중식");
        } else if (tag.equals("japanFood")) {
            menuNum = 3;
            intent.putExtra("category", "일식");
        } else if (tag.equals("westFood")) {
            menuNum = 4;
            intent.putExtra("category", "양식");
        } else if (tag.equals("fastFood")) {
            menuNum = 5;
            intent.putExtra("category", "패스트푸드");
        } else if (tag.equals("asianFood")) {
            menuNum = 6;
            intent.putExtra("category", "아시안");
        } else {
            menuNum = 0;
        }
        startActivityForResult(intent,REQUEST_CODE + menuNum);
    }

    public int selectFood(Intent data) {
        int nonNullCount = 0;
        if (data == null) {
            return 0;
        }
        String[] selecteds = data.getStringArrayExtra("selected");
        if (selecteds == null) {
            return 0;
        }
        for (String item : selecteds) {
            String selected = item.substring(0,1);
            String foodName = item.substring(2);
            if (selected.equals("T")) {
                FoodVO foodVO = foodDAO.findByFoodName(foodName);
                if (foodVO == null) {
                    FoodVO addFoodVO = new FoodVO(foodName);
                    addFoodVO.setSelected(true);
                    foodDAO.insert(addFoodVO);
                } else {
                    foodVO.setSelected(true);
                    foodDAO.update(foodVO);
                }
                nonNullCount++;
            } else {
                FoodVO foodVO = foodDAO.findByFoodName(foodName);
                if (foodVO != null) {
                    foodVO.setSelected(false);
                    foodDAO.update(foodVO);
                }
            }
        }
        return nonNullCount;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        if(requestCode == 101) {
            if(selectFood(data)<1) {
                cbKo.setChecked(false);
            }
            else {
                cbKo.setChecked(true);
                showToast("한식 결정!");
            }

        } else if(requestCode == 102) {
            if(selectFood(data)<1) {
                cbCh.setChecked(false);
            }
            else {
                cbCh.setChecked(true);
                showToast("중식 결정!");
            }
        } else if(requestCode == 103) {
            if(selectFood(data)<1) {
                cbJp.setChecked(false);
            }
            else {
                cbJp.setChecked(true);
                showToast("일식 결정!");
            }
        } else if(requestCode == 104) {
            if(selectFood(data)<1) {
                cbWe.setChecked(false);
            }
            else {
                cbWe.setChecked(true);
                showToast("양식 결정!");
            }
        } else if(requestCode == 105) {
            if(selectFood(data)<1) {
                cbFast.setChecked(false);
            }
            else {
                cbFast.setChecked(true);
                showToast("패스트푸드 결정!");
            }
        } else if(requestCode == 106) {
            if(selectFood(data)<1) {
                cbAsian.setChecked(false);
            }
            else {
                cbAsian.setChecked(true);
                showToast("아시안 결정!");
            }
        }
    }

    private static Toast sToast;
    public void showToast(String message) {
        if (sToast == null) {
            sToast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
        }
        sToast.show();
    }
}
