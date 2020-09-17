package com.tsofen.agsenceapp.entities;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected int id;
    protected String username;
    protected String email;

    public User(){}
    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  }
