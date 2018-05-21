package com.example.mkmkmk.capteursandroid;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Capteurs extends AppCompatActivity {

    // The sensor manager
    SensorManager sensorManager;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capteurs);

        // Instanicer le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        txtInfo = (TextView) findViewById(R.id.txtInfo);

        // Faire la liste des capteurs de l'appareil
        listSensor();
    }

    /**
     * Lister les capteurs existant
     *
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */
    private void listSensor() {

        StringBuffer sensorDisponibilite = new StringBuffer();
        // Trouver tous les capteurs de l'appareil :
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // La chaîne descriptive de chaque capteur
        StringBuffer sensorDesc = new StringBuffer();
        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensors) {
            sensorDesc.append("NEW SENSOR DETECTED: \r\n");
            sensorDesc.append("\tNAME : " + sensor.getName() + "\r\n");
            sensorDesc.append("\tTYPE: " + getType(sensor.getType()) + "\r\n");
            sensorDesc.append("\tVERSION: " + sensor.getVersion() + "\r\n");
            //sensorDesc.append("\tRESOLUTION (in the sensor unit) : " + sensor.getResolution() + "\r\n");
            //sensorDesc.append("\tPOWER IN MA USED BY THIS SENSOR WHILE IN USE " + sensor.getPower() + "\r\n");
            //sensorDesc.append("\tVENDOR : " + sensor.getVendor() + "\r\n");
            //sensorDesc.append("\tMAXIMUM RANGE OF THE SENSOR IN THE SENSOR's UNIT." + sensor.getMaximumRange() + "\r\n");
            //sensorDesc.append("\tMINIMUM DELAY ALLOWED BETWEEN TWO EVENTS IN MICROSECOND" + " OR ZERO IF THIS SENSOR ONLY RETURNS A VALUE WHEN THE DATA IT'S MEASURING CHANGES" + sensor.getMinDelay() + "\r\n");
            sensorDesc.append("\r\n");
            sensorDesc.append("\r\n");

        }

        // Pour trouver un capteur spécifique&#160;:
        Sensor gyroscopeDefault = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Pour trouver tous les capteurs d'un type fixé :
        List<Sensor> gyroscopes = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);

        txtInfo.setText(sensorDesc);
    }



    private String getType(int type) {
        String strType;
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                strType = "TYPE_ACCELEROMETER";
                break;
            case Sensor.TYPE_GRAVITY:
                strType = "TYPE_GRAVITY";
                break;
            case Sensor.TYPE_GYROSCOPE:
                strType = "TYPE_GYROSCOPE";
                break;
            case Sensor.TYPE_LIGHT:
                strType = "TYPE_LIGHT";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                strType = "TYPE_LINEAR_ACCELERATION";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                strType = "TYPE_MAGNETIC_FIELD";
                break;
            case Sensor.TYPE_ORIENTATION:
                strType = "TYPE_ORIENTATION";
                break;
            case Sensor.TYPE_PRESSURE:
                strType = "TYPE_PRESSURE";
                break;
            case Sensor.TYPE_PROXIMITY:
                strType = "TYPE_PROXIMITY";
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                strType = "TYPE_ROTATION_VECTOR";
                break;
            case Sensor.TYPE_TEMPERATURE:
                strType = "TYPE_TEMPERATURE";
                break;
            default:
                strType = "TYPE_UNKNOW";
                break;
        }
        return strType;
    }


}
