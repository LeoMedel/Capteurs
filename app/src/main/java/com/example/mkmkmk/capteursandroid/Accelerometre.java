package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometre extends Activity implements SensorEventListener{

    SensorManager sensorManager;
    // L'accéléromètre
    Sensor accelerometer;

    TextView acelX, acelY, acelZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        acelX = (TextView) findViewById(R.id.aceleroX);
        acelY = (TextView) findViewById(R.id.aceleroY);
        acelZ = (TextView) findViewById(R.id.aceleroZ);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float AcX, AcY, AcZ;

            AcX = sensorEvent.values[0];
            AcY = sensorEvent.values[1];
            AcZ = sensorEvent.values[2];

            acelX.setText("X : "+AcX);
            acelY.setText("Y : "+AcY);
            acelZ.setText("Z : "+AcZ);


            if (AcX < 9 && AcX > 3 )
            {
                acelX.setBackgroundColor(Color.RED);
                acelX.setText("Superieur X : "+AcX);
                acelX.setTextColor(Color.BLACK);
            }
            else if (AcX < 3 && AcX > -3)
            {
                acelX.setBackgroundColor(Color.BLACK);
                acelX.setText("Moyenne X : "+AcX);
                acelX.setTextColor(Color.WHITE);
            }
            else if (AcX < -3 && AcX > -9)
            {
                acelX.setBackgroundColor(Color.GREEN);
                acelX.setText("Inferieur X : "+AcX);
                acelX.setTextColor(Color.BLACK);
            }

            // VALUER "Y"
            if (AcY < 9 && AcY > 3 )
            {
                acelY.setBackgroundColor(Color.RED);
                acelY.setText("Superieur Y : "+AcY);
                acelY.setTextColor(Color.BLACK);
            }
            else if (AcY < 3 && AcY > -3)
            {
                acelY.setBackgroundColor(Color.BLACK);
                acelY.setText("Moyenne Y : "+AcY);
                acelY.setTextColor(Color.WHITE);
            }
            else if (AcY > -9 && AcY < -3)
            {
                acelY.setBackgroundColor(Color.GREEN);
                acelY.setText("Inferieur Y : "+AcY);
                acelY.setTextColor(Color.BLACK);
            }

            //VALEUR "Z"
            if (AcZ < 9 && AcZ > 3 )
            {
                acelZ.setBackgroundColor(Color.RED);
                acelZ.setText("Superieur Z : "+AcZ);
                acelZ.setTextColor(Color.BLACK);
            }
            else if (AcZ < 3 && AcZ > -3)
            {
                acelZ.setBackgroundColor(Color.BLACK);
                acelZ.setText("Moyenne Z : "+AcZ);
                acelZ.setTextColor(Color.WHITE);
            }
            else if (AcZ < -3 && AcZ > -9)
            {
                acelZ.setText("Inferieur Z : "+AcZ);
                acelZ.setBackgroundColor(Color.GREEN);

                acelZ.setTextColor(Color.BLACK);
            }




        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
