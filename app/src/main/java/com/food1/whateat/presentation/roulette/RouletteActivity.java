package com.food1.whateat.presentation.roulette;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bluehomestudio.luckywheel.WheelItem;
import com.food1.whateat.MainActivity;
import com.food1.whateat.R;
import com.food1.whateat.data.calendar.FoodCalendar;
import com.food1.whateat.data.calendar.FoodCalendarDAO;
import com.food1.whateat.data.food.FoodManager;
import com.food1.whateat.db.FoodDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteActivity extends AppCompatActivity {
    private SensorManager sm;
    //private Sensor mAccelerometer;
    TextView randomTextView;
    SoundPool soundPool;
    int soundPlay_start;
    int soundPlay_end;
    List<WheelItem> wheelItems;//룰렛에 들어갈 음식칸
    //String point;
    //String LastPoint=new String();
    String money;
    ArrayList <String> allMenu2 = new ArrayList<>();//모든 음식 배열
    private boolean isStopped = true;
    Dialog dilaog0;
    ImageButton start;
    int streamId;
    int streamId2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        soundPlay_start=soundPool.load(this, R.raw.roll_dice_start,0);
        soundPlay_end=soundPool.load(this, R.raw.roll_dice_end,0);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        start = findViewById(R.id.spin_btn);
        ImageButton Finish = findViewById(R.id.Ifinish);
        randomTextView = findViewById(R.id.select_food_text);


        // mAccelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        dilaog0 = new Dialog(RouletteActivity.this);       // Dialog 초기화
        dilaog0.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog0.setContentView(R.layout.dialog02);
        dilaog0.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // 다이얼로그가 취소될 때의 로직
                start.setBackgroundResource(R.drawable.start_btn);
            }
        });

        Intent it = getIntent();
        allMenu2 = it.getStringArrayListExtra("selectedFoodList");






        final Handler handler = new Handler();
        final int animationDuration = 120; // 애니메이션 지속 시간
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!isStopped) {
                    int randomIndex = new Random().nextInt(allMenu2.size());
                    String randomMenu = allMenu2.get(randomIndex);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            randomTextView.setText(randomMenu);
                            start.setBackgroundResource(R.drawable.action_btn);
                            // 회전 애니메이션
                            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(randomTextView, "rotationY", 0f, 360f);
                            rotateAnimator.setDuration(animationDuration);

                            // 크기 축소 애니메이션 (화면으로부터 멀어짐)
                            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(randomTextView, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(randomTextView, "scaleY", 1f, 0.5f);
                            scaleDownX.setDuration(animationDuration);
                            scaleDownY.setDuration(animationDuration);

                            // 크기 확대 애니메이션 (화면으로부터 가까워짐)
                            ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(randomTextView, "scaleX", 0.5f, 1f);
                            ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(randomTextView, "scaleY", 0.5f, 1f);
                            scaleUpX.setDuration(animationDuration);
                            scaleUpY.setDuration(animationDuration);

                            // 애니메이션 재생성
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.play(rotateAnimator).with(scaleDownX).with(scaleDownY).before(scaleUpX).before(scaleUpY);
                            animatorSet.start();
                        }
                    });
                }
                handler.postDelayed(this, 100);
            }
        };
        handler.post(runnable);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                streamId = soundPool.play(soundPlay_start, 1.0F, 1.0F,1, -1,0.7F); // 버튼누르면 무한으로 돌리는소리
                if (!isStopped) {
                    final String currentItem = randomTextView.getText().toString();
                    soundPool.stop(streamId);  //무한소리 끄기
                    streamId2 = soundPool.play(soundPlay_end, 1.0F, 1.0F,1, 0,0.7F);  //마지막소리는 한번만
                    ValueAnimator decelerateAnimator = ValueAnimator.ofFloat(0, 1);
                    decelerateAnimator.setInterpolator(new DecelerateInterpolator(2f));

                    decelerateAnimator.setDuration(2000);


                    decelerateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            // 여기서는 필요한 경우 애니메이션 상태를 업데이트합니다.
                        }
                    });
                    decelerateAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            isStopped = true;
                            randomTextView.setText(currentItem);
                            randomTextView.setTranslationY(0);
                            Log.d("Debug", "TextView Visibility: " + randomTextView.getVisibility());
                            Log.d("Debug", "TextView X: " + randomTextView.getTranslationX() + ", Y: " + randomTextView.getTranslationY());
                            Log.d("Debug", "TextView Width: " + randomTextView.getWidth() + ", Height: " + randomTextView.getHeight());
                            Log.d("Debug", "TextView Alpha: " + randomTextView.getAlpha());
                            dilaog0.show();
                            dilaog0.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            TextView showView = dilaog0.findViewById(R.id.will_you_choice);
                            Button noBtn = dilaog0.findViewById(R.id.noBtn);



                            showView.setText(currentItem + "(으)로 결정하시겠습니까?");

                            noBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (allMenu2.size() == 1)
                                        showToast(RouletteActivity.this, "더이상 지울수 없습니다.");
                                    else if (allMenu2.contains(currentItem)) {
                                        allMenu2.remove(currentItem);
                                        streamId = soundPool.play(soundPlay_start, 1.0F, 1.0F,1, -1,0.7F); // 버튼누르면 무한으로 돌리는소리
                                    }
                                    isStopped = false;
                                    dilaog0.dismiss();
                                }
                            });

                            dilaog0.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    isStopped = true;
                                    dilaog0.dismiss();
                                    money = currentItem;
                                    on_Click_sub(view);
                                }
                            });
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                        }
                    });

                    decelerateAnimator.start();
                } else {

                    isStopped = false;
                }
            }
        });



    }







    public void on_Click_sub(View v){
        if(money == null)
        {
            showToast(RouletteActivity.this,"메뉴를 결정하세요");
        }


        else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Finish", money);

            selectFood(money);

            setResult(RESULT_OK, intent);
            finish();
        }
    }//최종 결정된 LastPoint를 전달



    private void selectFood(String foodName) {
        FoodManager.getInstance().setSelectedFood(foodName);
        FoodDatabase foodDatabase = FoodDatabase.getInstance(this);
        FoodCalendarDAO foodCalendarDAO = foodDatabase.foodCalendarDAO();
        foodCalendarDAO.insert(new FoodCalendar(foodName, LocalDateTime.now()));
    }





    @Override
    public void onBackPressed(){
        soundPool.stop(streamId);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Finish", "미정");
        setResult(RESULT_OK, intent);
        finish();

    }//백스페이스로 돌아가면 intent에 아무값도 없어 오류가 나 공백을 넣음


    private static Toast sToast;
    public static void showToast(Context context, String message) {
        if (sToast == null) {
            sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
        }
        sToast.show();
    }



}











