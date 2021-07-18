package com.example.kospolijejember.Util;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import static android.content.ContentValues.TAG;

public class GetLocation {
    public interface Listener {
        void success(double latitude, double longitude);
    }
    public GetLocation(Context context, Listener up) {
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocation.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                Log.d("HOME", "lokasi --> " + location);
                up.success(location.getLatitude(), location.getLongitude());
            } else {
                Log.e(TAG, "GetLocation: null");
            }
        });

    }
}
