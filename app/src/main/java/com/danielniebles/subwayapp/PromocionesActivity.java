package com.danielniebles.subwayapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PromocionesActivity extends AppCompatActivity {

    int posicion;
    int id, idUsuario;
    ImageView imageView;
    FloatingActionButton floatingActionButton;
    SQLiteDatabase dbUsuarios;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String user;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);

        //Parametros de base de datos
        SQLiteHelper db = new SQLiteHelper(this, "Database", null, 1);
        dbUsuarios = db.getWritableDatabase();

        //Parametros de la interfaz
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        imageView = (ImageView)findViewById(R.id.imagenp);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        Bundle extras = getIntent().getExtras();
        posicion = extras.getInt("Posicion");

        //Preferencias compartidas
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();
        user = prefs.getString("User","");

        //Definir el usuario actual
        Cursor c = dbUsuarios.rawQuery("select * from Usuarios where usuario='"+user+"'",null );
        if (c.moveToFirst()){
            idUsuario = c.getInt(c.getColumnIndex("id"));
            c.close();
        }

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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                switch(posicion){
                    case 0:
                        c = dbUsuarios.rawQuery("select * from MisFavoritos where idProducto = '"+1+"' and idUsuario = '"+idUsuario+"'", null);
                        if (c.getCount() <= 0){
                            Toast.makeText(getApplicationContext(), "Añadido a favoritos",
                                    Toast.LENGTH_SHORT).show();
                            dbUsuarios.execSQL("INSERT INTO MisFavoritos VALUES(null, '"+idUsuario+"', '1')");
                        }else{

                            Toast.makeText(getApplicationContext(), "Ya fue agregado",
                                    Toast.LENGTH_SHORT).show();
                        }
                        c.close();
                        break;
                    case 1:
                        c = dbUsuarios.rawQuery("select * from MisFavoritos where idProducto = '"+2+"' and idUsuario = '"+idUsuario+"'", null);
                        if (c.getCount() <= 0){
                            Toast.makeText(getApplicationContext(), "Añadido a favoritos",
                                    Toast.LENGTH_SHORT).show();
                            dbUsuarios.execSQL("INSERT INTO MisFavoritos VALUES(null, '"+idUsuario+"', '2')");
                        }else{

                            Toast.makeText(getApplicationContext(), "Ya fue agregado",
                                    Toast.LENGTH_SHORT).show();
                        }
                        c.close();
                        break;
                    case 2:
                        c = dbUsuarios.rawQuery("select * from MisFavoritos where idProducto = '"+3+"' and idUsuario = '"+idUsuario+"'", null);
                        if (c.getCount() <= 0){
                            Toast.makeText(getApplicationContext(), "Añadido a favoritos",
                                    Toast.LENGTH_SHORT).show();
                            dbUsuarios.execSQL("INSERT INTO MisFavoritos VALUES(null, '"+idUsuario+"', '3')");
                        }else{

                            Toast.makeText(getApplicationContext(), "Ya fue agregado",
                                    Toast.LENGTH_SHORT).show();
                        }
                        c.close();
                        break;
                    case 3:
                        c = dbUsuarios.rawQuery("select * from MisFavoritos where idProducto = '"+4+"' and idUsuario = '"+idUsuario+"'", null);
                        if (c.getCount() <= 0){
                            Toast.makeText(getApplicationContext(), "Añadido a favoritos",
                                    Toast.LENGTH_SHORT).show();
                            dbUsuarios.execSQL("INSERT INTO MisFavoritos VALUES(null, '"+idUsuario+"', '4')");
                        }else{

                            Toast.makeText(getApplicationContext(), "Ya fue agregado",
                                    Toast.LENGTH_SHORT).show();
                        }
                        c.close();
                        break;
                    case 4:
                        c = dbUsuarios.rawQuery("select * from MisFavoritos where idProducto = '"+5+"' and idUsuario = '"+idUsuario+"' ", null);
                        if (c.getCount() <= 0){
                            Toast.makeText(getApplicationContext(), "Añadido a favoritos",
                                    Toast.LENGTH_SHORT).show();
                            dbUsuarios.execSQL("INSERT INTO MisFavoritos VALUES(null, '"+idUsuario+"', '5')");
                        }else{

                            Toast.makeText(getApplicationContext(), "Ya fue agregado",
                                    Toast.LENGTH_SHORT).show();
                        }
                        c.close();
                        break;
                }
            }
        });
    }
}
