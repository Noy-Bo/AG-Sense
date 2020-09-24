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
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter extends ArrayAdapter<Account> implements Serializable {
    LayoutInflater inflater;

    public AccountsAdapter(Context context, int resource, List<Account> users) {
        super(context, 0, users);
        inflater = LayoutInflater.from(context);
    }



    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View layout = this.inflater.inflate(R.layout.activity_account_status_news_shape, null);
        final Account account = (Account) getItem(position);
        TextView name = layout.findViewById(R.id.accountname);
        TextView amountofdevices = layout.findViewById((R.id.amountofdevices));
        TextView accountlastupdate = layout.findViewById((R.id.accountlastupdate));
        ImageView imageView = layout.findViewById(R.id.account_status_imageview);
        imageView.setImageResource(R.drawable.faulty_accounts_icon);

        name.setText(account.getUsername());

        LinearLayout linearLayout = layout.findViewById(R.id.AccountItemShape);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AccountDashboardActivity.class);
                intent.putExtra("account",account);
                getContext().startActivity(intent);
            }
        });

       // amountofdevices.setText(account.);
        //name.setText(account.getUsername());
        return layout;
    }


}
