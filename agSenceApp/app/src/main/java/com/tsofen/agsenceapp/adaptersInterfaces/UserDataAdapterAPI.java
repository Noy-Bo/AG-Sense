package com.tsofen.agsenceapp.adaptersInterfaces;

public interface UserDataAdapterAPI {
    void userLogin(String username, String password , final onUserLoginHandler handler);
    void getAdminDashboardInfo(int adminId, AdminDashboardInfoHandler handler);
}
