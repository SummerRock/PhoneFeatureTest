package com.example.yanxia.phonefeaturetest.testactivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanxia.phonefeaturetest.R;

import static com.example.yanxia.phonefeaturetest.R.id.fab;
import static com.example.yanxia.phonefeaturetest.R.id.phonetvgps;
import static com.example.yanxia.phonefeaturetest.R.id.textView;

public class GpsTestActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private TextView textGPSView;
    private double latitude = 0;
    private double longitude = 0;
    private String srtLocation = null;
    private LocationListener locationListener;
    private Location lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        textGPSView = (TextView) findViewById(R.id.phonetvgps);
        LocationProvider gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        LocationProvider netProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
        locationManager.getAllProviders();
        Log.d("GpsTestActivity","available location provider = "+locationManager.getAllProviders());
        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    srtLocation = "latitude = " + latitude + " longitude = " + longitude;
                } else {
                    srtLocation = "location is null!";
                }
                textGPSView.setText(srtLocation);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            try{
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, locationListener);
            }catch (SecurityException se){
                Toast.makeText(this, "permission error!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Open location settings", Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(i);
        }

        /*if (ContextCompat.checkSelfPermission(GpsTestActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GpsTestActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try{
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                    }catch (SecurityException se){
                        Toast.makeText(this, "permission error!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void updataGpsView() {
        if (lastKnownLocation != null) {
            latitude = lastKnownLocation.getLatitude();
            longitude = lastKnownLocation.getLongitude();
            srtLocation = "latitude = " + latitude + " longitude = " + longitude;
        } else {
            srtLocation = "lastknownLocation is null!";
        }
        textGPSView.setText(srtLocation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Remove the listener you previously added
        if (ContextCompat.checkSelfPermission(GpsTestActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GpsTestActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.removeUpdates(locationListener);
        }
    }
}
