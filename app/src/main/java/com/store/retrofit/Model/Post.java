package com.store.retrofit.Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    private String userId;
    private Integer id;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(String userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
