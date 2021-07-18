package com.example.kospolijejember;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kospolijejember.Util.GetKost;
import com.example.kospolijejember.Util.GetKostByDistricts;
import com.example.kospolijejember.Util.GetLocation;
import com.example.kospolijejember.adapter.ResponseGetKost;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MapsKost extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    public static final int PERMISSIONS_REQUEST = 1;
    LatLng location_device;
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_kost);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        dropdown = findViewById(R.id.spinner1);

        String[] items = new String[]{"Lokasi Anda", "Kuta Utara", "Kuta Selatan", "Kuta", "Abiansemal", "Petang", "Mengwi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getKostArround();
                        break;
                    case 1:
                        getKostByDistricts("Kuta Utara");
                        break;
                    case 2:
                        getKostByDistricts("Kuta Selatan");
                        break;
                    case 3:
                        getKostByDistricts("Kuta");
                        break;
                    case 4:
                        getKostByDistricts("Abiansemal");
                        break;
                    case 5:
                        getKostByDistricts("Petang");
                        break;
                    case 6:
                        getKostByDistricts("Mengwi");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: no selected");
            }
        });

        permissionCheck();
    }

    private void getKostByDistricts(String districts_name){
        mMap.clear();
        new GetKostByDistricts(districts_name, new GetKostByDistricts.Listener() {
            @Override
            public void success(List<ResponseGetKost> response) {
                int kosts = response.size();
                Log.i(TAG, "success: "+kosts);
                if(kosts > 0){
                    Log.i(TAG, "success: "+response.get(0).getNamakos());
                    for(int i=0;i<kosts;i++){
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(response.get(i).getLatitude()), Double.parseDouble(response.get(i).getLongtitude()))).title(response.get(i).getNamakos()).snippet(response.get(i).getId()));
                    }
                    boundsLocation(new LatLng(Double.parseDouble(response.get(0).getLatitude()), Double.parseDouble(response.get(0).getLongtitude())),
                            new LatLng(Double.parseDouble(response.get(0).getLatitude()), Double.parseDouble(response.get(0).getLongtitude())));
                }else {
                    Toast.makeText(MapsKost.this, "Tidak ada kost di area "+districts_name, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failed(String msg) {
                Toast.makeText(MapsKost.this, "error : "+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        Log.d("HOME", "OnMapReady Triger");
        mMap = googleMap;
        mMap.setMaxZoomPreference(18);
        mMap.setOnMarkerClickListener(marker -> {
            Log.i(TAG, "onMarkerClick: marker "+ marker.getTitle());
            startActivity(new Intent(MapsKost.this, Detailkos_activity.class).putExtra("id", marker.getSnippet()));
            return false;
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());
        task.addOnSuccessListener(this, locationSettingsResponse -> {
            new GetLocation(MapsKost.this, (latitude, longitude) -> location_device = new LatLng(latitude, longitude));
            Log.i(TAG, "onMapReady: lokasi lat : "+location_device.latitude+" lng : "+location_device.longitude);
            getKostArround();
        });
        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                ResolvableApiException resolvable = (ResolvableApiException) e;
                try {
                    resolvable.startResolutionForResult(this, 51);
                } catch (IntentSender.SendIntentException e1) {
                    Log.d("HOME", "ERROR onMapReady");
                    e1.printStackTrace();
                }
            }
        });
    }

    private void getKostArround(){
        mMap.clear();
        int radius = 10;
        new GetLocation(MapsKost.this, (latitude, longitude) -> new GetKost(latitude, longitude, radius, new GetKost.Listener() {
            @Override
            public void success(List<ResponseGetKost> response) {
                int kosts = response.size();
                Log.i(TAG, "success: "+ kosts);
              // lokasi perangkat -> kost terjauh
                if(kosts > 0){
                    Log.i(TAG, "success: "+response.get(0).getNamakos());
                    for(int i=0;i<kosts;i++){
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(response.get(i).getLatitude()), Double.parseDouble(response.get(i).getLongtitude()))).title(response.get(i).getNamakos()).snippet(response.get(i).getId()));
                    }
                    boundsLocation(new LatLng(Double.parseDouble(response.get(0).getLatitude()), Double.parseDouble(response.get(0).getLongtitude())),
                            new LatLng(Double.parseDouble(response.get(0).getLatitude()), Double.parseDouble(response.get(0).getLongtitude())));
                }else {
                    Log.i(TAG, "success: tidak ada kost terdekat diradius "+radius+" km");
                    Toast.makeText(MapsKost.this, "tidak ada kost terdekat diradius "+radius+" km", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failed(String msg) {
                Toast.makeText(MapsKost.this, "error : "+msg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void boundsLocation(LatLng start, LatLng end){
        new GetLocation(MapsKost.this, (latitude, longitude) -> location_device = new LatLng(latitude, longitude));
        if(!(location_device == null)){
            LatLngBounds latLngBounds = new LatLngBounds(start, end);
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 13));
        }else {
            if(!isFinishing()){
                Log.e(TAG, "boundsLocation: lokasi tidak ditemukan");
            }
        }
    }
    private void permissionCheck() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm != null) {
            if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.d("HOME", "Permission needed");
            }
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                Log.d("HOME", "permission ok");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
            }
            new GetLocation(MapsKost.this, (latitude, longitude) -> location_device = new LatLng(latitude, longitude));
            FragmentManager myFM = getSupportFragmentManager();
            final SupportMapFragment mapFragment = (SupportMapFragment) myFM.findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(MapsKost.this);
            } else {
                Log.d("HOME", "mapFragment null");
            }
        }
    }
}