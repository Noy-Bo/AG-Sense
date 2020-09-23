package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.activities.AccountDashboardActivity;
import com.tsofen.agsenceapp.activities.AppBaseActivity;
import com.tsofen.agsenceapp.activities.DeviceView;
import com.tsofen.agsenceapp.entities.Devices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DevicesAdapter extends ArrayAdapter<Devices> implements Serializable {
    LayoutInflater inflater;

    public DevicesAdapter(Context context, int resource, List<Devices> devices) {
        super(context, resource, devices);
        inflater = LayoutInflater.from(context);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View layout = this.inflater.inflate(R.layout.activity_device_status_shape, null);
        final Devices devices = getItem(position);
        TextView name = layout.findViewById(R.id.accountname);
        TextView devicetypeid = layout.findViewById((R.id.deviceidtype));
        TextView faultytime = layout.findViewById((R.id.devicefaultTime));
        TextView lastupdate = layout.findViewById(R.id.devicelastUpdate);
        ImageView imageView = layout.findViewById(R.id.device_status_imageview);
        imageView.setImageResource(R.drawable.faulty_devices_icon);


        name.setText( String.valueOf(devices.getAccountId()));
        devicetypeid.setText((devices.getType()));
        faultytime.setText(String.valueOf(devices.getFaultTime()));
        lastupdate.setText(String.valueOf(devices.getLastUpdate()));


        LinearLayout linearLayout = layout.findViewById(R.id.DeviceItemShape);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DeviceView.class);
                intent.putExtra("device", devices);
                getContext().startActivity(intent);
            }
        });
        return layout;
    }
}
