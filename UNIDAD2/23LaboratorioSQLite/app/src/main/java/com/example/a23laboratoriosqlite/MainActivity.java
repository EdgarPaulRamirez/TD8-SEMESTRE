package com.example.a23laboratoriosqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnnew;
    private ImageButton btndel;
    private ImageButton btnupdate;
    private ImageButton btnlist;

    private EditText idCliente;
    private EditText nombre;
    private EditText rfc;
    private EditText cel;
    private EditText descripcion;
    private EditText costo;

    ConexionSQLiteHelper con;

    ArrayList<Paciente> listaClientes;

    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayaotManager;
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        con = new ConexionSQLiteHelper(MainActivity.this);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        con.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        con.closeDB();
    }

    private void init() {
        btnnew = findViewById(R.id.imageButton);
        btndel = findViewById(R.id.imageButton2);
        btnupdate = findViewById(R.id.imageButton3);
        btnlist = findViewById(R.id.imageButton4);

        idCliente = findViewById(R.id.edt0);
        nombre = findViewById(R.id.edt1);
        rfc = findViewById(R.id.edt2);
        cel = findViewById(R.id.edt3);
        descripcion = findViewById(R.id.edt4);
        costo = findViewById(R.id.edt5);

        btnnew.setOnClickListener(btnListener);
        btndel.setOnClickListener(btnListener);
        btnupdate.setOnClickListener(btnListener);
        btnlist.setOnClickListener(btnListener);

        listaObjetos= findViewById(R.id.carview);
        listaObjetos.setLayoutManager(new LinearLayoutManager(this));
        listaClientes=new ArrayList<>();
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageButton:
                    Long registros;
                    registros = registrarClientes();

                    if (registros==-1){
                        Toast.makeText(MainActivity.this,"ERROR: No se pudo registrar ",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MainActivity.this,"Se registro correctamente el paciente con el id: "+ registros, Toast.LENGTH_SHORT).show();
                        idCliente.setText("");
                        nombre.setText("");
                        rfc.setText("");
                        cel.setText("");
                        descripcion.setText("");
                        costo.setText("");
                    }
                    break;
                case R.id.imageButton2:
                    consultar();
                    break;

                case R.id.imageButton3:
                    Intent editar = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(editar);
                    break;

                case R.id.imageButton4:
                    Toast.makeText(MainActivity.this,"Lista de Clientes cargada correctamente",Toast.LENGTH_SHORT).show();
                    listardatos();
                    break;
            }
        }
    };

    private void listardatos() {
        try {

            listaClientes = new ArrayList<>();
            StringBuffer datos = new StringBuffer();
            Cursor cursor = con.getAll();

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                datos.append(cursor.getInt(cursor.getColumnIndex(Utilidades.IDPACIENTE)));
                datos.append(" - ");
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.NOMBRE)));
                datos.append(" - ");
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.RFC)));
                datos.append(" - ");
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.CEL)));
                datos.append(" - ");
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.EMAIL)));
                datos.append(" - ");
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.FECHA)));
                datos.append("\n");
                listaClientes.add(new Paciente(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            }
            adaptador = new Adaptador(listaClientes, getApplicationContext());
            listaObjetos.setAdapter(adaptador);

        }catch (SQLException e) {

        }

    }

    //Insertar Datos del cliente en la base de datos
    public Long registrarClientes() {

        return con.insert(idCliente.getText().toString(), nombre.getText().toString(), rfc.getText().toString(), cel.getText().toString(), descripcion.getText().toString(), costo.getText().toString());

    }


    //Consultar un Cliente en especifico
    private void consultar() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        final EditText id = new EditText(this);
        id.setInputType(InputType.TYPE_CLASS_NUMBER);
        id.setHint("ID PACIENTE");

        alerta.setTitle("CONSULTAR....").setMessage("Escriba el ID del Paciente a consultar")
                .setView(id).setPositiveButton("Consultar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(id.length()==0){
                    Toast.makeText(MainActivity.this, "Porfavor de agregar el ID", Toast.LENGTH_LONG).show();
                }else{
                    dialog.dismiss();
                    consultarCliente(id.getText().toString());
                }
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void consultarCliente(String buscarId) {

        ConexionSQLiteHelper db = new ConexionSQLiteHelper(MainActivity.this);
        String[] datos;
        datos = db.buscarCliente(buscarId);

        idCliente.setText(datos[1]);
        nombre.setText(datos[2]);
        rfc.setText(datos[3]);
        cel.setText(datos[4]);
        descripcion.setText(datos[5]);
        costo.setText(datos[6]);

        Toast.makeText(MainActivity.this, "Se encontro correctamente el paciente con el id: "+ buscarId, Toast.LENGTH_LONG).show();

    }

}
