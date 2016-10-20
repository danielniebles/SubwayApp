package com.danielniebles.subwayapp;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.danielniebles.subwayapp.R.id.iUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiperfilFragment extends Fragment {

    SQLiteDatabase dbUsuarios;
    String usuario, mail, sexo, fecha;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    TextView tPerfil;
    ImageView iUser;

    public MiperfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_miperfil, container, false);

        SQLiteHelper db = new SQLiteHelper(getActivity(), "Database", null, 1);
        dbUsuarios = db.getWritableDatabase();

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        usuario = prefs.getString("User","");

        //Obtener datos de DB
        Cursor c = dbUsuarios.rawQuery("select * from Usuarios where usuario='"+usuario+"'",null );

        if (c.moveToFirst()){
            mail = c.getString(c.getColumnIndex("email"));
            sexo = c.getString(c.getColumnIndex("sexo"));
            fecha = c.getString(c.getColumnIndex("fecha"));
            c.close();
        }

        dbUsuarios.close();

        iUser = (ImageView)view.findViewById(R.id.iUser);
        tPerfil = (TextView)view.findViewById(R.id.tPerfil);


        if(sexo.equals("Femenino")){
            iUser.setBackgroundResource(R.drawable.usuario2);
        }else{
            iUser.setBackgroundResource(R.drawable.usuario);
        }

        tPerfil.setText(new StringBuilder().append("Usuario: ").append(usuario).append("\n").append("Email: ")
                .append(mail).append("\n").append("Sexo: ").append(sexo).append("\n").append("Fecha de nacimiento: ")
                .append(fecha));

        // Inflate the layout for this fragment
        return view;
    }

}
