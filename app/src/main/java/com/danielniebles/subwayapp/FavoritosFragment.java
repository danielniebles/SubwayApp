package com.danielniebles.subwayapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    String usuario;
    int idUsuario;
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

        for(int j = 1; j <= 10; j++){
            c4 = dbUsuarios.rawQuery("select * from MisFavoritos where idFavorito='"+ j +"'",null);
            if (c4.moveToFirst()) {
                aux = c4.getInt(c4.getColumnIndex("idUsuario"));
                if (aux == idUsuario) {
                    productos.add(c4.getInt(c4.getColumnIndex("idProducto")));
                }
                c4.close();
            }
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

        Adapter adaptador = new Adapter(getActivity(), promociones1);
        list2 = (ListView) view.findViewById(R.id.list2);
        list2.setAdapter(adaptador);

        return view;
    }

    class Adapter extends ArrayAdapter<Promociones>{
        public Adapter(Context context, ArrayList<Promociones> objects) {
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
