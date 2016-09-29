package com.danielniebles.subwayapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity {

    EditText eUser, eMail, ePass, eRepeat;
    Button bSave, bCancel;
    DatePicker datePicker;
    Calendar calendar;
    int año, mes, dia;
    StringBuilder fecha;
    String usuario, email, pass, rpass, sexo;
    RadioGroup rdgGroup;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        eUser = (EditText)findViewById(R.id.eUser);
        eMail = (EditText)findViewById(R.id.eMail);
        ePass = (EditText)findViewById(R.id.ePass);
        eRepeat = (EditText)findViewById(R.id.eRepeat);
        bSave = (Button)findViewById(R.id.bSave);
        bCancel = (Button)findViewById(R.id.bCancel);
        calendar = Calendar.getInstance();
        rdgGroup = (RadioGroup)findViewById(R.id.rdgGroup1);



        //Obtener de fecha
        año = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = eUser.getText().toString();
                email = eMail.getText().toString();
                pass = ePass.getText().toString();
                rpass = eRepeat.getText().toString();

                if(TextUtils.isEmpty(usuario)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)||
                        TextUtils.isEmpty(rpass)){
                    Toast.makeText(getApplicationContext(), "Hay campos vacíos",
                            Toast.LENGTH_SHORT).show();
                }else if(pass.equals(rpass)!=true){
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",
                            Toast.LENGTH_SHORT).show();
                }else if(rdgGroup.getCheckedRadioButtonId()== -1){
                    Toast.makeText(getApplicationContext(), "Seleccione el Sexo",
                            Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(fecha)) {
                    Toast.makeText(getApplicationContext(), "Seleccione la Fecha",
                            Toast.LENGTH_SHORT).show();

                }else{
                    int a = rdgGroup.getCheckedRadioButtonId();
                    switch (a){
                        case R.id.rFem:
                            sexo = "Femenino";
                            break;
                        case R.id.rMas:
                            sexo = "Masculino";
                            break;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("Name",usuario);
                    intent.putExtra("Pass",pass);
                    intent.putExtra("Mail",email);
                    intent.putExtra("Date",fecha.toString());
                    intent.putExtra("Sex",sexo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view){
        showDialog(999);
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == 999){
            return new DatePickerDialog(this,myDateListener,año,mes,dia);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3){
            showDate(arg1,arg2+1,arg3);
        }
    };

    private void showDate(int año, int mes, int dia){
        fecha = new StringBuilder().append(dia).append("/").append(mes).append("/")
                .append(año);
    }
}
