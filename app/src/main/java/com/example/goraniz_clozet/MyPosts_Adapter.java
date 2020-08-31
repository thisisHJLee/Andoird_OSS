package com.example.goraniz_clozet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPosts_Adapter extends RecyclerView.Adapter<MyPosts_Adapter.CustomViewHolder>{

    private ArrayList<MyPosts_Data> arrayList;

    public MyPosts_Adapter(ArrayList<MyPosts_Data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list, parent, false);
        MyPosts_Adapter.CustomViewHolder holder = new MyPosts_Adapter.CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.iv_thumbnail_p.setImageResource(arrayList.get(position).getIv_thumbnail());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_thumbnail_p;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_thumbnail_p = (ImageView)itemView.findViewById(R.id.iv_thumbnail_p);
        }
    }
}