package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class menu_profile extends Fragment {

    private View view;

    // 피드게시물/포스트 관련
    private ArrayList<MyPosts_Data> arrayList_P;
    private MyPosts_Adapter myPosts_adapter;
    private RecyclerView recyclerView_P;
    private GridLayoutManager gridLayoutManager;

    // 댓글 관련
    private ArrayList<MyComments_Data> arrayList;
    private MyComments_Adapter myComments_adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_profile, container, false);

        // 피드 게시물/포스트 관련
        recyclerView_P = (RecyclerView)view.findViewById(R.id.recycler_view_posts);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView_P.setLayoutManager(gridLayoutManager);

        arrayList_P = new ArrayList<>();

        myPosts_adapter = new MyPosts_Adapter(arrayList_P);
        recyclerView_P.setAdapter(myPosts_adapter);


        // 댓글 관련
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_comments);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        myComments_adapter =  new MyComments_Adapter(arrayList);
        recyclerView.setAdapter(myComments_adapter);


        // 설정 아이콘 클릭 시 설정 페이지로 이동
        ImageView options = (ImageView)view.findViewById(R.id.options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Settings_Activity.class);
                startActivity(intent);
            }
        });


        // FEED POST 버튼 클릭 시 내 게시물 보여줌
        Button feed_posts = (Button)view.findViewById(R.id.feed_post);
        feed_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView_P.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

                MyPosts_Data myPosts_data = new MyPosts_Data(R.mipmap.ic_launcher);
                myPosts_adapter.notifyDataSetChanged();
            }
        });


        // MY COMMENTS 버튼 클릭 시 내 댓글 보여줌
        Button comments = (Button)view.findViewById(R.id.comments);
        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView_P.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                MyComments_Data myComments_data = new MyComments_Data(R.mipmap.ic_launcher, "Title", "Writer", 0, "Comment contents");
                myComments_adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
