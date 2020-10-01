package com.example.ServerTsofen45.pushNotification;

public class DbUser {
    private String userName;
    private String token;

    public DbUser(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    @Override
    public String toString() {
        return "DbUser{" +
                "userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
