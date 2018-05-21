package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Lumiere extends Activity implements SensorEventListener {

    TextView txtInfoLumiere;
    // Valeur courante de la lumière
    float l;

    // Le sensor manager
    SensorManager sensorManager;

    // Le capteur de lumière
    Sensor light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lumiere);

        txtInfoLumiere = (TextView) findViewById(R.id.txtLumiere);
        // Instancier le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instancier le capteur de lumière
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }


    @Override
    protected void onPause() {
        // désenregistrer notre écoute du capteur
        sensorManager.unregisterListener(this, light);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // enregistrer notre écoute du capteur
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Mettre à jour uniquement dans le cas de notre capteur
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            // La valeur de la lumière
            l = sensorEvent.values[0];

            //Toast.makeText(getApplicationContext(), ""+l, Toast.LENGTH_LONG).show();
            txtInfoLumiere.setText("Lumiere : "+l);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Faîtes quelque chose ou pas&#8230;
    }
}
