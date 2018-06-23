package com.example.mkmkmk.capteursandroid;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class GPS extends AppCompatActivity implements SensorEventListener {


    int count =0;
    double latitude = 0.0;
    double longitude = 0.0;

    double lastLatitude = 0.0;
    double lastLongitude = 0.0;

    Location location;

    // Declaring a Location Manager
    protected LocationManager locationManager;

    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtLastLat;
    TextView txtLastLon;
    TextView lastUbication;
    TextView txtStep;
    Button btnDistance;
    int nombrePas =0;

    // The minimum distance to change updates in meters
    private static final long DISTANCE_METRES = 3; // 3 meters

    //Mis a jour apres un minute
    // The minimum time between updates in milliseconds
    private static final long TIME_UPDATE = 5000; // 5 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        txtLatitude = (TextView) findViewById(R.id.txt_Latitude);
        txtLongitude = (TextView) findViewById(R.id.txt_Longitude);
        txtLastLat = (TextView) findViewById(R.id.txtLastLatitude);
        txtLastLon = (TextView) findViewById(R.id.txtLastLongitude);
        txtStep = (TextView) findViewById(R.id.txtStepCounter);
        lastUbication = (TextView) findViewById(R.id.txtDistance);
        btnDistance = (Button) findViewById(R.id.btnDistance);

        txtLatitude.setText("Bonjour");
        txtLongitude.setText("le Monde ...");

        //startUbication();
        monUbication();


        SensorManager mSensorManager;
        Sensor stepSensor;

        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        stepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        mSensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        StringBuilder infoStep = new StringBuilder("STEP COUNTER ");
        infoStep.append("\r\n");
        infoStep.append("Value (Nombre des pas) "+sensorEvent.values[0]);

        txtStep.setText(infoStep);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void startUbication()
    {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //new AttemptUpdate().execute();
                        Log.i("Repeat", "This'll run 5 seconds later");
                        count++;
                        monUbication();
                    }
                });
            }
        }, 0, 5000);
    }

    private void monUbication() {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationListener locationListener = new LocationListener() {
            //S'il y a un change d'ubication il va faire un mis a jour
            @Override
            public void onLocationChanged(Location location) {
                updateUbication(location);

            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_UPDATE, DISTANCE_METRES, locationListener);

            if (locationManager != null) {

                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                updateUbication(location);

            }
    }

    private void updateUbication(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            txtLatitude.setText("Latitude =>  " + latitude);
            txtLongitude.setText("Longitude =>  " + longitude);

            if (lastLatitude == 0.0 || lastLongitude == 0.0)
            {
                lastLatitude = latitude;
                lastLongitude = longitude;

                txtLastLat.setText("Derniere latitude : "+lastLatitude);
                txtLastLon.setText("Derniere longitude : "+lastLongitude);
            }

            Toast.makeText(this, "Update Ubication", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDistance(View view)
    {
        //variables de type Location avec les coordonnes correspondant
        Location l = new Location("Location 1");
        l.setLatitude(latitude);
        l.setLongitude(longitude);

        Location l2 = new Location("Location 2");
        l2.setLatitude(lastLatitude);
        l2.setLongitude(lastLongitude);

        //on obtiens la distance
        double distanceM = l.distanceTo(l2);
        double distanceK = distanceM/1000;

        StringBuilder message = new StringBuilder();
        message.append(String.format("%.2f", distanceM)+"m");
        message.append("\r\n");
        message.append(String.format("%.2f", distanceK)+"Km");

        lastUbication.setText(message.toString());
    }


}


