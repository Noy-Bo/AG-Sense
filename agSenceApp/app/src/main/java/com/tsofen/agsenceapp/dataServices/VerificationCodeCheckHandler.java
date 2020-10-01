package com.tsofen.agsenceapp.dataServices;

public interface VerificationCodeCheckHandler extends BaseHandler {
    void onVerificationCodeFinished(Boolean finishedSuccessfully);
}
