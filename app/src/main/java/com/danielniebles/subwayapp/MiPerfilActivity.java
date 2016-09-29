package com.danielniebles.subwayapp;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MiPerfilActivity extends NavExActivity {

    TextView tPerfil;
    ImageView iUser;
    String usuario, mail, sexo, fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mi_perfil);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_mi_perfil, contentFrameLayout);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        tPerfil = (TextView)findViewById(R.id.tPerfil);
        iUser = (ImageView)findViewById(R.id.iUser);

        /*Bundle extras = getIntent().getExtras();
        usuario = extras.getString("Name");
        mail = extras.getString("Mail");
        fecha = extras.getString("Date");
        sexo = extras.getString("Sex");*/

        usuario = prefs.getString("User","");
        mail = prefs.getString("Email","");
        sexo = prefs.getString("Sex", "Femenino");
        fecha = prefs.getString("Date","");

        if(sexo.equals("Femenino")){
            iUser.setBackgroundResource(R.drawable.usuario2);
        }else{
            iUser.setBackgroundResource(R.drawable.usuario);
        }

        tPerfil.setText(new StringBuilder().append("Usuario: ").append(usuario).append("\n").append("Email: ")
        .append(mail).append("\n").append("Sexo: ").append(sexo).append("\n").append("Fecha de nacimiento: ")
        .append(fecha));
    }

}
