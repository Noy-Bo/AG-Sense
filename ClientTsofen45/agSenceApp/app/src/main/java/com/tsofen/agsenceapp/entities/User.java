package com.tsofen.agsenceapp.entities;

import java.io.Serializable;

public class User implements Serializable {
    protected int id;
    protected String userName;
    protected String email;
    protected String userType;

    public User(int id, String userName, String email, String userType) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
