package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.activities.DeviceView;

import com.tsofen.agsenceapp.entities.Devices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DevicesAdapter<D> extends ArrayAdapter<Devices> implements Serializable {
    LayoutInflater inflater;
    ArrayList<Devices> allDevices;

    public DevicesAdapter(Context context, List<Devices> devices) {
        super(context, 0, devices);
        inflater = LayoutInflater.from(context);
        allDevices = new ArrayList<>(devices);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return devicesFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = this.inflater.inflate(R.layout.activity_device_status_shape, null);

        final Devices device = getItem(position);
        TextView name = layout.findViewById(R.id.device_item_name);
        TextView accountNname = layout.findViewById(R.id.device_item_account_name);
        TextView devicetypeid = layout.findViewById((R.id.device_item_type));
        TextView faultytime = layout.findViewById((R.id.device_item_fault_time));
        TextView lastupdate = layout.findViewById(R.id.device_item_last_update);
        ImageView imageView = layout.findViewById(R.id.device_status_image_view);
        if (device.getFaulty())
            imageView.setImageResource(R.drawable.faulty_devices_icon);
        else
            imageView.setImageResource(R.drawable.healthy_devices_icon);

        name.setText(device.getName());
        accountNname.setText(device.getAccountId().toString());
        devicetypeid.setText((device.getType()));
        if (device.getFaultTime() == null)
            faultytime.setText("");
        else
            faultytime.setText(String.valueOf(device.getFaultTime()));
        if (device.getLastUpdate() == null)
            lastupdate.setText("");
        else
            lastupdate.setText(String.valueOf(device.getLastUpdate()));


        LinearLayout linearLayout = layout.findViewById(R.id.device_item_test_shape);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), DeviceView.class);
//                intent.putExtra("device", device);
//                getContext().startActivity(intent);
//            }
//        });
        return layout;
    }

    private Filter devicesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<Devices> suggestions = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                suggestions.addAll(allDevices);
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Devices device : allDevices) {
                    if (device.getName().toLowerCase().contains(filterPattern))
                        suggestions.add(device);
                }
            }
            filterResults.values = suggestions;
            filterResults.count = suggestions.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Devices) resultValue).getName();
        }
    };

}
