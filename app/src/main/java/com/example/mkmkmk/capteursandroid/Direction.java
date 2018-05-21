package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Direction extends Activity implements SensorEventListener {

    TextView txtDirection;
    ImageView direction;

    // Déclaration de l'attribut en tant qu'attribut de l'activité
    // Le sensor manager (gestionnaire de capteurs)
    SensorManager sensorManager;

    // L'accéléromètre
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_app);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtDirection = (TextView) findViewById(R.id.txtDirection);
        direction = (ImageView) findViewById(R.id.imgDirection);

        // Gérer les capteurs&#160;:
        // Instancier le gestionnaire des capteurs, le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Instancier l'accéléromètre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Faire d'autres trucs
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // unregister the sensor (désenregistrer le capteur)
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Récupérer les valeurs du capteur
        float x, y, z;
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            if (x > 2 && x < 9.7) {
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                txtDirection.setText("G A U C H E !!!");
                txtDirection.setTextColor(Color.BLUE);
                direction.setImageResource(R.drawable.gauche);
            } else if (x < -2 && x > -9.7) {
                getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                txtDirection.setText("D R O I T E !!!");
                txtDirection.setTextColor(Color.WHITE);
                direction.setImageResource(R.drawable.droite);
            } else if (y < -2 && y > -9.7) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                txtDirection.setText("B A S !!!");
                txtDirection.setTextColor(Color.WHITE);
                direction.setImageResource(R.drawable.bas);
            } else if (y > 2 && y < 9.7) {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                txtDirection.setText("H A U T !!!");
                txtDirection.setTextColor(Color.BLACK);
                direction.setImageResource(R.drawable.haute);
            } else {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                txtDirection.setText("P L A T !!!");
                txtDirection.setTextColor(Color.BLACK);
                direction.setImageResource(R.drawable.equis);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


        // Rien à faire la plupart du temps
    }
}
