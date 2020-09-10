package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UserMap userMap;
    ArrayList<LatLng> latLngList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        userMap = (UserMap) intent.getSerializableExtra("user_map");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int counter = 0;
        for (int i = 0; i < userMap.getPlaces().size(); i++) {
            LatLng latLng = new LatLng(userMap.getPlaces().get(i).getLatitude(), userMap.getPlaces().get(i).getLongitude());
            latLngList.add(latLng);
            builder.include(latLng);
            mMap.addMarker(new MarkerOptions().position(latLng).title(userMap.getPlaces().get(i).getTitle()).snippet(userMap.getPlaces().get(i).getSnippet()));
            counter++;
        }
        //add static places
        LatLng latLng = new LatLng(32.7582555, 35.0278015);
        builder.include(latLng);
        latLngList.add(latLng);
        mMap.addMarker(new MarkerOptions().position(latLng).title("University of Haifa").snippet("University"));
        latLng = new LatLng(32.7885608,35.0071756);
        builder.include(latLng);
        latLngList.add(latLng);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Grand Canyon").snippet("Shopping mall"));
        latLng = new LatLng(32.7793361,35.0165388);
        builder.include(latLng);
        latLngList.add(latLng);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Technion").snippet("University"));
        latLng = new LatLng(32.8278888,34.978133);
        builder.include(latLng);
        latLngList.add(latLng);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Rambam").snippet("Hospital"));
        counter += 4;
        if (counter > 0) {
            Intent intent = getIntent();
            if (intent.getBooleanExtra("flag", false)) {
                PolylineOptions poly = new PolylineOptions();
                LatLng latLng1 = latLngList.get(0);
                LatLng latLng2;
                for (int i = 1; i < latLngList.size(); i++) {
                    latLng2 = latLngList.get(i);
                    poly.add(latLng1, latLng2);
                    mMap.addPolyline(poly).setColor(Color.RED);
                    latLng1 = latLng2;
                }
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
        }
    }

}