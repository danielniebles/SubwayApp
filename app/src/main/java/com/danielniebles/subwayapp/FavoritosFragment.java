package com.danielniebles.subwayapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    ArrayList<Promociones> promociones1 = new ArrayList<Promociones>();
    ArrayList<Integer> productos = new ArrayList<Integer>();
    SQLiteDatabase dbUsuarios;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    FloatingActionButton floatingActionButton;
    Adapter adaptador = null;
    String usuario;
    int idUsuario, clicked;
    Cursor c1, c2, c3, c4;
    int j = 1;
    String nombre, descripcion, precio;
    int idImagen, aux;
    ListView list2;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_favoritos, container, false);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab2);

        SQLiteHelper db = new SQLiteHelper(getActivity(), "Database", null, 1);
        dbUsuarios = db.getWritableDatabase();

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        usuario = prefs.getString("User","");

        c1 = dbUsuarios.rawQuery("select * from Usuarios where usuario='"+usuario+"'",null );
        if (c1.moveToFirst()){
            idUsuario = c1.getInt(c1.getColumnIndex("id"));
            c1.close();
        }

        c4 = dbUsuarios.rawQuery("select * from MisFavoritos",null);
        while (c4.moveToNext()) {
            aux = c4.getInt(c4.getColumnIndex("idUsuario"));
            if (aux == idUsuario) {
                productos.add(c4.getInt(c4.getColumnIndex("idProducto")));
            }
            //c4.close();
        }

        for(int k = 0; k < productos.size(); k++){
            c3 = dbUsuarios.rawQuery("select * from Productos where idProducto='"+productos.get(k)+"'",null);
            if (c3.moveToFirst()) {
                nombre = c3.getString(c3.getColumnIndex("nombre"));
                descripcion = c3.getString(c3.getColumnIndex("descripcion"));
                precio = c3.getString(c3.getColumnIndex("precio"));
                idImagen = c3.getInt(c3.getColumnIndex("imagen"));
                c3.close();
            }
            Promociones promociones2 = new Promociones(idImagen, nombre, descripcion, precio);
            promociones1.add(promociones2);
        }

        adaptador = new Adapter(getActivity(), promociones1);
        list2 = (ListView) view.findViewById(R.id.list2);


        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clicked = position;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "";
                if(promociones1.size()==0){
                    Toast.makeText(getActivity(), "No tiene favoritos",
                            Toast.LENGTH_SHORT).show();
                }else {
                    String nombreaux = (promociones1.get(clicked)).getNombre();
                    promociones1.remove(clicked);
                    c2 = dbUsuarios.rawQuery("SELECT * FROM Productos WHERE nombre = '" + nombreaux + "'", null);
                    if (c2.moveToFirst()) {
                        pos = c2.getString(c2.getColumnIndex("idProducto"));
                    }
                    dbUsuarios.execSQL("DELETE FROM MisFavoritos WHERE idProducto = '" + pos + "' AND idUsuario = '" + idUsuario + "'");
                    list2.invalidateViews();
                }
            }
        });

        list2.setAdapter(adaptador);
        //dbUsuarios.close();

        return view;
    }

    class Adapter extends ArrayAdapter<Promociones>{
        public Adapter(Context context, ArrayList objects) {
            super(context, R.layout.layout_item, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            View item = inflater.inflate(R.layout.layout_item, null);

            TextView nombre = (TextView)item.findViewById(R.id.tPromocion);
            nombre.setText((promociones1.get(position)).getNombre());

            TextView descripcion = (TextView)item.findViewById(R.id.tDescripcion);
            descripcion.setText((promociones1.get(position)).getDescripcion());

            TextView precio = (TextView)item.findViewById(R.id.tPrecio);
            precio.setText((promociones1.get(position)).getPrecio());

            ImageView imagen = (ImageView) item.findViewById(R.id.imagen);
            imagen.setImageResource((promociones1.get(position)).getIdImagen());

            return item;
        }
    }
}
