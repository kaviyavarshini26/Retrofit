package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
   //in post dont want  tosend this in post becoz it the json automaticallymakes it null
   // private int id;
    private int userId;
    private Integer id;
    private String title;
    @SerializedName("body")
    private String text;

    //post


    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
