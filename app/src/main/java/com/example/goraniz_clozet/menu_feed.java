package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.like.LikeButton;
import com.like.OnLikeListener;

public class menu_feed extends Fragment{

    private View view;
    String userID;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.menu_feed, container, false);

        // 유저 아이디 로그인 창으로부터 받아오기
        if(getArguments() != null){
            userID = getArguments().getString("userID");
        }

        // 댓글 버튼 선택 시 댓글쓰기 창으로 이동
        ImageButton btn_comment = view.findViewById(R.id.feed_comment);
        btn_comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedComment_Activity.class);
                startActivity(intent);
            }
        });

        // 좋아요 버튼 클릭 시 알림 띄우기 + 데베 연동
        LikeButton btn_like = view.findViewById(R.id.feed_like);
        btn_like.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                // 데이터베이스로 값 보내기 (userID, postID, 좋아요 정보)
                String liked = "1";

                Intent intent = new Intent(getActivity(), FeedLike_Activity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("like", liked);
                startActivity(intent);
            }
            @Override
            public void unLiked(LikeButton likeButton) {
                // 데이터베이스로 값 보내기 (userID, postID, 좋아요 취소 정보)
                String liked = "-1";

                Intent intent = new Intent(getActivity(), FeedLike_Activity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("like", liked);
                startActivity(intent);
            }
        });

        // 피드 글쓰기 버튼 클릭 시 글쓰기 창으로 이동
        FloatingActionButton feed_Write = (FloatingActionButton) view.findViewById(R.id.feed_Write);
        feed_Write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent write_intent = new Intent(getActivity(), FeedWrite_Activity.class);
                write_intent.putExtra("userID", userID);
                startActivity(write_intent);
            }
        });

        return view;
    }

}
