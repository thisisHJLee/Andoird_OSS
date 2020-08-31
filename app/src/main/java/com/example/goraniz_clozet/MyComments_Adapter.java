package com.example.goraniz_clozet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyComments_Adapter extends RecyclerView.Adapter<MyComments_Adapter.CustomViewHolder>{

    private ArrayList<MyComments_Data> arrayList;

    public MyComments_Adapter(ArrayList<MyComments_Data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyComments_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyComments_Adapter.CustomViewHolder holder, int position) {
        holder.iv_thumbnail.setImageResource(arrayList.get(position).getIv_thumbnail());
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_writer.setText(arrayList.get(position).getWriter());
        holder.tv_likes.setText(arrayList.get(position).getLikes());
        holder.tv_content.setText(arrayList.get(position).getComment_contents());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_thumbnail;
        protected TextView tv_title;
        protected TextView tv_writer;
        protected TextView tv_likes;
        protected TextView tv_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_thumbnail = (ImageView)itemView.findViewById(R.id.iv_thumbnail);
            this.tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            this.tv_writer = (TextView)itemView.findViewById(R.id.tv_writer);
            this.tv_likes = (TextView)itemView.findViewById(R.id.tv_likes);
            this.tv_content = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
