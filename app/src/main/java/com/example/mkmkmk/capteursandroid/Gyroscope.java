package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Gyroscope extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor gyroscopeSensor;

    TextView infX, infY, infZ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        infX = (TextView) findViewById(R.id.txtX);
        infY = (TextView) findViewById(R.id.txtY);
        infZ = (TextView) findViewById(R.id.txtZ);



        //getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);



        if (gyroscopeSensor == null)
        {
            Toast.makeText(getApplicationContext(), "Gyroscope not disponible", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {

        sensorManager.unregisterListener(this, gyroscopeSensor);
        super.onPause();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            infX.setText("X :  " + x);
            infY.setText("Y :  " + y);
            infZ.setText("Z :  " + z);


            if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
