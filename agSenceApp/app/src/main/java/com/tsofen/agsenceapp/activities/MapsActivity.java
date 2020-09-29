package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        ClusterManager.OnClusterItemClickListener<Place>,
        ClusterManager.OnClusterClickListener<Place>{

    private GoogleMap mMap;
    private UserMap userMap;
    private ClusterManager<Place> mClusterManager;
    private Renderer mRenderer;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();
    private ConstraintLayout mMapLayout;
    private AutoCompleteTextView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_with_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        userMap = (UserMap) getIntent().getExtras().getSerializable("user_map");


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.activity_device_status, null, false);

        // only if we are at map for all devices
            searchView = (AutoCompleteTextView) findViewById(R.id.map_search_text_view);
            searchView.setHint(R.string.search_device_hint);
            DeviceDataAdapter.getInstance().getAllDevices(0, 0, new DeviceDataRequestHandler() {
                @Override
                public void onDeviceDataLoaded(final List<Devices> devices) {
                    MapsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            searchView.setAdapter(new DevicesAdapter<Devices>(MapsActivity.this, devices));
                        }
                    });

                }
            });
            searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MapsActivity.this, DeviceView.class);// write here method that zooms at specific device when chosen. via lat/long maybe?
                    Devices device = (Devices) searchView.getAdapter().getItem(i);
                    intent.putExtra("device", device);
                    startActivity(intent);
                }
            });




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
            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
                }
            });
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
