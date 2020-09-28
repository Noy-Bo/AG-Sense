package com.tsofen.agsenceapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

public class GeneralProgressBar {
    public static ProgressDialog progressDialog;

    //public static ProgressDialog getProgressDialog() { return progressDialog; }

    public static ProgressDialog displayProgressDialog(Context context, String message)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public static void removeProgressDialog(ProgressDialog progressDialog)
    {
        if(progressDialog != null)
            progressDialog.dismiss();
    }
}
