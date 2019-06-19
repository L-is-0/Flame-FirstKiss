package com.hazel.myfirstkiss.Models;

public class User {
    private int userId;
    private String userName;

    public void setUserId(int id){ this.userId = id; }

    public void setUserName(String name){
        this.userName = name;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }
}
