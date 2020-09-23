package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.util.List;

public class Account extends User implements Serializable {

    private boolean faulty;
    public int accountid;
    private List<Devices> devices;

    public Account(){
        super();
    }

    public Account(int id, String username, String email, boolean isFaulty, int accountid) {
        super(id, username, email);
        this.faulty = isFaulty;
        this.accountid = accountid;
    }

    public Account(int id, String username, String email, boolean isFaulty, int accountid, List<Devices> devices) {
        super(id, username, email);
        this.faulty = isFaulty;
        this.accountid = accountid;
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
        return faulty;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setFaulty(boolean faulty) {
        this.faulty = faulty;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "isFaulty=" + faulty +
                ", accountId=" + accountid +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
