package com.example.goraniz_clozet;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FeedWrite_Request extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://116.34.65.201/goraniz/post_feed.php";
    private Map<String, String> map;

    public FeedWrite_Request(String userID, String description, String tag, String image, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        //map.put("uri", uri);
        map.put("description", description);
        map.put("style", tag);
        map.put("image", image);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
