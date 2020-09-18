package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.DeviceLastMessage;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class DeviceDataListAdapter extends ArrayAdapter<DeviceData> {
    private LayoutInflater inflater;

    public DeviceDataListAdapter(@NonNull Context context, @NonNull List<DeviceData> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DeviceData deviceData = getItem(position);
//        if (position == 0) { //inflating title row...
//            View layout = this.inflater.inflate(R.layout.device_last_message_titles, null);
//            return layout;
//        }
        View layout = this.inflater.inflate(R.layout.device_last_message_item, null);

        TextView update_time = layout.findViewById(R.id.update_time_column);
        TextView time_ellapsed = layout.findViewById(R.id.time_ellapsed_column);
        TextView other_info = layout.findViewById(R.id.other_info_column);


        String updateTimeStr = String.valueOf(deviceData.getDateAndTime());
        String timeEllapsedStr = String.valueOf(deviceData.getDateAndTime());
        String infoStr = "Lat: " + deviceData.getLat() + " Lon: " + deviceData.getLon();

        update_time.setText(updateTimeStr);
        time_ellapsed.setText(timeEllapsedStr);
        other_info.setText(infoStr);

        return layout;

    }
}
