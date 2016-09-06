package com.danielniebles.subwayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MiPerfilActivity extends AppCompatActivity {

    TextView tPerfil;
    ImageView iUser;
    String usuario, mail, sexo, fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        tPerfil = (TextView)findViewById(R.id.tPerfil);
        iUser = (ImageView)findViewById(R.id.iUser);

        Bundle extras = getIntent().getExtras();

        usuario = extras.getString("Name");
        mail = extras.getString("Mail");
        fecha = extras.getString("Date");
        sexo = extras.getString("Sex");

        if(sexo.equals("Femenino")){
            iUser.setBackgroundResource(R.drawable.usuario2);
        }else{
            iUser.setBackgroundResource(R.drawable.usuario);
        }

        tPerfil.setText(new StringBuilder().append("Usuario: ").append(usuario).append("\n").append("Email: ")
        .append(mail).append("\n").append("Sexo: ").append(sexo).append("\n").append("Fecha de nacimiento: ")
        .append(fecha));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mMiPerfil:
                break;

            case R.id.mPrincipal:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
