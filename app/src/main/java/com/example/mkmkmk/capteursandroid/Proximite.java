package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Proximite extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor proximitySensor;

    TextView txtproximite;
    ImageView proximite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximite);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        proximite = (ImageView) findViewById(R.id.imgProximite);
        txtproximite = (TextView) findViewById(R.id.txtProximite);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(proximitySensor == null) {
            Toast.makeText(getApplicationContext(), "Proximity sensor not available.", Toast.LENGTH_LONG).show();
            finish(); // Close app
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                proximitySensor, 5 * 1000 * 1000);
    }


    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
            // Detected something nearby
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            proximite.setImageResource(R.drawable.homerproche);
            txtproximite.setTextColor(Color.BLACK);
            txtproximite.setText("Près... !!!");
        } else {
            // Nothing is nearby
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            proximite.setImageResource(R.drawable.homerloin);
            txtproximite.setTextColor(Color.WHITE);
            txtproximite.setText("Loin !!!");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
