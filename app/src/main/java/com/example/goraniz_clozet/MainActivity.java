package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView BottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 하단 메뉴 바 정의
        BottomNavigation = findViewById(R.id.BottomNavigation);
        BottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                BottomNavigate(item.getItemId());
                return true;
            }
        });
        BottomNavigation.setSelectedItemId((R.id.menu_feed));
    }

    // Bottom Navigation을 이용한 메뉴 이동 기능
    private void BottomNavigate(int id){

        // 클릭한 메뉴의 id 받아오기
        String tag = String.valueOf(id);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();

        if(currentFragment != null){
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if(fragment == null){
            // menu_feed 페이지로 이동
            if(id == R.id.menu_feed){
                fragment = new menu_feed();

                // 유저 아이디 받아오기
                Intent intent = getIntent();
                String userID = intent.getStringExtra("userID");

                // 유저 아이디 보내주기
                Intent send_intent = new Intent(MainActivity.this, FeedWrite_Activity.class);
                send_intent.putExtra("userID", userID);
                startActivity(send_intent);
            }
            // menu_styleSuggestion 페이지로 이동
            else if(id == R.id.menu_styleSuggestion){
                fragment = new menu_style_suggestion();
            }

            // menu_community 페이지로 이동
            //else if(id == R.id.menu_community){
            //    fragment = new menu_community();
            //}

            // menu_mallSuggestion 페이지로 이동
            else if(id == R.id.menu_mallSuggestion){
                fragment = new menu_mall_suggestion();
            }
            // menu_profile 페이지로 이동
            else{
                fragment = new menu_profile();
            }
            fragmentTransaction.add(R.id.main_frameLayout, fragment, tag);
        }
        // default로 설정된 기본 Fragment 보이기
        else{
            fragmentTransaction.show(fragment);
        }

        // fragment 변경
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();

    }

}