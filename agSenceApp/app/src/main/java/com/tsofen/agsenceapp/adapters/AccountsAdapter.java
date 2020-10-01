package com.tsofen.agsenceapp.adapters;

import android.annotation.SuppressLint;
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
import com.tsofen.agsenceapp.activities.AccountDashboardActivity;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter<A extends User> extends ArrayAdapter<Account> implements Serializable {
    LayoutInflater inflater;
    ArrayList<Account> accounts;

    public AccountsAdapter(Context context, List<Account> users) {
        super(context, 0, users);
        inflater = LayoutInflater.from(context);
        accounts = new ArrayList<>(users);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return accountsFilter;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View layout = this.inflater.inflate(R.layout.activity_account_status_news_shape, null);
        final Account account = (Account) getItem(position);
        TextView accountName = layout.findViewById(R.id.account_name);
        TextView username = layout.findViewById(R.id.account_name);
        TextView amountofdevices = layout.findViewById((R.id.amountofdevices));
        TextView accountLastUpdate = layout.findViewById((R.id.accountlastupdate));
        ImageView imageView = layout.findViewById(R.id.account_status_imageview);
        assert account != null;
        if (account.getFaultyAccount())
            imageView.setImageResource(R.drawable.faulty_accounts_icon);
        else
            imageView.setImageResource(R.drawable.healthy_accounts_icon);

        username.setText(account.getUsername());
//        accountName.setText("");
        LinearLayout linearLayout = layout.findViewById(R.id.account_item_shape);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AccountDashboardActivity.class);
                intent.putExtra("account", account);
                getContext().startActivity(intent);
            }
        });

        amountofdevices.setText(String.format("%d faulty devices out of %d", account.getFaultyDevices(), account.getNumberOfDevices()));
        // accountLastUpdate.setText("");
        return layout;
    }

    private Filter accountsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<Account> suggestions = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
                suggestions.addAll(accounts);
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Account account : accounts) {
                    if (account.getUsername().toLowerCase().contains(filterPattern))
                        suggestions.add(account);
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
            return ((Account) resultValue).getUsername();
        }
    };
}
