package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tsofen.agsenceapp.R;

public class SearchBaseActivity extends AppBaseActivity {

    protected AutoCompleteTextView searchView;
    View contentView;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.activity_search_base, null, false);
        clear = contentView.findViewById(R.id.clear);
        clear.setVisibility(View.INVISIBLE);
    }



    public void clear(View view) {

        searchView.setText("");
        clear.setVisibility(View.GONE);
    }

    public void setTextWatcher(){
//        searchView = contentView.findViewById(R.id.search_text_view);
//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // do nothing
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence.length() != 0) {
//                    clear.setVisibility(View.VISIBLE);
//                } else {
//                    clear.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // do nothing
//            }
//        });
    }
}