package com.food1.whateat.presentation.choice.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food1.whateat.R;
import com.food1.whateat.data.food.FoodImage;
import com.food1.whateat.data.food.FoodImages;
import com.food1.whateat.presentation.choice.ChoiceFoodModel;

import java.util.List;

public class ChoiceFoodAdapter extends RecyclerView.Adapter<ChoiceFoodAdapter.ViewHolder> {

    private final Context context;
    private final List<ChoiceFoodModel> models;

    public List<ChoiceFoodModel> getModels() {
        return models;
    }

    public ChoiceFoodAdapter(Context context, List<ChoiceFoodModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_choice_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChoiceFoodModel model = models.get(position);
        String name = model.getFood().getName();
        holder.tvChoice.setText(name);
        holder.checkBox.setOnClickListener(v -> {
            model.setSelected(!model.isSelected());
        });
        holder.checkBox.setChecked(model.isSelected());
        ImageView ivChoiceFood = holder.ivChoiceFood;
        ivChoiceFood.setClipToOutline(true);
        FoodImage foodImage = FoodImage.findByName(name);
        // 음식 이미지가 존재할때만 해당 View에 이미지 추가
        // View를 재사용 하므로 새롭게 찾는 음식 이미지가 없을경우 기존 이미지가 남음
        if (foodImage != null) {
            ivChoiceFood.setImageResource(foodImage.getId());
        } else {
            ivChoiceFood.setImageResource(R.drawable.rounded_corners);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChoice;
        CheckBox checkBox;
        ImageView ivChoiceFood;

        public ViewHolder(@NonNull View view)
        {
            super(view);
            tvChoice = view.findViewById(R.id.tv_choice_food);
            checkBox = view.findViewById(R.id.checkbox_choice_select);
            ivChoiceFood = view.findViewById(R.id.iv_choice_food);
        }
    }
}
