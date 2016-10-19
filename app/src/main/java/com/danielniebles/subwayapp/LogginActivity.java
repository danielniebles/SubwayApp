package com.danielniebles.subwayapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity {

    EditText eUser, ePass;
    Button bEnter;
    TextView tRegister;
    String user, pass;
    String userextra, passextra, sexextra, datextra, mailextra;
    String user2, pass2;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    SQLiteDatabase dbUsuarios;
    ContentValues dataBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        SQLiteHelper db = new SQLiteHelper(this, "Database", null, 1);

        dbUsuarios = db.getWritableDatabase();

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        eUser = (EditText)findViewById(R.id.eUserL);
        ePass = (EditText)findViewById(R.id.ePassL);
        bEnter = (Button)findViewById(R.id.bEnter);
        tRegister = (TextView) findViewById(R.id.tRegister);


        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = eUser.getText().toString();
                pass = ePass.getText().toString();

                //Obtener datos de DB
                Cursor c = dbUsuarios.rawQuery("select * from Usuarios where usuario='"+user+"'",null );

                if (c.moveToFirst()){
                    user2 = c.getString(c.getColumnIndex("usuario"));
                    pass2 = c.getString(c.getColumnIndex("contraseña"));
                    c.close();
                }

                dbUsuarios.close();

                //Validación de datos
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Hay campos vacíos",
                            Toast.LENGTH_SHORT).show();
                }else if(!user.equals(user2) || !pass.equals(pass2)){
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                    /*dataBD = new ContentValues();
                    dataBD.put("nombre", user);
                    dataBD.put("contraseña", pass);

                    dbUsuarios.insert("Productos", null, dataBD);*/
                    editor.putString("User", user2);
                    editor.putInt("Logged", 1);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
            }
        });
        tRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eUser.setText("");
                ePass.setText("");
                Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivityForResult(i,1234);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == RESULT_OK){

            userextra = data.getStringExtra("Name");
            passextra = data.getStringExtra("Pass");
            sexextra = data.getStringExtra("Sex");
            datextra = data.getStringExtra("Date");
            mailextra = data.getStringExtra("Mail");

            editor.putString("User", userextra);
            editor.putString("Password", passextra);
            editor.putString("Sex", sexextra);
            editor.putString("Date", datextra);
            editor.putString("Email", mailextra);
            editor.commit();

        }else{
            Log.d("mensaje","No se cargaron los datos");
        }
    }
}
