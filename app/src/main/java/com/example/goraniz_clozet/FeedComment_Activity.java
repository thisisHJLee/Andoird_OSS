package com.example.goraniz_clozet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeedComment_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_comment);

        Button btn_done = findViewById(R.id.button_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스에 업로드
                // 댓글 창 새로고침
            }
        });
    }
}