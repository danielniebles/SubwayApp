package com.danielniebles.subwayapp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String user, mail, sex, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("Name");
        mail = extras.getString("Mail");
        sex = extras.getString("Sex");
        date = extras.getString("Date");
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
                Intent intent = new Intent(this,MiPerfilActivity.class);
                intent.putExtra("Name", user);
                intent.putExtra("Mail", mail);
                intent.putExtra("Sex", sex);
                intent.putExtra("Date", date);
                startActivity(intent);
                break;
            case R.id.mPrincipal:
                break;
            case R.id.mLigeros:
                Intent intent2 = new Intent(this,PublicidadActivity.class);
                intent2.putExtra("Name", user);
                intent2.putExtra("Mail", mail);
                intent2.putExtra("Sex", sex);
                intent2.putExtra("Date", date);
                startActivity(intent2);
                break;
            case R.id.mSanduches:
                Intent intent3 = new Intent(this,Publicidad2Activity.class);
                intent3.putExtra("Name", user);
                intent3.putExtra("Mail", mail);
                intent3.putExtra("Sex", sex);
                intent3.putExtra("Date", date);
                startActivity(intent3);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
