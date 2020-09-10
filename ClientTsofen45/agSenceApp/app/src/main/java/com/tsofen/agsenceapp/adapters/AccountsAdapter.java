package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.activities.LoginActivity;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;

public class AccountsAdapter extends ArrayAdapter<User> implements Serializable {
    LayoutInflater inflater;

    public AccountsAdapter(Context context, int resource, User[] users) {
        super(context, resource, users);
        inflater = LayoutInflater.from(context);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View layout = this.inflater.inflate(R.layout.activity_account_status_news_shape, null);
        User users = getItem(position);
        TextView name = layout.findViewById(R.id.accountname);
        TextView amountofdevices = layout.findViewById((R.id.amountofdevices));
        TextView accountlastupdate = layout.findViewById((R.id.accountlastupdate));


        name.setText( LoginActivity.user.getUserName());
//        amountofdevices.setText((users.getId()));
        accountlastupdate.setText(String.valueOf(users.getUserType()));


        return layout;
    }
}
