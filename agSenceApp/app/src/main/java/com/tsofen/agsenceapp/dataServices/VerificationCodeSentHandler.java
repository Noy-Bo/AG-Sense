package com.tsofen.agsenceapp.dataServices;

public interface VerificationCodeSentHandler extends BaseHandler {
    void onVerificationCodeSent(Boolean finishedSuccessfully);
}
