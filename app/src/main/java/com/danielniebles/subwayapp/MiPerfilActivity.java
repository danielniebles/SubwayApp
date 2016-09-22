package com.danielniebles.subwayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
                intent.putExtra("Name", usuario);
                intent.putExtra("Mail", mail);
                intent.putExtra("Sex", sexo);
                intent.putExtra("Date", fecha);
                startActivity(intent);
                break;
            case R.id.mLigeros:
                Intent intent2 = new Intent(this,PublicidadActivity.class);
                intent2.putExtra("Name", usuario);
                intent2.putExtra("Mail", mail);
                intent2.putExtra("Sex", sexo);
                intent2.putExtra("Date", fecha);
                startActivity(intent2);
                break;
            case R.id.mSanduches:
                Intent intent3 = new Intent(this,Publicidad2Activity.class);
                intent3.putExtra("Name", usuario);
                intent3.putExtra("Mail", mail);
                intent3.putExtra("Sex", sexo);
                intent3.putExtra("Date", fecha);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
