package com.food1.whateat.presentation.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.food1.whateat.R;
import com.food1.whateat.data.calendar.FoodCalendar;
import com.food1.whateat.data.calendar.FoodCalendarDAO;
import com.food1.whateat.db.FoodDatabase;
import com.food1.whateat.presentation.calendar.adapter.CalendarFoodItemAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    FoodCalendarDAO foodCalendarDAO;
    MaterialCalendarView calendarView;

    CalendarFoodItemAdapter calendarFoodItemAdapter;

    ArrayList<FoodCalendar> foodCalendars;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);
        View root = binding.getRoot();

        FoodDatabase database = FoodDatabase.getInstance(getContext());
        foodCalendarDAO = database.foodCalendarDAO();

        foodCalendars = new ArrayList<>();
        RecyclerView recyclerView = root.findViewById(R.id.rv_calendar_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        calendarFoodItemAdapter = new CalendarFoodItemAdapter(getContext(), foodCalendarDAO, foodCalendars);
        recyclerView.setAdapter(calendarFoodItemAdapter);

        calendarView = root.findViewById(R.id.cv_calendar);
        calendarView.setDateSelected(CalendarDay.today(), true);
        click(CalendarDay.today());

        calendarView.setOnDateChangedListener((widget, calendarDay, selected) -> {
            int year = calendarDay.getYear();
            int month = calendarDay.getMonth();
            int day = calendarDay.getDay();
            String dayOfMonth = day < 10 ? "0" + day : String.valueOf(day);
            List<FoodCalendar> foodCalendarByDate = foodCalendarDAO.getFoodCalendarByDate(String.valueOf(year), String.valueOf(month), dayOfMonth);
            foodCalendars.clear();
            foodCalendars.addAll(foodCalendarByDate);
            calendarFoodItemAdapter.notifyDataSetChanged();
        });


        return root;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            calendarView.setDateSelected(CalendarDay.today(), true);
            click(CalendarDay.today());
        }
    }

    private void click(CalendarDay calendarDay) {
        int year = calendarDay.getYear();
        int month = calendarDay.getMonth();
        int day = calendarDay.getDay();
        String dayOfMonth = day < 10 ? "0" + day : String.valueOf(day);
        List<FoodCalendar> foodCalendarByDate = foodCalendarDAO.getFoodCalendarByDate(String.valueOf(year), String.valueOf(month), dayOfMonth);
        foodCalendars.clear();
        foodCalendars.addAll(foodCalendarByDate);
        calendarFoodItemAdapter.notifyDataSetChanged();
    }
}
