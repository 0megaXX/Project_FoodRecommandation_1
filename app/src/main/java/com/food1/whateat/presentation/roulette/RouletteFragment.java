package com.food1.whateat.presentation.roulette;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.food1.whateat.R;
import com.food1.whateat.data.food.FoodDAO;
import com.food1.whateat.data.food.FoodVO;
import com.food1.whateat.db.FoodDatabase;
import com.food1.whateat.presentation.main.MainActivity2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RouletteFragment extends Fragment {

    View generalBtn;
    View microwaveBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_roulette, null);

        generalBtn = root.findViewById(R.id.cl_roulette_general_btn);
        microwaveBtn = root.findViewById(R.id.cl_roulette_microwave_btn);
        generalBtn.setOnClickListener(v -> {
            if (generalBtn.getBackground() != null &&
                    generalBtn.getBackground().getConstantState() != null &&
                    generalBtn.getBackground().getConstantState().equals(
                            ContextCompat.getDrawable(getContext(), R.drawable.bg_coners_stoke_gray).getConstantState())) {
                generalBtn.setBackgroundResource(R.drawable.bg_coners_stoke);
                microwaveBtn.setBackgroundResource(R.drawable.bg_coners_stoke_gray);
            }
        });
        microwaveBtn.setOnClickListener(v -> {
            if (microwaveBtn.getBackground() != null &&
                    microwaveBtn.getBackground().getConstantState() != null &&
                    microwaveBtn.getBackground().getConstantState().equals(
                            ContextCompat.getDrawable(getContext(), R.drawable.bg_coners_stoke_gray).getConstantState())) {
                microwaveBtn.setBackgroundResource(R.drawable.bg_coners_stoke);
                generalBtn.setBackgroundResource(R.drawable.bg_coners_stoke_gray);
            }
        });


        root.findViewById(R.id.cl_roulette_btn).setOnClickListener(v -> {
            activityRoulette();
        });
        return root;
    }


    public void activityRoulette() {
        Intent rouletteIntent;
        if (generalBtn.getBackground() != null &&
                generalBtn.getBackground().getConstantState() != null &&
                generalBtn.getBackground().getConstantState().equals(
                        ContextCompat.getDrawable(getContext(), R.drawable.bg_coners_stoke).getConstantState())) {
            rouletteIntent = new Intent(getContext(), RouletteActivity.class);
        } else {
            rouletteIntent = new Intent(getContext(), RouletteActivity2.class);
        }

        FoodDatabase foodDatabase = FoodDatabase.getInstance(getContext());
        FoodDAO foodDAO = foodDatabase.foodDAO();
        List<FoodVO> foodsBySelected = foodDAO.findFoodsBySelected();
        if (foodsBySelected.size() < 2)
            Toast.makeText(getContext(), "음식을 두개이상 고르셔야 룰렛이 작동합니다. (현재 갯수) "
                    + foodsBySelected.size() + "개", Toast.LENGTH_LONG).show();
        else {
            List<String> foodList = foodsBySelected.stream()
                    .map(foodVO -> foodVO.getName())
                    .collect(Collectors.toList());
            rouletteIntent.putStringArrayListExtra("selectedFoodList", new ArrayList<>(foodList));
            startActivityForResult(rouletteIntent, 10000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10000)
        {
            if(!(("미정".equals(data.getStringExtra("Finish"))))) {
                String sendData = data.getStringExtra("Finish");
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog03, null);

                TextView goto_map_to_choice = dialogView.findViewById(R.id.goto_map_to_choice);
                if (goto_map_to_choice != null) {
                    goto_map_to_choice.setText("주변에 있는 '" + sendData + "' 가게를\n 확인하실래요?");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);
                builder.setCancelable(true);

                final AlertDialog question_to_goto_food_dilago = builder.create();

                Button yesBtn = dialogView.findViewById(R.id.yesBtn);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        question_to_goto_food_dilago.dismiss();
                        FragmentActivity activity = getActivity();
                        if (activity == null) {
                            return;
                        }
                        MainActivity2 mainActivity2 = (MainActivity2) activity;
                        mainActivity2.activityMap();
                    }
                });

                Button noBtn = dialogView.findViewById(R.id.noBtn);
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        question_to_goto_food_dilago.dismiss();
                    }
                });

                question_to_goto_food_dilago.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                question_to_goto_food_dilago.show();


            }

        }
    }
}
