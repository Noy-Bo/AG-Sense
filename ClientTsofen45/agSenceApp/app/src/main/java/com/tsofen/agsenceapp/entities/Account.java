package com.tsofen.agsenceapp.entities;

import android.bluetooth.BluetoothClass;

import java.io.Serializable;
import java.util.List;

public class Account extends User implements Serializable {

    private boolean isFaulty;
    public int accountId;
    private List<Devices> devices;

    public Account(){
        super();
    }

    public Account(int id, String username, String email, boolean isFaulty, int accountId) {
        super(id, username, email);
        this.isFaulty = isFaulty;
        this.accountId = accountId;
    }

    public Account(int id, String username, String email, boolean isFaulty, int accountId, List<Devices> devices) {
        super(id, username, email);
        this.isFaulty = isFaulty;
        this.accountId = accountId;
        this.devices = devices;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public Account setDevices(List<Devices> devices) {
        this.devices = devices;
        return this;
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

    @Override
    public String toString() {
        return "Account{" +
                "isFaulty=" + isFaulty +
                ", accountId=" + accountId +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
