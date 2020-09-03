package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.Devices;

import java.io.Serializable;

public class DevicesAdapter extends ArrayAdapter<Devices> implements Serializable {
    LayoutInflater inflater;

    public DevicesAdapter(Context context, int resource, Devices[] devices) {
        super(context, resource, devices);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = this.inflater.inflate(R.layout.activity_device_status_shape, null);
        Devices devices = getItem(position);
        TextView name = layout.findViewById(R.id.devicename);
        TextView devicetypeid = layout.findViewById((R.id.deviceidtype));
        TextView faultytime = layout.findViewById((R.id.devicefaultTime));
        TextView lastupdate = layout.findViewById(R.id.devicelastUpdate);
        ImageView imageView = layout.findViewById(R.id.device_status_imageview);
        imageView.setImageResource(R.drawable.faulty_devices_icon);
        name.setText(devices.getName());
        devicetypeid.setText(String.valueOf(devices.getDeviceTypeID()));
        faultytime.setText(devices.getFaultTime());
        lastupdate.setText(devices.getLastUpdate());

        return layout;
    }
}