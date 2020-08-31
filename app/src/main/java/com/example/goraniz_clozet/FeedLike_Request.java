package com.example.goraniz_clozet;

import android.content.Intent;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FeedLike_Request extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://15.165.210.41/feed_like.php";
    private Map<String, String> map;

    public FeedLike_Request(String userID, String postID, int like, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("postID", postID);
        map.put("like", like + "");
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
