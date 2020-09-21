package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.User;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        ClusterManager.OnClusterItemClickListener<Place>,
        ClusterManager.OnClusterClickListener<Place>{

    private GoogleMap mMap;
    private UserMap userMap;
    List<Devices> devices;
    private ClusterManager<Place> mClusterManager;
    private Renderer mRenderer;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        userMap = new UserMap();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        devices = (List<Devices>) getIntent().getSerializableExtra("devices");
        for (Devices device : devices) {
            userMap.addPlace(new Place(device.getName(), device.getLastUpdate().toString(), device.getLatitude(), device.getLogitude()));
        }
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
        setUpClusterer();
    }

    private void setUpClusterer() {
        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<Place>(this, mMap);
        mRenderer = new Renderer(this, mMap, mClusterManager);
        mClusterManager.setRenderer(mRenderer);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterClickListener(this);
        // Add cluster items (markers) to the cluster manager.
        addItems();
        // Move camera to bounded position
        mapBounds();
        if (getIntent().getBooleanExtra("flag", false)) {
            addPolylines();
        }
    }
    private void addItems() {
//        List<Devices> data = new ArrayList<>();
//        data.add(new Devices());
//        for(Devices device : devices)
//        {
//            userMap.addPlace(new Place(device.getLatitude(), device.getLogitude()));
//        }
        mClusterManager.addItems(userMap.getPlaces());
    }

    private void animateZoomIn(LatLng latLng) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) Math.floor(mMap.getCameraPosition().zoom + 3)), 800, null);
    }

    private void mapBounds() {
        int count = 0;
        for (ClusterItem item : mClusterManager.getAlgorithm().getItems()) {
            builder.include(item.getPosition());
            count++;
        }
        if (count > 0) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
        }
    }

    private void addPolylines() {
        if (!userMap.getPlaces().isEmpty()) {
            PolylineOptions poly = new PolylineOptions();
            LatLng latLng1 = userMap.getPlaces().get(0).getLocation();
            LatLng latLng2;
            for (int i = 1; i < userMap.getPlaces().size(); i++) {
                latLng2 = userMap.getPlaces().get(i).getLocation();
                poly.add(latLng1, latLng2);
                mMap.addPolyline(poly).setColor(Color.RED);
                latLng1 = latLng2;
            }
        }
    }

    @Override
    public boolean onClusterItemClick(Place item) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(item.getLocation(), 15));
        Marker marker = mRenderer.getMarker(item);
        marker.setTitle(item.getTitle());
        marker.setSnippet(item.getSnippet());
        marker.showInfoWindow();
        return true;
    }

    @Override
    public boolean onClusterClick(Cluster<Place> cluster) {
        animateZoomIn(cluster.getPosition());
        return true;
    }
/////////////////////////////////////////////////renderer class////////////////////////////////////////////////////
    static class Renderer extends DefaultClusterRenderer {

        public Renderer(Context context, GoogleMap map, ClusterManager<Place> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        public Marker getMarker(Cluster cluster) {
            return super.getMarker(cluster);
        }
    }
}
