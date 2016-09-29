package com.danielniebles.subwayapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Promociones[] promociones1 =
            new Promociones[]{
                    new Promociones(R.drawable.publicidad2,"Los Ligeros","Sánduches con 6 gramos de grasa, o menos.", "$7000"),
                    new Promociones(R.drawable.publicidad3,"Diarios","Todos los días un sub diferente!","Desde $7000"),
                    new Promociones(R.drawable.promocion3,"Nueva adición", "Prueba YA la nueva adición de guacamole!", "$2000"),
                    new Promociones(R.drawable.promocion2, "Nuevo Baratísimo", "Sub de pollo apanado", "$4700"),
                    new Promociones(R.drawable.promocion5, "Nuevos combos!", "Sorteos semanales", "Desde $4000")
            };


    ListView list;
    FrameLayout contentFrameLayout;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        /*Bundle extras = getIntent().getExtras();
        user = extras.getString("Name");
        mail = extras.getString("Mail");
        sex = extras.getString("Sex");
        date = extras.getString("Date");*/

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
