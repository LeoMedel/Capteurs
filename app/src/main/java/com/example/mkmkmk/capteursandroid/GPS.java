package com.example.mkmkmk.capteursandroid;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GPS extends AppCompatActivity {

    double latitude = 0.0;
    double longitude = 0.0;
    double lastLatitude = 0.0;
    double lastLongitude = 0.0;
    float distance = 0.0f;

    Location location;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isGPSTrackingEnabled = false;
    private String provider_info;

    // Declaring a Location Manager
    protected LocationManager locationManager;


    TextView txtLatitude;
    TextView txtLongitude;
    TextView lastUbication;

    // The minimum distance to change updates in meters
    private static final long DISTANCE_METRES = 10; // 10 meters

    //Mis a jour apres un minute
    // The minimum time between updates in milliseconds
    private static final long TIME_UPDATE = 15000; // 15 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);


        txtLatitude = (TextView) findViewById(R.id.txt_Latitude);
        txtLongitude = (TextView) findViewById(R.id.txt_Longitude);
        lastUbication = (TextView) findViewById(R.id.txtDistance);

        txtLatitude.setText("Bonjour");
        txtLongitude.setText("le Monde ...");

        monUbication();
    }


    private void monUbication() {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnabled) {
            provider_info = LocationManager.GPS_PROVIDER;
        } else if (isNetworkEnabled) {
            provider_info = LocationManager.NETWORK_PROVIDER;
        }

        if (!provider_info.isEmpty()) {
            locationManager.requestLocationUpdates(provider_info, TIME_UPDATE, DISTANCE_METRES, locationListener);

            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(provider_info);
                updateUbication(location);
                Toast.makeText(this, "Update Ubication", Toast.LENGTH_SHORT).show();
                lastUbication();
            }
        }
    }

    private void updateUbication(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            txtLatitude.setText("Latitude =>  " + latitude);
            txtLongitude.setText("Longitude =>  " + longitude);
            Log.i("GPS Longitude", "Longitude => " + longitude);
            Log.i("GPS Longitude", "Latitude => " + latitude);

        }
    }

    LocationListener locationListener = new LocationListener() {
        //S'il y a un change d'ubication il va faire un mis a jour
        @Override
        public void onLocationChanged(Location location) {
            lastUbication();
            updateUbication(location);

            longitude = location.getLongitude();
            latitude = location.getLatitude();

            txtLatitude.setText("Latitude =>  " + latitude);
            txtLongitude.setText("Longitude =>  " + longitude);


        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void lastUbication() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        lastLatitude = location.getAltitude();
        lastLongitude = location.getLongitude();


        Location locationA = new Location("punto A");

        locationA.setLatitude(lastLatitude);
        locationA.setLongitude(lastLongitude);

        Location locationB = new Location("punto B");

        locationB.setLatitude(latitude);
        locationB.setLongitude(longitude);

        distance += locationA.distanceTo(locationB);

        lastUbication.setText("Distance (Mètres) parcouru : "+distance);
    }
}
