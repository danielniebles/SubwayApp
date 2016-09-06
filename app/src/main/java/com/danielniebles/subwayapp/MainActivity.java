package com.danielniebles.subwayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
        }
        return super.onOptionsItemSelected(item);
    }

}
