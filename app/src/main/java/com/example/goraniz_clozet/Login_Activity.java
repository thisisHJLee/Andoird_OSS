package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity{

    private TextInputEditText TextInputEditText_ID, TextInputEditText_Password;
    private RelativeLayout RelativeLayout_Login;
    private Button Button_SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_ID = findViewById(R.id.TextInputEditText_ID);
        TextInputEditText_Password = findViewById(R.id.TextInputEditText_Password);
        RelativeLayout_Login = findViewById(R.id.RelativeLayout_Login);
        Button_SignUp = findViewById(R.id.Button_SignUp);

        // 회원가입 버튼 클릭 시 수행
        Button_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });

        RelativeLayout_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력자 정보 가져오기
                String userID = TextInputEditText_ID.getText().toString();
                final String userPassword = TextInputEditText_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);   // 통신상태의 성공 여부 반환함
                            boolean success = jsonObject.getBoolean("success");
                            // 로그인에 성공한 경우
                            if(success){
                                String userID = jsonObject.getString("userID");
                                String userPassword = jsonObject.getString("userPW");

                                // 피드 화면으로 유저 아이디 보내기
                                Bundle bundle = new Bundle();
                                bundle.putString("userID", userID);
                                Fragment menu_feed = new Fragment();
                                menu_feed.setArguments(bundle);

                                Toast.makeText(getApplicationContext(), "Login Succeed.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPW", userPassword);
                                startActivity(intent);

                            }
                            else{        // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 로그인 처리 완성
                Login_Request loginRequest = new Login_Request(userID, userPassword, responseListener);
                RequestQueue  queue = Volley.newRequestQueue(Login_Activity.this);
                queue.add(loginRequest);
            }
        });

    }
}
