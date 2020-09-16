package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;

public interface AccountDevicesHandler extends BaseHandler {
    void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices);
}
