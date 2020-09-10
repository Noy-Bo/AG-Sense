package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.tsofen.agsenceapp.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    public static final String TAG = "SliderAdapter:";
    //Arrays for slider-layouts..

    public SliderAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        Log.d(TAG,"getCount()");
        return 3;
    }


    //Determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int).
    // This method is required for a PagerAdapter to function properly.
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.d(TAG,"isViewFromObject()");
        return view==(LinearLayout)object;
    }



    //create the page for the given position. The adapter is responsible for adding the view to the container given here,
    //although it only must ensure this is done by the time it returns from finishUpdate(ViewGroup).
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG,"instantiateItem()");
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if(position==0) {
            view = layoutInflater.inflate(R.layout.activity_device_view_slider1, container, false);
        }
        if(position == 1) {
            view = layoutInflater.inflate(R.layout.activity_device_view_slider2, container, false);
        }
        if(position == 2) {
            view = layoutInflater.inflate(R.layout.activity_device_view_slider3, container, false);
        }
        if(position==3){
            view = layoutInflater.inflate(R.layout.activity_device_view_slider4, container, false);
        }
        if(position==4){
            view = layoutInflater.inflate(R.layout.activity_device_view_slider5, container, false);
        }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG,"destroyItem()");
        container.removeView((LinearLayout)object);
    }

}
