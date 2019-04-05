package firebase.app.a35servicioswebfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import firebase.app.a35servicioswebfirebase.model.Cliente;

public class Main2Activity extends AppCompatActivity {

    EditText licencia, monto, puntos, cel, mail;

    Button btnInsertar, btnRegresar;

    //FirebaseDatabase firebaseDatabase;
    //DatabaseReference databaseReference;
    DatabaseReference bdd;
    boolean cierto = false;

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

        inicializarFirebase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txtLicencia = licencia.getText().toString();
                final String txtMonto = monto.getText().toString();
                final String txtPuntos = puntos.getText().toString();
                final String txtCel= cel.getText().toString();
                final String txtMail = mail.getText().toString();

                Query q = bdd.orderByChild("licencia").equalTo(txtLicencia);
                cierto = false;

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int cont = 0;
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            cont++;

                            Cliente c = dataSnapshot1.getValue(Cliente.class);

                            int intMonto = Integer.parseInt(c.getMonto());
                            int intPuntos = Integer.parseInt(c.getPuntos());

                            intMonto = intMonto + Integer.parseInt(txtMonto);
                            intPuntos = intPuntos + Integer.parseInt(txtPuntos);

                            final Cliente cliente = new Cliente();
                            cliente.setLicencia(txtLicencia);
                            cliente.setMonto(String.valueOf(intMonto));
                            cliente.setPuntos(String.valueOf(intPuntos));
                            cliente.setCel(txtCel);
                            cliente.setMail(txtMail);

                            bdd.child(cliente.getLicencia()).setValue(cliente);
                            limpiarCampos();
                            cierto = true;

                        }
                        if (cierto == true){
                            Toast.makeText(Main2Activity.this, "La licencia es igual", Toast.LENGTH_LONG).show();
                        }else{
                            final Cliente cliente2 = new Cliente();
                            cliente2.setLicencia(txtLicencia);
                            cliente2.setMonto(txtMonto);
                            cliente2.setPuntos(txtPuntos);
                            cliente2.setCel(txtCel);
                            cliente2.setMail(txtMail);

                            bdd.child(cliente2.getLicencia()).setValue(cliente2);
                            Toast.makeText(Main2Activity.this, "Se registro correctamete", Toast.LENGTH_LONG).show();
                            limpiarCampos();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                /*databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (final DataSnapshot snapshot : dataSnapshot.getChildren()){

                            databaseReference.child("Cliente").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Cliente c = snapshot.getValue(Cliente.class);

                                    if (c.getLicencia().equals(licencia.getText().toString())){
                                        int intMonto = Integer.parseInt(c.getMonto());
                                        int intPuntos = Integer.parseInt(c.getPuntos());

                                        intMonto = intMonto + Integer.parseInt(txtMonto);
                                        intPuntos = intPuntos + Integer.parseInt(txtPuntos);

                                        final Cliente cliente = new Cliente();
                                        cliente.setLicencia(txtLicencia);
                                        cliente.setMonto(String.valueOf(intMonto));
                                        cliente.setPuntos(String.valueOf(intPuntos));
                                        cliente.setCel(txtCel);
                                        cliente.setMail(txtMail);

                                        databaseReference.child("Cliente").child(cliente.getLicencia()).setValue(cliente);
                                        limpiarCampos();
                                        Toast.makeText(Main2Activity.this, "La licencia es igual", Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
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
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();*/
        bdd = FirebaseDatabase.getInstance().getReference("Cliente");
    }

    private void limpiarCampos() {
        licencia.setText("");
        monto.setText("");
        puntos.setText("");
        cel.setText("");
        mail.setText("");
    }
}
