package com.example.taskmanagerapp;

public class User{
    private int userId;
    private String username;

    private User() {
    }
    public static User instance = null;
    public static User getInstance() {
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

