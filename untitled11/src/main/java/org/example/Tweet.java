package org.example;

import java.util.LinkedList;

public class Tweet {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(LinkedList<Reply> replies) {
        this.replies = replies;
    }

    int id;
    String user_name;
    String title;
    String post;
    String img;
    String date;
    LinkedList<Reply> replies=new LinkedList<>();
}
