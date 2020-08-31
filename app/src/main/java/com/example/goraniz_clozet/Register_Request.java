package com.example.goraniz_clozet;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Register_Request extends StringRequest {

    //서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://116.34.65.201/goraniz/Register.php";
    private Map<String, String> map;

    public Register_Request(String userEmail, String userID, String userPW, String userSex, String userPhoneNumber, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail", userEmail);
        map.put("userID", userID);
        map.put("userPW", userPW);
        map.put("userSex", userSex);
        map.put("userPhone", userPhoneNumber);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
