package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;

import com.tsofen.agsenceapp.entities.DeviceData;

import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        ClusterManager.OnClusterItemClickListener<Place>,
        ClusterManager.OnClusterClickListener<Place>{

    private GoogleMap mMap;
    private UserMap userMap;
    private ClusterManager<Place> mClusterManager;
    private Renderer mRenderer;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();

    private ArrayList<LatLng> listPoints;
    private ArrayList<LatLng> listPointsPoly;
    private MarkerOptions markerOptions;
    private MarkerOptions initialMarker;
    private LatLng initialMarkerLatlng;
    private Polygon polygon;
    private Button clear;
    private AutoCompleteTextView searchView;
    private int opCode;

    //opCode:
    // 1. Map activity with search bar. Organizes markers in clusters
    // 2. Map activity with timeline of device - shows polyline between them
    // 3. Map with last device location to define geofence


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        opCode = getIntent().getIntExtra("opcode", 1);
        switch (opCode) {
            case 1:
                setContentView(R.layout.activity_maps_with_search);
                clear = (Button) findViewById(R.id.clear_button);
                searchView = (AutoCompleteTextView) findViewById(R.id.map_search_text_view);
                searchView.setHint(R.string.search_device_hint);
                String filterStr = getIntent().getStringExtra("filter");
                if(filterStr == null){
                    DeviceDataAdapter.getInstance().getAllDevices(0, 0,false, new DeviceDataRequestHandler() {
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
                }else {
                    if (filterStr.equals("healthy")) {
                        DeviceDataAdapter.getInstance().getHealthyDevices(new DeviceDataRequestHandler() {
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
                    } else if (filterStr.equals("faulty")) {
                        DeviceDataAdapter.getInstance().getFaultyDevices(new DeviceDataRequestHandler() {
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
                    } else {
                        DeviceDataAdapter.getInstance().getAllDevices(0, 0, false, new DeviceDataRequestHandler() {
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
                    }
                }

                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Devices device = (Devices) searchView.getAdapter().getItem(i);
                        builder = new LatLngBounds.Builder();
                        builder.include(new LatLng(Float.parseFloat(device.getLatitude()), Float.parseFloat(device.getLogitude())));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
                    }
                });
                break;

            case 2:
                setContentView(R.layout.activity_maps);
                break;

            case 3:
                setContentView(R.layout.activity_maps_geofence);
                break;
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        userMap = (UserMap) getIntent().getSerializableExtra("user_map");
        listPoints = new ArrayList<>();
        listPointsPoly = new ArrayList<>();

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
        switch (opCode) {
            case 1:
                setUpClusterer();
                break;

            case 2:
                setUpClusterer();
                break;

            case 3:
                if(userMap != null && userMap.getPlaces() != null && userMap.getPlaces().get(0) != null) {
                    initialMarker = new MarkerOptions();
                    initialMarker.position(userMap.getPlaces().get(0).getLocation());
                    initialMarker.title(userMap.getPlaces().get(0).getTitle());
                    mMap.addMarker(initialMarker);
                    initialMarkerLatlng = new LatLng(userMap.getPlaces().get(0).getLocation().latitude, userMap.getPlaces().get(0).getLocation().longitude);
                    builder = new LatLngBounds.Builder();
                    builder.include(userMap.getPlaces().get(0).getLocation());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
                }
        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (opCode == 3) {
                    if (listPoints.size() == 2) {
                        listPoints.clear();
                        listPointsPoly.clear();
                        mMap.clear();
                        if (initialMarker != null) {
                            mMap.addMarker(initialMarker);
                        }
                        if (polygon != null) {
                            polygon.remove();
                        }
                    }
                    listPoints.add(latLng);
                    markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    mMap.addMarker(markerOptions);
                    if(listPoints.size() == 2) {
                        mMap.clear();
                        if (initialMarker != null) {
                            mMap.addMarker(initialMarker);
                        }
                        Double top = Double.max(listPoints.get(0).latitude, listPoints.get(1).latitude);
                        Double bottom = Double.min(listPoints.get(0).latitude, listPoints.get(1).latitude);
                        Double right = Double.max(listPoints.get(0).longitude, listPoints.get(1).longitude);
                        Double left = Double.min(listPoints.get(0).longitude, listPoints.get(1).longitude);
                        if (initialMarker != null && (initialMarkerLatlng.latitude < bottom || initialMarkerLatlng.latitude > top ||
                                initialMarkerLatlng.longitude < left || initialMarkerLatlng.longitude > right)) {
                            Toast.makeText(MapsActivity.this, "Device must be inside defined area", Toast.LENGTH_SHORT).show();
                            listPoints.clear();
                            listPointsPoly.clear();
                        } else {
                            builder = new LatLngBounds.Builder();
                            builder.include(listPoints.get(0));
                            builder.include(listPoints.get(1));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
                            listPointsPoly.add(new LatLng(top, left));
                            listPointsPoly.add(new LatLng(top, right));
                            listPointsPoly.add(new LatLng(bottom, right));
                            listPointsPoly.add(new LatLng(bottom, left));
                            PolygonOptions polygonOptions = new PolygonOptions().addAll(listPointsPoly);
                            polygonOptions.strokeColor(Color.RED);
                            polygonOptions.strokeWidth(6);
                            polygon= mMap.addPolygon(polygonOptions);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void clearText(View view) {

        searchView.setText("");

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
        if (opCode == 2) {
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
            Polyline polyline;
            LatLng latLng1 = userMap.getPlaces().get(0).getLocation();
            LatLng latLng2;
            for (int i = 1; i < userMap.getPlaces().size(); i++) {
                latLng2 = userMap.getPlaces().get(i).getLocation();
                poly.add(latLng1, latLng2);
                latLng1 = latLng2;
            }
            polyline = mMap.addPolyline(poly);
            polyline.setEndCap(new CustomCap(bitmapDescriptorFromVector(this, R.drawable.ic_arrow), 10));
        }
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
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

    public void acceptButton(View view) {
        if(listPoints.size() != 2) {
            Toast.makeText(this, "Invalid geofence, try again\n (long press to set marker)", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("pointA", new Place((float) listPoints.get(0).latitude, (float) listPoints.get(0).longitude));
            intent.putExtra("pointB", new Place((float) listPoints.get(1).latitude, (float) listPoints.get(1).longitude));
            setResult(RESULT_OK,intent);
            finish();
        }
    }

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
