package com.tsofen.agsenceapp.dataServices;

public interface OnDataReadyHandler {

    void onDataDownloadCompleted(String downloadedData);
    void onDownloadError();
}
