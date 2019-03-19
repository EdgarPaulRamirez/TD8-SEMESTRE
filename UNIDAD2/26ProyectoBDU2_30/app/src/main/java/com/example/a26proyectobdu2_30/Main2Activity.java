package com.example.a26proyectobdu2_30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText licencia, monto, puntos, cel, mail;

    Button btnInsertar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        licencia = findViewById(R.id.edt0);
        monto = findViewById(R.id.edt1);
        puntos = findViewById(R.id.edt2);
        cel = findViewById(R.id.edt3);
        mail = findViewById(R.id.edt4);
        btnInsertar = findViewById(R.id.button);
        btnRegresar = findViewById(R.id.button2);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarDatos();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertarDatos() {

        ConexionSQLiteHelper db = new ConexionSQLiteHelper(getApplicationContext());

        String txtLicencia = licencia.getText().toString();
        String txtMonto = monto.getText().toString();
        String txtPuntos = puntos.getText().toString();
        String txtCel = cel.getText().toString();
        String txtMail = mail.getText().toString();

        String mensaje = db.insertar(txtLicencia, txtMonto, txtPuntos, txtCel, txtMail);
        Toast.makeText(Main2Activity.this, mensaje, Toast.LENGTH_LONG).show();
        licencia.setText("");
        monto.setText("");
        puntos.setText("");
        cel.setText("");
        mail.setText("");

    }
}
