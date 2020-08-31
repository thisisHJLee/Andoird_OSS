package com.example.goraniz_clozet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ImageTag_Activity extends AppCompatActivity {

    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_tag);

        Button btn_cancel = (Button) findViewById(R.id.cancel_button);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("tag", "아직 태그가 선택되지 않았습니다.");    // 태그 버튼 값
                setResult(RESULT_OK, intent);   // 전달할 최종 값 입력
                finish();   // 현재 액티비티 종료
            }
        });

        Button tag_casual = findViewById(R.id.tag_btn_casual);
        tag_casual.setOnClickListener(onClickListener);
        Button tag_street = findViewById(R.id.tag_btn_street);
        tag_street.setOnClickListener(onClickListener);
        Button tag_youth = findViewById(R.id.tag_btn_youth);
        tag_youth.setOnClickListener(onClickListener);
        Button tag_campus = findViewById(R.id.tag_btn_campus);
        tag_campus.setOnClickListener(onClickListener);
        Button tag_school = findViewById(R.id.tag_btn_school);
        tag_school.setOnClickListener(onClickListener);
        Button tag_sports = findViewById(R.id.tag_btn_sports);
        tag_sports.setOnClickListener(onClickListener);
        Button tag_business = findViewById(R.id.tag_btn_business);
        tag_business.setOnClickListener(onClickListener);
        Button tag_formal = findViewById(R.id.tag_btn_formal);
        tag_formal.setOnClickListener(onClickListener);
        Button tag_dandy = findViewById(R.id.tag_btn_dandy);
        tag_dandy.setOnClickListener(onClickListener);
        Button tag_trip = findViewById(R.id.tag_btn_trip);
        tag_trip.setOnClickListener(onClickListener);
        Button tag_date = findViewById(R.id.tag_btn_date);
        tag_date.setOnClickListener(onClickListener);
        Button tag_retro = findViewById(R.id.tag_btn_retro);
        tag_retro.setOnClickListener(onClickListener);
        Button tag_hiphop = findViewById(R.id.tag_btn_hiphop);
        tag_hiphop.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.tag_btn_casual:
                    tag = "casual";
                    break;
                case R.id.tag_btn_street:
                    tag = "street";
                    break;
                case R.id.tag_btn_youth:
                    tag = "youth";
                    break;
                case R.id.tag_btn_campus:
                    tag = "campus";
                    break;
                case R.id.tag_btn_school:
                    tag = "school";
                    break;
                case R.id.tag_btn_sports:
                    tag = "sports";
                    break;
                case R.id.tag_btn_business:
                    tag = "business";
                    break;
                case R.id.tag_btn_formal:
                    tag = "formal";
                    break;
                case R.id.tag_btn_dandy:
                    tag = "dandy";
                    break;
                case R.id.tag_btn_trip:
                    tag = "trip";
                    break;
                case R.id.tag_btn_date:
                    tag = "date";
                    break;
                case R.id.tag_btn_retro:
                    tag = "retro";
                    break;
                case R.id.tag_btn_hiphop:
                    tag = "hiphop";
                    break;
            }

            Intent intent = new Intent();
            intent.putExtra("tag", tag);    // 태그 버튼 값
            setResult(RESULT_OK, intent);   // 전달할 최종 값 입력
            finish();   // 현재 액티비티 종료

        }
    };

}