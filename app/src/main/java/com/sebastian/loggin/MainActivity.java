package com.sebastian.loggin;

import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String nombre="",loggin="", correo="", telefono="", sexo="", hobbies = "",ciudad="",password="",cpassword,arroba="@";
    private  String[] Errores = {"Ingrese un Nombre", "Ingrese un Loggin", "Ingrese un Password", "Ingrese un Telefono", "Ingrese un Email","Confirme el Password","Correo Invalido","Número Incorrecto","Password Incorrecto"};
    private EditText eNombre,eLoggin,ePassword,eCPassword,eCorreo, eTelefono;
    private Button bRegistrar;
    private TextView tInformacion;
    private RadioButton rFemenino, rMasculino;
    private CheckBox cCine, cComer, cBailar, cLeer;
    private Spinner sCiudad;
    private DatePicker dFecha;
    private int flag = 0, encontrado = 0, email = 0, dia = 0, mes= 0, year = 0;
    private String fecha = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = (EditText) findViewById(R.id.eNombre);
        eLoggin = (EditText) findViewById(R.id.eLoggin);
        eCorreo = (EditText) findViewById(R.id.eCorreo);
        eTelefono = (EditText) findViewById(R.id.eTelefono);
        ePassword = (EditText) findViewById(R.id.ePassword);
        eCPassword = (EditText) findViewById(R.id.eCPassword);
        bRegistrar = (Button) findViewById(R.id.bRegistrar);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        cCine = (CheckBox) findViewById(R.id.cCine);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cBailar = (CheckBox) findViewById(R.id.cBailar);
        cLeer = (CheckBox) findViewById(R.id.cLeer);
        dFecha = (DatePicker) findViewById(R.id.dFecha);
        sCiudad = (Spinner) findViewById(R.id.sCiudad);
        tInformacion = (TextView) findViewById(R.id.tInformacion);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCiudad.setAdapter(adapter);
        sCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = eNombre.getText().toString();
                loggin = eLoggin.getText().toString();
                correo = eCorreo.getText().toString();
                password = ePassword.getText().toString();
                cpassword = eCPassword.getText().toString();
                telefono = eTelefono.getText().toString();
                dia = dFecha.getDayOfMonth();
                mes = dFecha.getMonth() + 1;
                year = dFecha.getYear();

                if(rMasculino.isChecked()){
                    sexo = "Masculino";
                }else{
                    sexo = "femenino";
                }
                if(cCine.isChecked()) {
                    hobbies = hobbies + " Ir a Cine ";
                }if(cBailar.isChecked()){
                    hobbies = hobbies + " Bailar ";
                }if(cComer.isChecked()){
                    hobbies = hobbies + " Comer ";
                }if(cLeer.isChecked()){
                    hobbies = hobbies + "Leer";
                }

                if(nombre.isEmpty()){
                    eNombre.setError(Errores[0]);
                }else{flag = flag +1;}
                if(loggin.isEmpty()){
                    eLoggin.setError(Errores[1]);
                }else{flag = flag +1;}
                if(password.isEmpty()){
                    ePassword.setError(Errores[2]);
                }else{flag = flag +1;}
                if(telefono.isEmpty()){
                    eTelefono.setError(Errores[3]);
                }else{if(telefono.length() >= 7)flag = flag +1;
                    else{eTelefono.setError(Errores[7]);}}
                if(correo.isEmpty()){
                    eCorreo.setError(Errores[4]);
                    email = 0;
                }else{encontrado = arroba.indexOf(correo); email =1;}
                if(ciudad.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ciudad Incorrecta", Toast.LENGTH_SHORT).show();
                }else{flag = flag +1;}
                if(cpassword.isEmpty()){
                    eCPassword.setError(Errores[5]);
                }else{flag = flag +1;}
                if(hobbies.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Selecciones Minimo un Hobbie", Toast.LENGTH_SHORT).show();
                }else{flag = flag +1;}
                if(encontrado == -1 && email == 1){
                    flag = flag +1;
                    email = 2;
                }else{if(email == 1)eCorreo.setError(Errores[6]);}
                if(password.equals(cpassword)){
                    flag = flag +1;
                }else{eCPassword.setError(Errores[8]);}
                if(year == 2017 || year < 1916){
                    Toast.makeText(MainActivity.this, "Fecha de Nacimiento Incorrecta", Toast.LENGTH_SHORT).show();
                }else{flag = flag +1;}
                if(flag >= 10){
                    fecha = Integer.toString(dia) + "/" +Integer.toString(mes) + "/" +Integer.toString(year);
                    tInformacion.setText("Nombre: "+nombre+ "\n"+"Loggin: "+loggin +"\n"+"Password: "
                            + password +"\n"+"Correo: "+ correo +"\n"+"Telefono: "+ telefono +"\n"+"Sexo: "+ sexo
                            +"\n"+"Fecha de Nacimiento: "+ fecha +"\n"+"Ciudad: "+ ciudad +"\n" +"Hobbies: "+ hobbies +"\n");
                    flag = 0;
                    hobbies = "";
                }else{flag = 0;hobbies="";tInformacion.setText("");}


            }
        });



    }
}
