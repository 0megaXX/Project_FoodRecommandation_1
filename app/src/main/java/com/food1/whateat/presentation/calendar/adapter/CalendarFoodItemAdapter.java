package com.food1.whateat.presentation.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food1.whateat.R;
import com.food1.whateat.data.calendar.FoodCalendar;
import com.food1.whateat.data.calendar.FoodCalendarDAO;

import java.time.LocalDateTime;
import java.util.List;


public class CalendarFoodItemAdapter extends RecyclerView.Adapter<CalendarFoodItemAdapter.ViewHolder> {

    private final Context context;
    private final FoodCalendarDAO foodCalendarDAO;
    private final List<FoodCalendar> foodCalendars;

    public CalendarFoodItemAdapter(Context context, FoodCalendarDAO foodCalendarDAO, List<FoodCalendar> foodCalendars) {
        this.context = context;
        this.foodCalendarDAO = foodCalendarDAO;
        this.foodCalendars = foodCalendars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodCalendar foodCalendar = foodCalendars.get(position);
        LocalDateTime date = foodCalendar.getDate();

        holder.tvTime.setText(date.getHour() + " " + date.getMinute());
        holder.tvFoodName.setText(foodCalendar.getFoodName());
        holder.ivFoodDelete.setOnClickListener(v -> {
            foodCalendarDAO.delete(foodCalendar);
            foodCalendars.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, foodCalendars.size());
        });
    }

    @Override
    public int getItemCount() {
        return foodCalendars.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;
        TextView tvFoodName;
        ImageView ivFoodDelete;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvTime = view.findViewById(R.id.tv_item_calendar_food_time);
            tvFoodName = view.findViewById(R.id.tv_item_calendar_food_name);
            ivFoodDelete = view.findViewById(R.id.iv_item_calendar_food_delete);
        }
    }
}
