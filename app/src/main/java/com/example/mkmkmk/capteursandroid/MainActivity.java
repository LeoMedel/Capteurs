package com.example.mkmkmk.capteursandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;
    CardView card5;
    CardView card6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        card1 = (CardView) findViewById(R.id.cardSensor);
        card2 = (CardView) findViewById(R.id.cardDetection);
        card3 = (CardView) findViewById(R.id.cardAcelero);
        card4 = (CardView) findViewById(R.id.cardDirection);
        card5 = (CardView) findViewById(R.id.cardFlash);
        card6 = (CardView) findViewById(R.id.cardProximite);

    }

    //  CARD 1
    public void setSensor(View view)
    {
        Intent intent = new Intent(this, Capteurs.class);
        startActivity(intent);
    }

    //  CARD 2
    public void setDetection(View view)
    {
        Toast.makeText(this, "Detection des Capteurs Absence / Présence ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetectionCapteurs.class);
        startActivity(intent);
    }

    //  CARD 3
    public void setAccelerometre(View view)
    {
        Intent intent = new Intent(this, Accelerometre.class);
        startActivity(intent);
    }

    //  CARD 4
    public void setAppDirection(View view)
    {
        Intent intent = new Intent(this, Direction.class);
        startActivity(intent);
    }

    //  CARD 5
    public void setFlash(View view)
    {
        Intent intent = new Intent(this, Secouer.class);
        startActivity(intent);
    }

    //  CARD 6
    public void setProximite(View view)
    {
        Intent intent = new Intent(this, Proximite.class);
        startActivity(intent);
    }



    //Plus Capteurs

    //Il Afiche l'information de la lumiere de l'ecran du dispositif
    public void setLumiere(View view)
    {
        Intent intent = new Intent(this, Lumiere.class);
        startActivity(intent);
    }

    //Capteur d'orientation, Affiche infromation du sensor OrientationVector
    public void setOrientation(View view)
    {
        Intent intent = new Intent(this, OrientationVector.class);
        startActivity(intent);
    }

    public void setGyroscope(View view)
    {
        //Toast.makeText(this, "Direction Secouer et Gyroscope", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Gyroscope.class);
        startActivity(intent);
    }
}
