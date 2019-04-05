package firebase.app.a35servicioswebfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import firebase.app.a35servicioswebfirebase.model.Cliente;

public class Main3Activity extends AppCompatActivity {

    EditText licencia, cel, mail;
    Button btnActualizar, btnRegresar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        licencia = findViewById(R.id.licencia);
        cel = findViewById(R.id.cel);
        mail = findViewById(R.id.mail);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnRegresar = findViewById(R.id.btnRegresar);

        inicializarFirebase();

        //Recuperacion de datos del Adaptador
        final String cadena = getIntent().getExtras().getString("cadena");

        String arreglo[] = cadena.split("&");

        licencia.setText(arreglo[0]);
        licencia.setEnabled(false);
        final String monto = arreglo[1];
        final String puntos = arreglo[2];
        cel.setText(arreglo[3]);
        mail.setText(arreglo[4]);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente c = new Cliente();

                c.setLicencia(licencia.getText().toString());

                c.setMonto(monto);
                c.setPuntos(puntos);
                c.setCel(cel.getText().toString().trim());
                c.setMail(mail.getText().toString().trim());
                databaseReference.child("Cliente").child(c.getLicencia()).setValue(c);

                Toast.makeText(getApplicationContext(), "Actualizado Correctamente", Toast.LENGTH_LONG).show();
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

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
