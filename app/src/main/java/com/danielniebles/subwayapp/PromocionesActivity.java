package com.danielniebles.subwayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class PromocionesActivity extends AppCompatActivity {

    int posicion;
    int id;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        imageView = (ImageView)findViewById(R.id.imagenp);

        Bundle extras = getIntent().getExtras();
        posicion = extras.getInt("Posicion");

        switch (posicion){
            case 0:
                id = R.drawable.publicidad2;
                getSupportActionBar().setTitle("Los ligeros");
                break;
            case 1:
                id = R.drawable.publicidad3;
                getSupportActionBar().setTitle("Diarios");
                break;
            case 2:
                id = R.drawable.promocion3;
                getSupportActionBar().setTitle("Nueva adición");
                break;
            case 3:
                id = R.drawable.promocion2;
                getSupportActionBar().setTitle("Baratísimos");
                break;
            case 4:
                id = R.drawable.promocion5;
                getSupportActionBar().setTitle("Combos");
                break;
        }
        imageView.setImageResource(id);
    }
}
