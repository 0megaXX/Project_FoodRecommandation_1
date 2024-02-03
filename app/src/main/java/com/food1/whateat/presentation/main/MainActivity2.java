package com.food1.whateat.presentation.main;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.food1.whateat.R;
import com.food1.whateat.data.food.FoodDAO;
import com.food1.whateat.data.food.FoodManager;
import com.food1.whateat.data.food.FoodVO;
import com.food1.whateat.db.FoodDatabase;
import com.food1.whateat.presentation.add_food.FoodListFragment;
import com.food1.whateat.presentation.calendar.CalendarFragment;
import com.food1.whateat.presentation.home.HomeFragment;
import com.food1.whateat.presentation.onboarding.OnboardingActivity;
import com.food1.whateat.presentation.roulette.RouletteActivity;
import com.food1.whateat.presentation.roulette.RouletteActivity2;
import com.food1.whateat.presentation.roulette.RouletteFragment;
import com.food1.whateat.show_restaurant_kakaoMap;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MainActivity2 extends AppCompatActivity {

    FoodDatabase foodDatabase;

    Dialog dialog01;

    private static final int GPS_ENABLE_REQUEST_CODE = 1;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    String send_data;
    boolean check_result = true;
    Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
        setContentView(R.layout.activity_main2);
        foodDatabase = FoodDatabase.getInstance(this);
        dialog01 = new Dialog(MainActivity2.this);       // Dialog 초기화
        dialog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog01.setContentView(R.layout.dialog01);
        int fcvMain = R.id.fcv_main;
        HomeFragment homeFragment = new HomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setReorderingAllowed(true)
                .add(fcvMain, homeFragment, "home")
                .commit();
        currentFragment = homeFragment;

        NavigationBarView navigationBarView = findViewById(R.id.bnv_main);
        navigationBarView.setOnItemSelectedListener(item -> {

            FragmentManager listenerFragmentManager = getSupportFragmentManager();
            FragmentTransaction listenerFragmentTransaction = fragmentManager.beginTransaction();

            Fragment switchFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                Fragment fragment = listenerFragmentManager.findFragmentByTag("home");
                if (fragment == null) {
                    fragment = new HomeFragment();
                    listenerFragmentTransaction.add(fcvMain, fragment, "home");
                }
                switchFragment = fragment;
            } else if (itemId == R.id.menu_food_list) {
                Fragment fragment = listenerFragmentManager.findFragmentByTag("food_list");
                if (fragment == null) {
                    fragment = new FoodListFragment();
                    listenerFragmentTransaction.add(fcvMain, fragment, "food_list");
                }
                switchFragment = fragment;
            } else if (itemId == R.id.menu_roulette) {
                Fragment fragment = listenerFragmentManager.findFragmentByTag("roulette");
                if (fragment == null) {
                    fragment = new RouletteFragment();
                    listenerFragmentTransaction.add(fcvMain, fragment, "roulette");
                }
                switchFragment = fragment;
            } else if (itemId == R.id.menu_map) {
                activityMap();
                return false;
            } else if (itemId == R.id.menu_calendar) {
                Fragment fragment = listenerFragmentManager.findFragmentByTag("calendar");
                if (fragment == null) {
                    fragment = new CalendarFragment();
                    listenerFragmentTransaction.add(fcvMain, fragment, "calendar");
                }
                switchFragment = fragment;
            }
            if (switchFragment != null) {
                listenerFragmentManager.getFragments().forEach(fragment -> {
                    listenerFragmentTransaction.hide(fragment);
                });
                currentFragment = switchFragment;
                listenerFragmentTransaction.show(switchFragment);
                listenerFragmentTransaction.commit();
                return true;
            }
            return false;
        });
    }

    public void actionHomeFragment(Consumer<HomeFragment> consumer) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("home");
        if (fragment == null) {
            return;
        }
        if (fragment.getTag() != null) {
            if (fragment.getTag().equals("home")) {
                HomeFragment homeFragment = ((HomeFragment) fragment);
                consumer.accept(homeFragment);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        actionHomeFragment(homeFragment -> {
            homeFragment.updateSelectFood();
        });


        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkPermission();
                        return;
                    }
                }
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int PermRequestCode, @NonNull String[] permission, @NonNull int[] grandResults) {
        super.onRequestPermissionsResult(PermRequestCode, permission, grandResults);

        if (PermRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {
            // 요청 코드 PERMISSIONS_REQUEST_CODE 권한이 정상적으로 부여되었는지 확인
            // boolean check_result = true;

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            // 115~131 권한 설정(check_result의 값에 따라 선택)
            if (check_result) {
                //거부가 아니라면 위치를 설정할 수 있음
            } else {
                // 만얀 권한이 거부가 된다면 2가지 경우로 설명해줌.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(MainActivity2.this, "위치 접근 권한이 없습니다.\n설정(앱 정보)에서 확인해주세요.", Toast.LENGTH_LONG).show();
                    // finish();
                } else {
                    Toast.makeText(MainActivity2.this, "위치 접근 권한이 없습니다.\n설정(앱 정보)에서 확인해주세요.", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    void checkPermission() {

        //권한 확인 단계
        // FINE_LOCATION, COARSE_LOCATION이 Manifest에 들어가 있는지 확인
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        // 권한이 만약 있다면 위치값을 가져올 수 있음.
        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

        } else {  // 권한 요청을 거부하였다면 다시 권한 요구

            // 사용자가 위치 접근 권한 거부를 했다면
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity2.this, REQUIRED_PERMISSIONS[0])) {

                // 다시한번 권한을 요청함.
                ActivityCompat.requestPermissions(MainActivity2.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 거부한적이 없다면 권한을 다시 바로 요청함.
                ActivityCompat.requestPermissions(MainActivity2.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    public boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void activityMap() {
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
            Toast.makeText(MainActivity2.this, "위치 접근 권한이 없습니다.\n설정(앱 정보)에서 확인해주세요.", Toast.LENGTH_LONG).show();
        }

        //데이터는 되지만 음식을 고르지 못했다면 못가게 막음 -->그냥 지도로 이동하게
        //여기 수정 필요. 체크박스 켜져 있는 타입의 가게만 나오게끔.

        actionHomeFragment(homeFragment -> {
            String selectedFood = FoodManager.getInstance().getSelectedFood();
            if (selectedFood != null) {
                Intent goMap = new Intent(this, show_restaurant_kakaoMap.class);
                goMap.putExtra("selected",true);
                goMap.putExtra("Finish", selectedFood);
                startActivity(goMap);
                return;
            }
            ArrayList<String> selectedFoods = homeFragment.getSelectedCheckBox();
            if(selectedFoods.isEmpty()) {
                Toast.makeText(MainActivity2.this, "카테고리 1개 이상 선택해주세요.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, show_restaurant_kakaoMap.class);
                intent.putExtra("selected",false);
                intent.putExtra("check_food_list", selectedFoods);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        showDialog01();
    }

    public void showDialog01() {

        dialog01.show(); // 다이얼로그 띄우기
        dialog01.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button noBtn = dialog01.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                dialog01.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        dialog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                finish();           // 앱 종료
            }
        });
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