package com.example.goraniz_clozet;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PersonalTaste_Request extends StringRequest {

    //서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://116.34.65.201/goraniz/rating.php";
    private Map<String, String> map;

    public PersonalTaste_Request(String userID, String first, String second, String third, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("first", first);
        map.put("second", second);
        map.put("third", third);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
