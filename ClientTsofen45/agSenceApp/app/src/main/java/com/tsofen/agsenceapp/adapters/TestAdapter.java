
        package com.tsofen.agsenceapp.adapters;

        import java.util.Timer;
        import java.util.TimerTask;

public class TestAdapter {

    public void login(String username, String password, final LoginCallBack handler)
    {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.onUserLoginSuccess();
            }
        },5*1000);


    }
}