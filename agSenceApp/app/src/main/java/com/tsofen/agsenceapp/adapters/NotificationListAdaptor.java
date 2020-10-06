package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.activities.AppBaseActivity;
import com.tsofen.agsenceapp.adaptersInterfaces.MarkNotificationRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.NotificationsDataAdapter;
import com.tsofen.agsenceapp.dataServices.MarkNotificationAsReadHandler;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.entities.User;
import com.tsofen.agsenceapp.utils.Severity;

import java.util.List;


public class NotificationListAdaptor extends ArrayAdapter<Notification> {
    LayoutInflater inflater;

    public NotificationListAdaptor(Context context,int resource, List<Notification> notificationArray)
    {
        super(context,0,notificationArray);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View layout = this.inflater.inflate(R.layout.notifictation_item_shape,null);
        final Notification notification = getItem(position);

        ImageView notificationIconBox = layout.findViewById(R.id.icon_image);
        final TextView deviceNameDeviceTypeBox = layout.findViewById(R.id.device_name_device_type);
        final TextView errorMessageBox = layout.findViewById((R.id.error_message));
        TextView accountNameBox = layout.findViewById(R.id.account_name);
        final TextView timeBox = layout.findViewById((R.id.date_time));

        if ( notification.getSeverity() == Severity.MODERATE)
            notificationIconBox.setImageResource(R.drawable.notification_icon_blue);
        else if ( notification.getSeverity() == Severity.LOW)
            notificationIconBox.setImageResource(R.drawable.notification_icon_green);
        else if ( notification.getSeverity() == Severity.MAJOR)
            notificationIconBox.setImageResource(R.drawable.notification_icon_yellow);
        else if ( notification.getSeverity() == Severity.CRITICAL)
            notificationIconBox.setImageResource(R.drawable.notification_icon_red);

        deviceNameDeviceTypeBox.setText(notification.getDevice_id()+" "+notification.getDevice_id());
        errorMessageBox.setText(notification.getMessage());

        timeBox.setText(String.valueOf(notification.getDate_time()));

        if(notification.getReaded()) {
            deviceNameDeviceTypeBox.setTypeface(null, Typeface.NORMAL);
            errorMessageBox.setTypeface(null, Typeface.NORMAL);
            timeBox.setTypeface(null, Typeface.NORMAL);
        }
        else {
            deviceNameDeviceTypeBox.setTypeface(null, Typeface.BOLD);
            errorMessageBox.setTypeface(null, Typeface.BOLD);
            timeBox.setTypeface(null, Typeface.BOLD);

        }
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!notification.getReaded()) {
//                    CacheMgr.getInstance().markNotificationAsReadJob(AppBaseActivity.getUser().getId(), notification.getId(), new MarkNotificationAsReadHandler() {
//                        @Override
//                        public void onNotificationMarkedAsRead(Boolean finishedSuccessfully) {
//                            if(finishedSuccessfully) {
//                                deviceNameDeviceTypeBox.setTypeface(null, Typeface.NORMAL);
//                                errorMessageBox.setTypeface(null, Typeface.NORMAL);
//                                timeBox.setTypeface(null, Typeface.NORMAL);
//                                notification.setReaded(true);
//                            }
//                        }
//                    });
                    int userId = AppBaseActivity.getUser().getId();
                    NotificationsDataAdapter.getInstance().setNotificationAsRead(userId, notification.getId(), new MarkNotificationRequestHandler() {
                        @Override
                        public void onInfoReceived(Boolean answer) {
                            if (answer)
                                deviceNameDeviceTypeBox.setTypeface(null, Typeface.NORMAL);
                                errorMessageBox.setTypeface(null, Typeface.NORMAL);
                                timeBox.setTypeface(null, Typeface.NORMAL);
                                notification.setReaded(true);
                        }
                    });

                }
            }
        });
        return layout;
    }

}
