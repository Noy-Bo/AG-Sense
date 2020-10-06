package com.tsofen.agsenceapp.dataServices;

public interface UserPasswordChangeHandler extends BaseHandler {
    void onUserPasswordChangedFinished(Boolean finishedSuccessfully);

}
