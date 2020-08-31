package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Register_Activity extends AppCompatActivity {

    private TextInputEditText TextInputEditText_Email, TextInputEditText_UserID, TextInputEditText_UserPW, TextInputEditText_UserSex, TextInputEditText_UserPhoneNumber;
    // private TextInputEditText UserCode;
    private RelativeLayout RelativeLayout_Continue;
    // private Button btn_verify, btn_check;
    // final int ServerCode;   // 서버에서 생성된 6자리 난수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText_Email = findViewById(R.id.TextInputEditText_Email);
        TextInputEditText_UserID = findViewById(R.id.TextInputEditText_UserID);
        TextInputEditText_UserPW = findViewById(R.id.TextInputEditText_UserPW);
        TextInputEditText_UserSex = findViewById(R.id.TextInputEditText_Sex);
        TextInputEditText_UserPhoneNumber = findViewById(R.id.TextInputEditText_PhoneNumber);

        /*
        // 이메일로 인증번호 보내기

        btn_verify = findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 서버로 이메일 전송
                // 서버에서 6자리 난수 생성
                // 서버에서 이메일과 안드로이드로 6자리 난수 반환
                Toast.makeText(Register_Activity.this, "이메일로 발송된 6자리 인증코드를 입력해주세요.", Toast.LENGTH_LONG).show();
            }
        });

        // 인증번호 확인하기
        UserCode = findViewById(R.id.TextInputEditText_VerificationCode);
        btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 서버에서 전송된 인증번호와 입력된 인증번호 비교
                if(ServerCode != UserCode){
                    Toast.makeText(Register_Activity.this, "Verification Failed", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Register_Activity.this, "Verification Succeeded", Toast.LENGTH_SHORT).show();
                    // 타이머 멈추기
                    // 타이머 시간이 다 되면 Toast.makeText(Register_Activity.this, "Time Out. Please try again.", Toast.LENGTH_SHORTH).show();
                }

           }
        });
*/
        // 회원가입 버튼 클릭 시 수행
        RelativeLayout_Continue = findViewById(R.id.RelativeLayout_Continue);
        RelativeLayout_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 입력자 정보 가져오기
                String userEmail = TextInputEditText_Email.getText().toString();
                String userID = TextInputEditText_UserID.getText().toString();
                String userPW = TextInputEditText_UserPW.getText().toString();
                String userSex = TextInputEditText_UserSex.getText().toString();
                String userPhoneNumber = TextInputEditText_UserPhoneNumber.getText().toString();

                if(userSex.equals("M")){
                    Intent intent = new Intent(Register_Activity.this, MaleTaste_Activity.class);
                    intent.putExtra("userEmail", userEmail);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userPW", userPW);
                    intent.putExtra("userSex", userSex);
                    intent.putExtra("userPhoneNumber", userPhoneNumber);
                    startActivity(intent);
                }
                else if(userSex.equals("F")){
                    Intent intent = new Intent(Register_Activity.this, FemaleTaste_Activity.class);
                    intent.putExtra("userEmail", userEmail);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userPW", userPW);
                    intent.putExtra("userSex", userSex);
                    intent.putExtra("userPhoneNumber", userPhoneNumber);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "성별을 다시 입력해 주세요! (M / F)", Toast.LENGTH_SHORT).show();
                }

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
                Register_Request register_request = new Register_Request(userEmail, userID, userPW, userSex, userPhoneNumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register_Activity.this);
                queue.add(register_request);

            }
        });

    }
}
