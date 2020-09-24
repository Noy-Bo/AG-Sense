package com.tsofen.agsenceapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.DeviceData;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    private TextView textView11;//holds the first textView field (title)
    private TextView textView12;//holds the first textView field (body)
    private TextView textView21;
    private TextView textView22;
    private TextView textView31;
    private TextView textView32;
    private TextView textView41;
    private TextView textView42;
    private TextView textView51;
    private TextView textView52;
    private View view;
    public static final String TAG = "SliderAdapter:";

    private DeviceData deviceData;
    //Arrays for slider-layouts..


    public SliderAdapter(Context context, DeviceData deviceData){
        this.context=context;
        this.deviceData = deviceData;
    }

    @Override
    public int getCount() {
        Log.d(TAG,"getCount()");
        return 10;
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
        view = layoutInflater.inflate(R.layout.activity_device_view_slider, container, false);

        textView11 = view.findViewById(R.id.device_view_text11);
        textView12 = view.findViewById(R.id.device_view_text12);
        textView21 = view.findViewById(R.id.device_view_text21);
        textView22 = view.findViewById(R.id.device_view_text22);
        textView31 = view.findViewById(R.id.device_view_text31);
        textView32 = view.findViewById(R.id.device_view_text32);
        textView41 = view.findViewById(R.id.device_view_text41);
        textView42 = view.findViewById(R.id.device_view_text42);
        textView51 = view.findViewById(R.id.device_view_text51);
        textView52 = view.findViewById(R.id.device_view_text52);

        if(position==0) { //incase the 1st page in the slider...
            addPageData_1();
        }
        else if(position == 1) {
            addPageData_2();
        }
        else if(position == 2) {
            addPageData_3();
        }
        else if(position==3){
            addPageData_4();
        }
        else if(position==4){
            addPageData_5();
        }
        else if(position==5){
            addPageData_6();
        }
        else if(position==6){
            addPageData_7();
        }
        else if(position==7){
            addPageData_8();
        }
        else if(position==8){
            addPageData_9();
        }
        else if(position==9){
            addPageData_10();
        }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG,"destroyItem()");
        container.removeView((LinearLayout)object);
    }

    public void addPageData_1(){
        textView11.setText("IMEI");
        textView12.setText(parseField(deviceData.getImei()));
        textView21.setText("GPS Type");
        textView22.setText(parseField(deviceData.getGpsType()));
        textView31.setText("GPS Valid");
        textView32.setText(parseField(deviceData.getGpsValid()));
        textView41.setText("Latitude Indicator");
        textView42.setText(parseField(deviceData.getLatitudeIndecator()));
        textView51.setText("Longtitude Indicator");
        textView52.setText(parseField(deviceData.getLonIndicator()));
    }

    public void addPageData_2(){
        textView11.setText("Speed");
        textView12.setText(parseField(deviceData.getSpeed()));
        textView21.setText("Orientation");
        textView22.setText(parseField(deviceData.getOrientation()));
        textView31.setText("Altitude");
        textView32.setText(parseField(deviceData.getAltitude()));
        textView41.setText("Mileage");
        textView42.setText(parseField(deviceData.getMileage()));
        textView51.setText("Satellites");
        textView52.setText(parseField(deviceData.getSatelites()));
    }

    public void addPageData_3(){
        textView11.setText("hdop");
        textView12.setText(parseField(deviceData.getHdop()));
        textView21.setText("gsm Signal");
        textView22.setText(parseField(deviceData.getGsmSignal()));
        textView31.setText("Input 1 Activated");
        textView32.setText(parseField(deviceData.getInput1Activated()));
        textView41.setText("Switch 1 Activated");
        textView42.setText(parseField(deviceData.getSwitch1Activated()));
        textView51.setText("Switch 2 Activated");
        textView52.setText(parseField(deviceData.getSwitch2Activated()));
    }

    public void addPageData_4(){
        textView11.setText("sesmo Activated");
        textView12.setText(parseField(deviceData.getSesmoActivated()));
        textView21.setText("Custom Input bit 0");
        textView22.setText(parseField(deviceData.getCustomInputBit0()));
        textView31.setText("Custom Input bit 1");
        textView32.setText(parseField(deviceData.getCustomInputBit1()));
        textView41.setText("Custom Input bit 2");
        textView42.setText(parseField(deviceData.getCustomInputBit2()));
        textView51.setText("Custom Input bit 3");
        textView52.setText(parseField(deviceData.getCustomInputBit3()));
    }

    public void addPageData_5(){
        textView11.setText("Power Cut");
        textView12.setText(parseField(deviceData.getPowerCut()));
        textView21.setText("Fuel Cut");
        textView22.setText(parseField(deviceData.getFuelCut()));
        textView31.setText("Door Locked");
        textView32.setText(parseField(deviceData.getDoorLocked()));
        textView41.setText("Door Unlocked");
        textView42.setText(parseField(deviceData.getDoorUnlocked()));
        textView51.setText("Move Alert Active");
        textView52.setText(parseField(deviceData.getMoveAlertActive()));
    }

    public void addPageData_6(){
        textView11.setText("Speeding Alert Active");
        textView12.setText(parseField(deviceData.getSpeedingAlterActive()));
        textView21.setText("Out Of Geo Fence Alert Active");
        textView22.setText(parseField(deviceData.getOutOfGeoFenceAlertActive()));
        textView31.setText("Into Geo Fence Alert Active");
        textView32.setText(parseField(deviceData.getIntoGeoFenceAlertActive()));
        textView41.setText("Custom Alert Bit 0");
        textView42.setText(parseField(deviceData.getCustomAlertBit0()));
        textView51.setText("Custom Alert Bit 1");
        textView52.setText(parseField(deviceData.getCustomAlertBit1()));
    }

    public void addPageData_7(){
        textView11.setText("Custom Alert Bit 2");
        textView12.setText(parseField(deviceData.getCustomAlertBit2()));
        textView21.setText("Custom Alert Bit 3");
        textView22.setText(parseField(deviceData.getCustomInputBit3()));
        textView31.setText("Working Mode 1");
        textView32.setText(parseField(deviceData.getWorkingMode1()));
        textView41.setText("Working Mode 2");
        textView42.setText(parseField(deviceData.getWorkingMode2()));
        textView51.setText("Working Mode 3");
        textView52.setText(parseField(deviceData.getWorkingMode3()));
    }

    public void addPageData_8(){
        textView11.setText("Working Mode 4");
        textView12.setText(parseField(deviceData.getWorkingMode4()));
        textView21.setText("Harsh Brake");
        textView22.setText(parseField(deviceData.getHarshBrake()));
        textView31.setText("Harsh Accelerate");
        textView32.setText(parseField(deviceData.getHarshAccelerate()));
        textView41.setText("Harsh Turn Right");
        textView42.setText(parseField(deviceData.getHarshTurnRight()));
        textView51.setText("Harsh Turn Left");
        textView52.setText(parseField(deviceData.getHarshTurnLeft()));
    }

    public void addPageData_9(){
        textView11.setText("Power");
        textView12.setText(parseField(deviceData.getPowerCut()));
        textView21.setText("Temperature External");
        textView22.setText(parseField(deviceData.getTemperatureExternal()));
        textView31.setText("Temperature Internal");
        textView32.setText(parseField(deviceData.getTemperatureInsideDevice()));
        textView41.setText("Fuel Voltage");
        textView42.setText(parseField(deviceData.getFuelVoltage()));
        textView51.setText("Humidity");
        textView52.setText(parseField(deviceData.getHumidity()));
    }

    public void addPageData_10(){
        textView11.setText("Analog 1");
        textView12.setText(parseField(deviceData.getAnalog1()));
        textView21.setText("Analog 2");
        textView22.setText(parseField(deviceData.getAnalog2()));
        textView31.setVisibility(View.INVISIBLE);
        textView32.setVisibility(View.INVISIBLE);
        textView41.setVisibility(View.INVISIBLE);
        textView42.setVisibility(View.INVISIBLE);
        textView51.setVisibility(View.INVISIBLE);
        textView52.setVisibility(View.INVISIBLE);
        view.findViewById(R.id.device_view__slider_grid3).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.device_view__slider_grid4).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.device_view__slider_grid5).setVisibility(View.INVISIBLE);
    }

    public String parseField(Object field){ //To parse nulls and etc, to Strings..
        if(field==null){
            return "-------";
        }
        else if(field instanceof Integer || field instanceof  String || field instanceof Long || field instanceof Boolean){
            return field.toString();
        }
        else{
            return "-------";
        }
    }

}
