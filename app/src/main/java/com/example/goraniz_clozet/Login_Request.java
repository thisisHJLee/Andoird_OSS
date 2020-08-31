package com.example.goraniz_clozet;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Login_Request extends StringRequest {

    //서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://116.34.65.201/goraniz/Login.php";
    private Map<String, String> map;

    public Login_Request(String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPW", userPassword);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
