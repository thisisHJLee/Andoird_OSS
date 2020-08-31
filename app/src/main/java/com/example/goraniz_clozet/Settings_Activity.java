package com.example.goraniz_clozet;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Settings_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting);

        // 설정 옵션
        setupSettingsList();

        // 화살표 누르면 다시 프로필 화면으로 돌아감
        ImageView backArrow = (ImageView)findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupSettingsList() {
        ListView listView = (ListView)findViewById(R.id.lv_AccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add("프로필 수정");
        options.add("사용자 정보 수정");
        options.add("알림 설정");
        options.add("다크모드 전환");
        options.add("로그아웃");
        options.add("문제 신고 및 고객 센터");
        options.add("탈퇴하기");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // 데이터 저장
    }
}
