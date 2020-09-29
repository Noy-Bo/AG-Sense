package com.tsofen.agsenceapp.utils;

public class AdminDashboardInfo {
    private Integer healthyAccounts;
    private Integer faultyAccounts;
    private Integer healthyDevices;
    private Integer faultyDevices;
    private Integer unreadNotifications;

    public AdminDashboardInfo(Integer healthyAccounts, Integer faultyAccounts, Integer healthyDevices, Integer faultyDevices, Integer unreadNotifications) {
        this.healthyAccounts = healthyAccounts;
        this.faultyAccounts = faultyAccounts;
        this.healthyDevices = healthyDevices;
        this.faultyDevices = faultyDevices;
        this.unreadNotifications = unreadNotifications;
    }

    public Integer getHealthyAccounts() {
        return healthyAccounts;
    }

    public void setHealthyAccounts(Integer healthyAccounts) {
        this.healthyAccounts = healthyAccounts;
    }

    public Integer getFaultyAccounts() {
        return faultyAccounts;
    }

    public void setFaultyAccounts(Integer faultyAccounts) {
        this.faultyAccounts = faultyAccounts;
    }

    public Integer getHealthyDevices() {
        return healthyDevices;
    }

    public void setHealthyDevices(Integer healthyDevices) {
        this.healthyDevices = healthyDevices;
    }

    public Integer getFaultyDevices() {
        return faultyDevices;
    }

    public void setFaultyDevices(Integer faultyDevices) {
        this.faultyDevices = faultyDevices;
    }

    public Integer getUnreadNotifications() {
        return unreadNotifications;
    }

    public void setUnreadNotifications(Integer unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }
}
