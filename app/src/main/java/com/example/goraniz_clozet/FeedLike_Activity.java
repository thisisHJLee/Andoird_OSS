package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedLike_Activity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String liked = intent.getStringExtra("liked");

        // postID 가져와야 함
        String postID;

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // 통신상태의 성공 여부 반환함
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = (boolean) jsonObject.get("success");

                    // 좋아요 보내기 성공한 경우
                    if (success) {
                        Toast.makeText(getApplicationContext(), "해당 게시글을 좋아합니다", Toast.LENGTH_SHORT).show();
                    }
                    // 좋아요 보내기 실패한 경우
                    else {
                        Toast.makeText(getApplicationContext(), "해당 게시물의 좋아요를 취소하였습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        /*
        // 서버로 volley를 이용해 요청을 함
        FeedLike_Request feedLike_request = new FeedLike_Request(userID, postID, liked, responseListener);
        RequestQueue queue = Volley.newRequestQueue(FeedLike_Activity.this);
        queue.add(feedLike_request);

         */
    }
}
