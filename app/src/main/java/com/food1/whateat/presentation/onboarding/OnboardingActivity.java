package com.food1.whateat.presentation.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.food1.whateat.R;
import com.food1.whateat.presentation.main.MainActivity2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OnboardingActivity extends AppCompatActivity {
    ViewPager slideViewPager;
    LinearLayout dotIndicator;
    ViewPagerAdapter viewPagerAdapter;
    TextView[] dots;
    Button backButton, nextButton;

    //run.dat 파일을 저장하기 위한 변수
    FileOutputStream fos;
    //내부 저장소(캐시) 경로를 저장하는 변수
    File cachePath;
    //온보딩에서 .으로 표시되는 부분을 표시하기 위한 폰트 변수
    Typeface typeface;

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**페이지가 확정 되었을 때
         * position = 페이지 index **/
        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);
            if (position > 0){
                backButton.setVisibility(View.VISIBLE);
            } else {
                backButton.setVisibility(View.INVISIBLE);
            }
            if (position == 3){
                nextButton.setText("Finish");

            }else{
                nextButton.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        /**폰트를 사용하기 위한 AssetManager 선언 **/
        AssetManager am = getAssets();
        typeface = Typeface.createFromAsset(am,"unifont_otf.otf");
        cachePath = getCacheDir();//내부저장소(캐시)경로 저장

        backButton = findViewById(R.id.backBtn);
        nextButton = findViewById(R.id.nextBtn);

        //Back 버튼 눌렀을때
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) > 0) {
                    //전페이지 이동
                    slideViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });
        //Next 버튼 눌렀을 때
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) < 3) {
                    //다음 페이지 이동
                    slideViewPager.setCurrentItem(getItem(1), true);
                } else {
                    //finish 버튼을 눌렀을 때 내부저장소(캐시)에 "run.dat"파일 저장
                    try {
                        fos = new FileOutputStream(cachePath + "/run.dat");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    //메인 엑티비티로 이동
                    Intent intent = new Intent(OnboardingActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        slideViewPager = findViewById(R.id.slideViewPager);
        dotIndicator = findViewById(R.id.dotIndicator);

        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);
        setDotIndicator(0);
        slideViewPager.addOnPageChangeListener(viewPagerListener);
    }
    //인티케이터 부분
    public void setDotIndicator(int position){
        dots = new TextView[4];
        dotIndicator.removeAllViews();
        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setTypeface(typeface);
            dots[i].setText(Html.fromHtml("&#0183",Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(50);
            dots[i].setTextColor(Color.parseColor("#808080"));
            dotIndicator.addView(dots[i]);
        }
        dots[position].setTextColor(Color.parseColor("#FFA500"));
    }

    private  int getItem(int i){
        return slideViewPager.getCurrentItem() + i;
    }
}