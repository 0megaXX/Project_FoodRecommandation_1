package com.food1.whateat.presentation.question;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.food1.whateat.R;
import com.food1.whateat.presentation.question.FoodData;
import com.food1.whateat.show_restaurant_kakaoMap;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FoodQuestionActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Button buttonYes, buttonNo, buttonStart;
    private Map<String, List<String>> foodMap;
    private List<String> allValues; //모든 음식들의 특성(중복 허용)
    private Random random;
    String randomValue;
    int count =6;
    RelativeLayout relativeLayout1;
    RelativeLayout relativeLayout2;
    Button Restartbtn;
    String Result;
    String selectedFood;
    boolean check_result = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_question);

        MaterialToolbar tbChoiceFood = findViewById(R.id.tb_food_question);
        tbChoiceFood.setNavigationOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        textViewResult = findViewById(R.id.textViewResult);
        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);
        buttonStart = findViewById(R.id.buttonStart);
        foodMap = new HashMap<>();  // 음식 저장할 map
        allValues = new ArrayList<>();
        random = new Random();
        relativeLayout1=findViewById(R.id.relative1);
        relativeLayout2=findViewById(R.id.relative2);
        Restartbtn=findViewById(R.id.buttonReStart);


        initializeFoodMap();
        updateAllValues();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestion();
                buttonStart.setVisibility(View.GONE); // 시작 버튼 숨기기
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.VISIBLE);
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSelection(true);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSelection(false);
            }
        });

        Restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reset();


            }
        });
    }



    private void initializeFoodMap() {

        foodMap= FoodData.getFoodMap();
        //foodMap에 음식 정보 불러옴
    }

    private void updateAllValues()  //food맵에 있는 속성들을  allvalue에 갱신시키는 함수
    {
        allValues.clear();
        for (Map.Entry<String, List<String>> entry : foodMap.entrySet()) {
            allValues.addAll(entry.getValue());
        }
    }



    private void askQuestion()
    {//allValues.isEmpty() ||
        count--;
        if (count == 0 || foodMap.size() == 1)
        {
                Set<String> keySet = foodMap.keySet();
                List<String> keys = new ArrayList<>(keySet);
              //  String selectedFood;

                if (foodMap.size() != 1 && count == 0) {
                    int randomIndex = random.nextInt(keys.size());
                    selectedFood = keys.get(randomIndex);
                } else {
                    selectedFood = keys.iterator().next();
                }

                textViewResult.setText("음식이 결정되었습니다 : " + selectedFood);
                Result = selectedFood;
                Restartbtn.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.GONE);
                relativeLayout2.setVisibility(View.GONE);



                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog03, null);

                TextView goto_map_to_choice = dialogView.findViewById(R.id.goto_map_to_choice);
                if (goto_map_to_choice != null) {
                    goto_map_to_choice.setText("주변에 있는 '" + selectedFood + "' 가게를\n 확인하실래요?");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(dialogView);
                builder.setCancelable(true);

                final AlertDialog question_to_goto_food_dilago = builder.create();

                Button yesBtn = dialogView.findViewById(R.id.yesBtn);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        question_to_goto_food_dilago.dismiss();

                        goto_map();
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

                return;
        }

        //아니라면

        int randomIndex = random.nextInt(allValues.size()); //특성의 범위내 랜덤 숫자 결정
        randomValue = allValues.get(randomIndex);    //랜덤 숫자의 위치에 맞는 특성 선택

        textViewResult.setText("(" + randomValue + ")의 음식을 원하시나요?");   //물어봄
    }



    private void processSelection(boolean select)  //버튼 누르면 불린에 따라 선택지 달라짐.
    {
        // String currentQuestion = textViewResult.getText().toString();
        // View mainLayout = findViewById(R.id.textViewResult); // mainLayoutId를 실제 레이아웃 ID로 대체
        //  flipAnimation(mainLayout,mainLayout,select);


        String selectedTrait =  randomValue ;  //특성 추출

        Map<String, List<String>> tempMap = new HashMap<>(); //temp맵 생성

        for (Map.Entry<String, List<String>> entry : foodMap.entrySet())  //foodmap을 순회
        {
            List<String> traits = entry.getValue(); //foodmap을 요소마다의 값을 저장하는 list(n번째 map의 value를 전부 저장하는곳임.)
            boolean contains = traits.contains(selectedTrait); //traits에 특성이 포함되어있는지의 불린

            if (select && contains) //select가 true (즉 yes버튼 을 눌렀고) contains가 true (즉 해당 특성이 순회하는 음식에 있다면)
            {
                traits.remove(selectedTrait);
                tempMap.put(entry.getKey(), traits);          //그 특성을 제거하고 temp에 저장
            }

            else if (!select && !contains) //y눌렀고 해당 특성이 없다면
            {
                tempMap.put(entry.getKey(), new ArrayList<>(traits));   //그것을 temp에 저장
            }
        }

        //문제가 하나 있음 만약 밸류가 동일한 짜장과 간짜장 두개뿐 존재할 경우, 내가 밸류값인 '짠맛'이란 키워드에 boolean을 false할 경우 temo에는 아무것도 남지않고 true를 할 경우에도 결국 키워드만 줄어들 뿐, 밸류는 같음

        //Toast.makeText(getApplicationContext(), String.valueOf(tempMap.size()), Toast.LENGTH_LONG).show();
        foodMap = tempMap;  //temp를 foodmap으로 교체
        updateAllValues();
        if (foodMap.isEmpty()) {

            textViewResult.setText("죄송합니다.\n 음식이 원하시는 음식을 발견하지 못했습니다. " );
            textViewResult.setGravity(Gravity.CENTER);
            Restartbtn.setVisibility(View.VISIBLE);
            relativeLayout1.setVisibility(View.GONE);
            relativeLayout2.setVisibility(View.GONE);
            return;
        }


        askQuestion(); // 다음 질문을 위해 askQuestion 호출
    }


    void reset()
    {

        // 텍스트뷰 초기화
        textViewResult.setText("");
        textViewResult.setGravity(Gravity.NO_GRAVITY);

        // 버튼 가시성 초기화
        selectedFood=" ";
        buttonStart.setVisibility(View.VISIBLE);
        Restartbtn.setVisibility(View.GONE);
        relativeLayout1.setVisibility(View.GONE);
        relativeLayout2.setVisibility(View.GONE);
        textViewResult.setText("음식을 골라주세요");
        // 데이터 모델 초기화
        initializeFoodMap();
        updateAllValues();
        count = 6; // count를 초기값으로 재설정

    }









    public void goto_map() {
        goto_map(null);
    }


    public void goto_map(View v)
    {

        //데이터 없으면 못들어가게 막음
        if(!(isConnected(this)))
        {
            showToast(this, "네트워크를 연결 해주세요");

        }


        else if (!check_result) {
            // 만얀 권한이 거부가 된다면 2가지 경우로 설명해줌.

            Toast.makeText(FoodQuestionActivity.this, "위치 접근 권한이 없습니다.\n설정(앱 정보)에서 확인해주세요.", Toast.LENGTH_LONG).show();

        }



        else {
            Intent goMap = new Intent(this, show_restaurant_kakaoMap.class);
            goMap.putExtra("selected",true);
            goMap.putExtra("Finish",  selectedFood);
            startActivity(goMap);
        }
    }




    public boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

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