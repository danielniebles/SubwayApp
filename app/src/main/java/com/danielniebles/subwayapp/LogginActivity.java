package com.danielniebles.subwayapp;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        eUser = (EditText)findViewById(R.id.eUserL);
        ePass = (EditText)findViewById(R.id.ePassL);
        bEnter = (Button)findViewById(R.id.bEnter);
        tRegister = (TextView) findViewById(R.id.tRegister);

        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = eUser.getText().toString();
                pass = ePass.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Hay campos vacíos",
                            Toast.LENGTH_SHORT).show();
                }else if(!user.equals(userextra) || !pass.equals(passextra)){
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("Name",user);
                    intent.putExtra("Pass",pass);
                    intent.putExtra("Mail",mailextra);
                    intent.putExtra("Date",datextra);
                    intent.putExtra("Sex",sexextra);
                    startActivity(intent);
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

        }else{
            Log.d("mensaje","No se cargaron los datos");
        }
    }
}
