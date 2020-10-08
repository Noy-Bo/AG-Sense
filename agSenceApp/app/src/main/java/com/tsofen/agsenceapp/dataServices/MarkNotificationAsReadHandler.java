package com.tsofen.agsenceapp.dataServices;

public interface MarkNotificationAsReadHandler extends BaseHandler {
    void onNotificationMarkedAsRead(Boolean finishedSuccessfully);
}
