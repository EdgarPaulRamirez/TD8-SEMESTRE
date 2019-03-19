package com.example.a26proyectobdu2_30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    EditText licencia, cel, mail;
    Button btnActualizar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        licencia = findViewById(R.id.licencia);
        cel = findViewById(R.id.cel);
        mail = findViewById(R.id.mail);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnRegresar = findViewById(R.id.btnRegresar);

        //Recuperacion de datos del Adaptador
        final String cadena = getIntent().getExtras().getString("cadena");

        String arreglo[] = cadena.split("&");

        licencia.setText(arreglo[0]);
        licencia.setEnabled(false);
        cel.setText(arreglo[1]);
        mail.setText(arreglo[2]);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionSQLiteHelper db = new ConexionSQLiteHelper(Main3Activity.this);

                String txtLicencia = licencia.getText().toString();
                String txtCel = cel.getText().toString();
                String txtMail = mail.getText().toString();

                String mensaje = db.actualizar(txtLicencia, txtCel, txtMail);

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                licencia.setText("");
                cel.setText("");
                mail.setText("");
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
