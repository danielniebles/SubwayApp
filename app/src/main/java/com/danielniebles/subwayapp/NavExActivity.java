package com.danielniebles.subwayapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class NavExActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    public static int mCurrentSelectedPosition = 0;
    String user, mail, sex, date;
    TextView tNombren, tMailn;
    ImageView imagend;
    SQLiteDatabase dbUsuarios;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_ex);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();
        user = prefs.getString("User","");

        SQLiteHelper db = new SQLiteHelper(this, "Database", null, 1);
        dbUsuarios = db.getWritableDatabase();

        //Obtener datos de DB
        Cursor c = dbUsuarios.rawQuery("select * from Usuarios where usuario='"+user+"'",null );

        if (c.moveToFirst()){
            mail = c.getString(c.getColumnIndex("email"));
            sex = c.getString(c.getColumnIndex("sexo"));
            c.close();
        }

        dbUsuarios.close();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout)findViewById(R.id.contenedorPrincipal);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abierto, R.string.cerrado);
        drawerLayout.setDrawerListener(drawerToggle);

        View header = navigationView.getHeaderView(0);
        tNombren = (TextView)header.findViewById(R.id.tNombren);
        tMailn = (TextView)header.findViewById(R.id.tMailn);
        imagend = (ImageView)header.findViewById(R.id.imagend);

        tNombren.setText(user);
        tMailn.setText(mail);
        if(sex.equals("Femenino")){
            imagend.setImageResource(R.drawable.usuario2);
        }else{
            imagend.setImageResource(R.drawable.usuario);
        }

        navigationView.getMenu().getItem(prefs.getInt("Actual",0)).setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_promociones:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        editor.putInt("Actual", 0);
                        editor.commit();
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_ligeros:
                        Intent intent2 = new Intent(getApplicationContext(), PublicidadActivity.class);
                        editor.putInt("Actual", 1);
                        editor.commit();
                        startActivity(intent2);
                        finish();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_perfil:
                        Intent intent3 = new Intent(getApplicationContext(), MiPerfilActivity.class);
                        editor.putInt("Actual", 3);
                        editor.commit();
                        startActivity(intent3);
                        finish();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_sanduches:
                        Intent intent4 = new Intent(getApplicationContext(), Publicidad2Activity.class);
                        editor.putInt("Actual", 2);
                        editor.commit();
                        startActivity(intent4);
                        finish();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_cerrar:
                        Intent intent5 = new Intent(getApplicationContext(), LogginActivity.class);
                        editor.putInt("Actual", 0);
                        editor.putInt("Logged", 0);
                        editor.commit();
                        startActivity(intent5);
                        finish();
                        drawerLayout.closeDrawers();
                }
                return false;
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
