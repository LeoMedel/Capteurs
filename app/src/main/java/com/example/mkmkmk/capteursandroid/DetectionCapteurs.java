package com.example.mkmkmk.capteursandroid;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetectionCapteurs extends AppCompatActivity {

    private SensorManager sensorManager = null;

    private Sensor sensorTemperature = null;
    private Sensor sensorProximite = null;
    private Sensor sensorLumiere = null;
    private Sensor sensorAccelerometre = null;
    private Sensor sensorOrientation = null;
    private Sensor sensorGeomagnetic = null;
    private Sensor sensorGameRotation = null;
    private Sensor sensorAceleroUncal = null;
    private Sensor sensorAmbientTemperature = null;
    private Sensor sensorDevPrivate = null;
    private Sensor sensorGravity = null;
    private Sensor sensorGyroscope = null;
    private Sensor sensorGyroscopeUncal = null;
    private Sensor sensorHeart = null;
    private Sensor sensorHeartRat = null;
    private Sensor sensorLinearAc = null;

    private Sensor sensorX = null;

    TextView txtInfPresence, txtInfAbsence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_capteurs);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtInfPresence = (TextView) findViewById(R.id.txtPresence);
        txtInfAbsence = (TextView) findViewById(R.id.txtAbsence);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorProximite = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        sensorLumiere = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAccelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorGeomagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        sensorGameRotation = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        sensorAceleroUncal = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED);
        sensorAmbientTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorDevPrivate = sensorManager.getDefaultSensor(Sensor.TYPE_DEVICE_PRIVATE_BASE);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorGyroscopeUncal = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        sensorHeart = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        sensorHeartRat = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        sensorLinearAc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        setCapteursPresences();
        setCapteursAbsences();
    }


    private void setCapteursAbsences()
    {
        StringBuffer sensorAbs = new StringBuffer();

        if(sensorLinearAc == null){

            sensorAbs.append("NAME : TYPE_LINEAR_ACCELERATION"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorHeartRat == null){

            sensorAbs.append("NAME : TYPE_HEART_RATE"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorHeart == null){

            sensorAbs.append("NAME : TYPE_HEART_BEAT"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorGyroscopeUncal == null){

            sensorAbs.append("NAME : TYPE_GYROSCOPE_UNCALIBRATED"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorGyroscope == null){

            sensorAbs.append("NAME : TYPE_GYROSCOPE"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorGravity == null){

            sensorAbs.append("NAME : TYPE_GRAVITY"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorDevPrivate == null){

            sensorAbs.append("NAME : TYPE_DEVICE_PRIVATE_BASE"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorAmbientTemperature == null){

            sensorAbs.append("NAME : TYPE_AMBIENT_TEMPERATURE"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorAceleroUncal == null){

            sensorAbs.append("NAME : TYPE_ACCELEROMETER_UNCALIBRATED"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorGameRotation == null){

            sensorAbs.append("NAME : TYPE_GAME_ROTATION_VECTOR"+ "\r\n");
            sensorAbs.append("\r\n");
        }


        if(sensorGeomagnetic == null){

            sensorAbs.append("NAME : TYPE_GEOMAGNETIC_ROTATION_VECTOR"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorAccelerometre == null){

            sensorAbs.append("NAME : TYPE_ACCELEROMETER"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorProximite == null){

            sensorAbs.append("NAME : TYPE_PROXIMITY"+ "\r\n");
            sensorAbs.append("\r\n");
        }
        if(sensorLumiere == null){
            sensorAbs.append("NAME : TYPE_LIGHT"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorTemperature == null){
            sensorAbs.append("NAME : TYPE_TEMPERATURE"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        if(sensorOrientation == null){
            sensorAbs.append("NAME : TYPE_ORIENTATION"+ "\r\n");
            sensorAbs.append("\r\n");
        }

        txtInfAbsence.setText(sensorAbs.toString());
    }

    private void setCapteursPresences()
    {
        // Trouver tous les capteurs de l'appareil :
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // La chaîne descriptive de chaque capteur
        StringBuffer sensorDesc = new StringBuffer();

        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensors) {
            sensorDesc.append("\tNAME : " + sensor.getName() + "\r\n");
            sensorDesc.append("\r\n");
        }

        txtInfPresence.setText(sensorDesc.toString());
    }


}
