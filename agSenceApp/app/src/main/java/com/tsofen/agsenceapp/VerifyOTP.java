package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.tsofen.agsenceapp.adaptersInterfaces.ConfirmCodeDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.ForgetPasswordDataAdapter;

public class VerifyOTP extends AppCompatActivity {
//    protected String verificationCodeBySystem;
    protected String verificationCodeByUser;
//    protected FirebaseAuth mAuth;
    protected String username;
    protected PinView pinView;
//   protected FirebaseUser fireBaseUser;
//    protected boolean isemailchecked;
//    protected String email = "ameerkadi97@gmail.com";
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String PhoneNumber = "0528745466";
        setContentView(R.layout.activity_verify_o_t_p);
        pinView = findViewById(R.id.pin_view);
        username = getIntent().getStringExtra("username");
        verificationCodeByUser = pinView.getText().toString();

    }

    public void goToHomeFromOTP(View view) {
    }

    public void confirmuserCode(View view) {
        ForgetPasswordDataAdapter.getInstance().confirmUserCode(username, verificationCodeByUser, new ConfirmCodeDataRequestHandler() {
            @Override
            public void onUserConfirmCode(boolean confirmed) {
if(confirmed)
{
    Intent intent = new Intent(VerifyOTP.this, VerifyOTP.class);
    intent.putExtra("username",username);
    startActivity(intent);
}
else
{
    Toast.makeText(VerifyOTP.this, "The Code you entered is invalid, try again.", Toast.LENGTH_SHORT).show();
}
            }
        });
    }
}
//    private void sendEmailVerificationCodeToUser(String email) {
//fireBaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//    @Override
//    public void onComplete(@NonNull Task<Void> task) {
//        if(task.isSuccessful())
//        {
//startlog();
//        }
//        else
//        {
//            Toast.makeText(VerifyOTP.this, "failed", Toast.LENGTH_SHORT).show();
//        }
//    }
//});
//
//    }
//    private void startlog()
//    {
//        isemailchecked = fireBaseUser.isEmailVerified();
//        if(isemailchecked){
//            Intent intent = new Intent(VerifyOTP.this, SetNewPassword.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            Toast.makeText(VerifyOTP.this, "Verification succeeded", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(VerifyOTP.this, "Verification failed", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void sendVerificationCodeToUser(String phoneNumber) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber("+972"+phoneNumber,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallBack);
//
//    }
//private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//    @Override
//    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//        super.onCodeSent(s, forceResendingToken);
//        verificationCodeBySystem = s;
//    }
//
//    @Override
//    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//        String code = phoneAuthCredential.getSmsCode() ;
//     if(code !=null)
//     {
//        // progressBar.setVisibility(View.VISIBLE);
//         verifyCode(code);
//     }
//    }
//
//    @Override
//    public void onVerificationFailed(@NonNull FirebaseException e) {
//        Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//    }
//};
//    public void goToHomeFromOTP(View view) {
//    }
//
//    public void callNextScreenFromOTP(View view) {
//      /*  pinView = findViewById(R.id.pin_view);
//verificationcodebyUser = pinView.getText().toString();
//System.out.println(verificationCodeBySystem + "      "  + verificationcodebyUser);
//        if(verificationcodebyUser !=null)
//        {
//           progressBar = findViewById(R.id.VerifyCodeProgressbar);
//           progressBar.setVisibility(View.VISIBLE);
//            verifyCode(verificationcodebyUser);
//        }
//        */
//
//        Intent intent = new Intent(VerifyOTP.this, SetNewPassword.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
//
//private void verifyCode(String codeByUser){
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
//        signInTheUserByCredentials(credential);
//}
//
//    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyOTP.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful())
//                {
//                    progressBar = findViewById(R.id.VerifyCodeProgressbar);
//                    progressBar.setVisibility(View.INVISIBLE);
//                    Intent intent = new Intent(VerifyOTP.this, SetNewPassword.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    Toast.makeText(VerifyOTP.this, "Verification succeeded", Toast.LENGTH_SHORT).show();
//                  //  Intent intent = new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class);
//
//                    //startActivity(intent);
//                }
//                else
//                {
//                    Toast.makeText(VerifyOTP.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }) ;
//    }
//}