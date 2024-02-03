package com.food1.whateat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.food1.whateat.presentation.main.MainActivity2;
import com.food1.whateat.presentation.onboarding.OnboardingActivity;

import java.io.File;

public class SplashActivity extends AppCompatActivity {
    //onboard를 첫실행 했을때만 표시하기 위해
    //run.dat로 판별 그래서 내부 저장소(캐시)에 run.dat 저장
    File fileCacheDir;//내부 저장소(캐시) 경로 저장을 위한 변수
    String fileName = "/run.dat";//판별 파일 이름
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        ImageView gif_image = (ImageView) findViewById(R.id.gif_image);
        Glide.with(this).load(R.drawable.splashgif).into(gif_image);
        fileCacheDir = getCacheDir();

        //내부저장소(캐시)폴더 안에 "run.dat"파일이 있는지 검사
        if(new File(fileCacheDir+fileName).exists()){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {//있다면 메인엑티비티로 이동
                    Intent intent = new Intent(SplashActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }, 1750);
        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {//없다면 온보딩표시
                    Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1750);
        }

    }
}