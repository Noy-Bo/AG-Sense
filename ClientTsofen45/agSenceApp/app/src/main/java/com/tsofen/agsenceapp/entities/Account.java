package com.tsofen.agsenceapp.entities;

import java.io.Serializable;

public class Account extends User implements Serializable {

    private boolean isFaulty;
    public int accountId;

    public Account(int id, String userName, String email, boolean isFaulty, int accountId) {
        super(id, userName, email);
        this.isFaulty = isFaulty;
        this.accountId = accountId;
    }

    public boolean isFaulty() {
        return isFaulty;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setFaulty(boolean faulty) {
        isFaulty = faulty;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
