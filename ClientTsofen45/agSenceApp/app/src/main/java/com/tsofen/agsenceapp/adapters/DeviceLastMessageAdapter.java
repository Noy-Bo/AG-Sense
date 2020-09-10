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
import com.tsofen.agsenceapp.entities.DeviceLastMessage;

import java.util.List;

public class DeviceLastMessageAdapter extends ArrayAdapter<DeviceLastMessage> {
    private LayoutInflater inflater;


    public DeviceLastMessageAdapter(@NonNull Context context, @NonNull List<DeviceLastMessage> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DeviceLastMessage lastMessage = getItem(position);
        if(position==0){ //inflating title row...
            View layout = this.inflater.inflate(R.layout.device_last_message_titles, null);
            return layout;
        }
        View layout = this.inflater.inflate(R.layout.device_last_message_item, null);

        TextView update_time = layout.findViewById(R.id.update_time_column);
        TextView time_ellapsed = layout.findViewById(R.id.time_ellapsed_column);
        TextView other_info = layout.findViewById(R.id.other_info_column);

        update_time.setText(lastMessage.getLastUpdateTime());
        time_ellapsed.setText(lastMessage.getTimeElapsed());
        other_info.setText(lastMessage.getOtherInfo());

        return layout;

    }
}
