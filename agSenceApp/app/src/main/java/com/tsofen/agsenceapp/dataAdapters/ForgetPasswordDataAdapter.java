package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.ConfirmCodeDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.ConfirmPasswordDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.EmailPickedConfirmedDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.ForgetPasswordDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.ForgetPasswordDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.PhonePickedConfirmedDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.UserDetailsForgetPasswordHandler;
import com.tsofen.agsenceapp.dataServices.UserPasswordChangeHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeCheckHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeSentHandler;
import com.tsofen.agsenceapp.entities.Account;

public class ForgetPasswordDataAdapter extends BaseDataAdapter implements ForgetPasswordDataAdapterAPI {
    private static ForgetPasswordDataAdapter instance;

private ForgetPasswordDataAdapter(){

}

    public static ForgetPasswordDataAdapter getInstance() {
        if (instance == null)
            instance = new ForgetPasswordDataAdapter();
        return instance;
    }


    @Override
    public void getUserDetails(String account ,final ForgetPasswordDataRequestHandler handler) {
        cacheManager.userDetailsForgetPassword(account, new UserDetailsForgetPasswordHandler() {
            @Override
            public void onUserDetails(Account returnedaccount) {

                handler.onUserDetailsReceived(returnedaccount);
            }
        });
    }

    @Override
    public void confirmUserCode(String username,String code, ConfirmCodeDataRequestHandler handler) {
        cacheManager.verifyCodeJob(username, code, new VerificationCodeCheckHandler() {
            @Override
            public void onVerificationCodeFinished(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                    handler.onUserConfirmCodeSuccess();
                else
                    handler.onUserConfirmCodeFailure();
            }
        });
    }

    @Override
    public void confirmUserPassword(String username,String code, String password, ConfirmPasswordDataRequestHandler handler) {
        cacheManager.changeUserPasswordJob(username,code ,password, new UserPasswordChangeHandler() {
            @Override
            public void onUserPasswordChangedFinished(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                    handler.onUserConfirmPasswordSuccess();
                else
                    handler.onUserConfirmPasswordFailure();

            }
        });
    }

    @Override
    public void emailPickedConfirmed(String username, EmailPickedConfirmedDataRequestHandler handler) {
        cacheManager.emailConfirmed(username, new VerificationCodeSentHandler() {
            @Override
            public void onVerificationCodeSent(Boolean finishedSuccessfully) {
              if(finishedSuccessfully)
                  handler.onUserEmailPickedSuccess();
              else
                  handler.onUserEmailPickedFailure();
            }
        });
    }

    @Override
    public void phonePickedConfirmed(String username, PhonePickedConfirmedDataRequestHandler handler) {
        cacheManager.phoneConfirmed(username, new VerificationCodeSentHandler() {
            @Override
            public void onVerificationCodeSent(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                handler.onUserPhonePickedSuccess();
                else
                    handler.onUserPhonePickedFailure();
            }
        });
    }

}
