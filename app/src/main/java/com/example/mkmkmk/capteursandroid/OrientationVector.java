package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OrientationVector extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor rotationVectorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_vector);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);

        if (rotationVectorSensor == null)
        {
            Toast.makeText(getApplicationContext(), "ROTATION VECTOR not disponible", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] rotationMatrix = new float[16];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);
        // Remap coordinate system
        float[] remappedRotationMatrix = new float[16];
        SensorManager.remapCoordinateSystem(rotationMatrix,
                SensorManager.AXIS_X,
                SensorManager.AXIS_Z,
                remappedRotationMatrix);

        // Convert to orientations
        float[] orientations = new float[3];
        SensorManager.getOrientation(remappedRotationMatrix, orientations);

        for(int i = 0; i < 3; i++) {
            orientations[i] = (float)(Math.toDegrees(orientations[i]));
        }

        if(orientations[2] > 45) {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        } else if(orientations[2] < -45) {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        } else if(Math.abs(orientations[2]) < 10) {
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
