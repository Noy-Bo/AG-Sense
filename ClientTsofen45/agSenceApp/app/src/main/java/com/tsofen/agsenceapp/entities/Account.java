package com.tsofen.agsenceapp.entities;

public class Account extends  User{
    boolean faulty;

    public Account(int id, String userName, String email, String userType) {
        super(id, userName, email, userType);
    }

    public boolean isFaulty() {
        return faulty;
    }
    public void setFaulty(boolean faulty) {
        this.faulty = faulty;
    }
}
