package com.example.mkmkmk.capteursandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Secouer extends Activity implements SensorEventListener {


    TextView txtFlash;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private boolean lumiere = false;
    ImageView linterne;


    // Déclaration de l'attribut en tant qu'attribut de l'activité
    // Le sensor manager (gestionnaire de capteurs)
    SensorManager sensorManager;

    // L'accéléromètre
    Sensor accelerometer;

    //request camera
    //private static final int MY_CAMERA_REQUEST_CODE = 100;


    // Appelé à la création de l'activité. */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secouer);

        txtFlash = (TextView) findViewById(R.id.txtFlash);

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        linterne = (ImageView) findViewById(R.id.imgLumiere);
/*
        PERMISSION POUR ACCEDER A LA CAMERA
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        }
*/
        // Gérer les capteurs&#160;:
        // Instancier le gestionnaire des capteurs, le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Instancier l'accéléromètre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Faire d'autres trucs
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void Linterne()
    {
        CameraManager camera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String[] tmp = camera.getCameraIdList();
            String cameraID = tmp[0];

            if(lumiere) {
                camera.setTorchMode(cameraID, true);
                //Toast.makeText(this, "Flash activé :D ", Toast.LENGTH_SHORT).show();
                txtFlash.setText("Flash ON !!!");
                txtFlash.setTextColor(Color.BLACK);
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                linterne.setImageResource(R.drawable.lighton);
            }
            else
            {
                camera.setTorchMode(cameraID, false);
                //Toast.makeText(this, "Flash desactivé D: ", Toast.LENGTH_SHORT).show();
                txtFlash.setText("Flash OFF !!!");
                txtFlash.setTextColor(Color.WHITE);
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                linterne.setImageResource(R.drawable.lightoff);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        /* Ce qu'en dit Google&#160; dans le cas de l'accéléromètre :
         * «&#160; Ce n'est pas nécessaire d'avoir les évènements des capteurs à un rythme trop rapide.
         * En utilisant un rythme moins rapide (SENSOR_DELAY_UI), nous obtenons un filtre
         * automatique de bas-niveau qui "extrait" la gravité  de l'accélération.
         * Un autre bénéfice étant que l'on utilise moins d'énergie et de CPU.&#160;»
         */
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // unregister the sensor (désenregistrer le capteur)
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {

                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    if (lumiere)
                    {
                        lumiere = false;
                    }
                    else
                    {
                        lumiere = true;
                    }
                    Linterne();
                    //A C T I V E R    L A    C A M E R A
                    //On fait le Intent pour appeller a la Camera
                    //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivity(cameraIntent);
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Rien à faire la plupart du temps
    }
}
