package com.food1.whateat.presentation.onboarding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.food1.whateat.R;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    
    //보여줄 이미지 설정
    int sliderAllImages[] = {R.drawable.page_1, R.drawable.page_2, R.drawable.page_3,R.drawable.page_4};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return sliderAllImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen,container,false);

        LinearLayout linearLayout = view.findViewById(R.id.slide_screen);
        linearLayout.setBackground(ContextCompat.getDrawable(context,sliderAllImages[position]));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
