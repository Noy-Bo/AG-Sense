package com.tsofen.agsenceapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class NotificationListAdaptor extends ArrayAdapter<Notification> {
    LayoutInflater inflater;

    public NotificationListAdaptor(Context context, ArrayList<Notification> notificationArray)
    {

        super(context,0,notificationArray);
        inflater = LayoutInflater.from(context);
        Log.d("IN ADAPTER","IN CONSTRUCTOR");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Log.d("IN ADAPTER","IN GETVIEW");
        View layout = this.inflater.inflate(R.layout.notifictation_item_shape,null);
        Notification notification = getItem(position);

        ImageView notificationIconBox = layout.findViewById(R.id.icon_image);
        TextView deviceNameDeviceTypeBox = layout.findViewById(R.id.device_name_device_type);
        TextView errorMessageBox = layout.findViewById((R.id.error_message));
        TextView accountNameBox = layout.findViewById(R.id.account_name);
        TextView timeBox = layout.findViewById((R.id.date_time));

        if ( notification.getImageStatus() == ColorStatus.blue)
            notificationIconBox.setImageResource(R.drawable.notification_icon_blue);
        else if ( notification.getImageStatus() == ColorStatus.green)
            notificationIconBox.setImageResource(R.drawable.notification_icon_green);
        else if ( notification.getImageStatus() == ColorStatus.yellow)
            notificationIconBox.setImageResource(R.drawable.notification_icon_yellow);
        else if ( notification.getImageStatus() == ColorStatus.red)
            notificationIconBox.setImageResource(R.drawable.notification_icon_red);

        deviceNameDeviceTypeBox.setText(notification.getDeviceName()+" "+notification.getDeviceType());
        errorMessageBox.setText(notification.getErrorMessage());
        accountNameBox.setText(notification.getAccountName());
        timeBox.setText(notification.getDateTime());

        return layout;
    }

}
