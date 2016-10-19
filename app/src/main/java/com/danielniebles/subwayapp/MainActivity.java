package com.danielniebles.subwayapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends NavExActivity {


    String user, mail, sex, date;
    private Promociones[] promociones1 = new Promociones[5];

    ListView list;
    FrameLayout contentFrameLayout;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    int idImagen;
    String nombre, descripcion, precio;
    SQLiteDatabase dbUsuarios;
    int i = 0;
    int j = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        SQLiteHelper db = new SQLiteHelper(this, "Database", null, 1);
        dbUsuarios = db.getWritableDatabase();

        for (i = 0; i<5; i++) {
            Cursor c = dbUsuarios.rawQuery("select * from Productos where idProducto='" + j + "'", null);
            if (c.moveToFirst()) {
                nombre = c.getString(c.getColumnIndex("nombre"));
                descripcion = c.getString(c.getColumnIndex("descripcion"));
                precio = c.getString(c.getColumnIndex("precio"));
                idImagen = c.getInt(c.getColumnIndex("imagen"));
                c.close();
            }
            promociones1[i] = new Promociones(idImagen, nombre, descripcion, precio);
            j++;
        }
        j = 0;
        dbUsuarios.close();

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        Adapter adaptador = new Adapter(this, promociones1);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PromocionesActivity.class);
                intent.putExtra("Posicion", position);
                startActivity(intent);
            }
        });

    }

    class Adapter extends ArrayAdapter<Promociones>{
        public Adapter(Context context, Promociones[] objects) {
            super(context, R.layout.layout_item, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.layout_item, null);

            TextView nombre = (TextView)item.findViewById(R.id.tPromocion);
            nombre.setText(promociones1[position].getNombre());

            TextView descripcion = (TextView)item.findViewById(R.id.tDescripcion);
            descripcion.setText(promociones1[position].getDescripcion());

            TextView precio = (TextView)item.findViewById(R.id.tPrecio);
            precio.setText(promociones1[position].getPrecio());

            ImageView imagen = (ImageView) item.findViewById(R.id.imagen);
            imagen.setImageResource(promociones1[position].getIdImagen());

            return item;
        }
    }
}
