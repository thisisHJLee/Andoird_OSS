package com.example.goraniz_clozet;

public class MyComments_Data {

    private int iv_thumbnail;
    private String title;
    private String writer;
    private int likes;
    private String comment_contents;

    public MyComments_Data(int iv_thumbnail, String title, String writer, int likes, String comment_contents) {
        this.iv_thumbnail = iv_thumbnail;
        this.title = title;
        this.writer = writer;
        this.likes = likes;
        this.comment_contents = comment_contents;
    }

    public int getIv_thumbnail() {
        return iv_thumbnail;
    }

    public void setIv_thumbnail(int iv_thumbnail) {
        this.iv_thumbnail = iv_thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getComment_contents() {
        return comment_contents;
    }

    public void setComment_contents(String comment_contents) {
        this.comment_contents = comment_contents;
    }
}
