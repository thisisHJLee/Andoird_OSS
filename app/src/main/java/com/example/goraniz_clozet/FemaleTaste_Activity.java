package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FemaleTaste_Activity extends AppCompatActivity {

    private int MAX_NUM = 3;    // 최대 선택 개수
    private ImageButton button1_img, button2_img, button3_img, button4_img, button5_img, button6_img, button7_img, button8_img, button9_img;
    int num = 0;
    String first, second, third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_taste);

        button1_img = findViewById(R.id.button1_img);
        button1_img.setOnClickListener(onClickListener);
        button2_img = findViewById(R.id.button2_img);
        button2_img.setOnClickListener(onClickListener);
        button3_img = findViewById(R.id.button3_img);
        button3_img.setOnClickListener(onClickListener);
        button4_img = findViewById(R.id.button4_img);
        button4_img.setOnClickListener(onClickListener);
        button5_img = findViewById(R.id.button5_img);
        button5_img.setOnClickListener(onClickListener);
        button6_img = findViewById(R.id.button6_img);
        button6_img.setOnClickListener(onClickListener);
        button7_img = findViewById(R.id.button7_img);
        button7_img.setOnClickListener(onClickListener);
        button8_img = findViewById(R.id.button8_img);
        button8_img.setOnClickListener(onClickListener);
        button9_img = findViewById(R.id.button9_img);
        button9_img.setOnClickListener(onClickListener);

        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");

        Button btn_done = (Button) findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // 입력자 정보 및 취향 데이터 전송
                // 선택한 3가지 취향 보내기 추가 필요
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 통신상태의 성공 여부 반환함
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = (boolean) jsonObject.get("success");
                            // 회원등록에 성공한 경우
                            if (success) {
                                Toast.makeText(getApplicationContext(), "Registration Succeeded.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FemaleTaste_Activity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {        // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "Registration Failed.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // 서버로 volley를 이용해 요청을 함
                PersonalTaste_Request personalTaste_request = new PersonalTaste_Request(userID, first, second, third, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FemaleTaste_Activity.this);
                queue.add(personalTaste_request);

            }
        });

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button1_img:
                    num += 1;
                    if(num == 1){
                        first = "formal";
                    }
                    else if(num == 2){
                        second = "formal";
                    }
                    else if(num == 3){
                        third = "formal";
                    }
                    break;
                case R.id.button2_img:
                    num += 1;
                    if(num == 1){
                        first = "chic";
                    }
                    else if(num == 2){
                        second = "chic";
                    }
                    else if(num == 3){
                        third = "chic";
                    }
                    break;
                case R.id.button3_img:
                    num += 1;
                    if(num == 1){
                        first = "romantic";
                    }
                    else if(num == 2){
                        second = "romantic";
                    }
                    else if(num == 3){
                        third = "romantic";
                    }
                    break;
                case R.id.button4_img:
                    num += 1;
                    if(num == 1){
                        first = "sports";
                    }
                    else if(num == 2){
                        second = "sports";
                    }
                    else if(num == 3){
                        third = "sports";
                    }
                    break;
                case R.id.button5_img:
                    num += 1;
                    if(num == 1){
                        first = "business";
                    }
                    else if(num == 2){
                        second = "business";
                    }
                    else if(num == 3){
                        third = "business";
                    }
                    break;
                case R.id.button6_img:
                    num += 1;
                    if(num == 1){
                        first = "trip";
                    }
                    else if(num == 2){
                        second = "trip";
                    }
                    else if(num == 3){
                        third = "trip";
                    }
                    break;
                case R.id.button7_img:
                    num += 1;
                    if(num == 1){
                        first = "girlish";
                    }
                    else if(num == 2){
                        second = "girlish";
                    }
                    else if(num == 3){
                        third = "girlish";
                    }
                    break;
                case R.id.button8_img:
                    num += 1;
                    if(num == 1){
                        first = "casual";
                    }
                    else if(num == 2){
                        second = "casual";
                    }
                    else if(num == 3){
                        third = "casual";
                    }
                    break;
                case R.id.button9_img:
                    num += 1;
                    if(num == 1){
                        first = "street";
                    }
                    else if(num == 2){
                        second = "street";
                    }
                    else if(num == 3){
                        third = "street";
                    }
                    break;
            }
        }
    };

}