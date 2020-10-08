package com.tsofen.agsenceapp.utils;

public class AdminDashboardInfo {
    private String unreadNotificationsNumber;
    private String faultyDevicesNumber;
    private String healtyAccountsNumber;
    private String faultyAccountsNumber;
    private String healtyDevicesNumber;

    public AdminDashboardInfo(){ }
    public AdminDashboardInfo(String unreadNotificationsNumber, String faultyDevicesNumber, String healtyAccountsNumber, String faultyAccountsNumber, String healtyDevicesNumber) {
        this.unreadNotificationsNumber = unreadNotificationsNumber;
        this.faultyDevicesNumber = faultyDevicesNumber;
        this.healtyAccountsNumber = healtyAccountsNumber;
        this.faultyAccountsNumber = faultyAccountsNumber;
        this.healtyDevicesNumber = healtyDevicesNumber;
    }

    public String getUnreadNotificationsNumber() {
        return unreadNotificationsNumber;
    }

    public void setUnreadNotificationsNumber(String unreadNotificationsNumber) {
        this.unreadNotificationsNumber = unreadNotificationsNumber;
    }

    public String getFaultyDevicesNumber() {
        return faultyDevicesNumber;
    }

    public void setFaultyDevicesNumber(String faultyDevicesNumber) {
        this.faultyDevicesNumber = faultyDevicesNumber;
    }

    public String getHealtyAccountsNumber() {
        return healtyAccountsNumber;
    }

    public void setHealtyAccountsNumber(String healtyAccountsNumber) {
        this.healtyAccountsNumber = healtyAccountsNumber;
    }

    public String getFaultyAccountsNumber() {
        return faultyAccountsNumber;
    }

    public void setFaultyAccountsNumber(String faultyAccountsNumber) {
        this.faultyAccountsNumber = faultyAccountsNumber;
    }

    public String getHealtyDevicesNumber() {
        return healtyDevicesNumber;
    }

    public void setHealtyDevicesNumber(String healtyDevicesNumber) {
        this.healtyDevicesNumber = healtyDevicesNumber;
    }

    @Override
    public String toString() {
        return "AdminDashboardInfo{" +
                "unreadNotificationsNumber='" + unreadNotificationsNumber + '\'' +
                ", faultyDevicesNumber='" + faultyDevicesNumber + '\'' +
                ", healtyAccountsNumber='" + healtyAccountsNumber + '\'' +
                ", faultyAccountsNumber='" + faultyAccountsNumber + '\'' +
                ", healtyDevicesNumber='" + healtyDevicesNumber + '\'' +
                '}';
    }
}
