package mx.edu.ittepic.menualumno;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.ittepic.menualumno.adapter.Datos;
import mx.edu.ittepic.menualumno.adapter.DiseaseAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuAlumno extends AppCompatActivity  implements AsyncResponse {
    Boolean update=false;
    Button btnBuscar, btnAgregar;
    EditText edi;
    ConexionWeb conexionWeb;
    private RecyclerView mRecyclerView;
    private DiseaseAdapter mAdapter;
    private List<Datos> datos = new ArrayList<>();
    LinearLayoutManager layoutManager;
    String nombre2;
    String direccion2;
    //String url="192.168.10.85:80";   //por ip local
    String url="cachitogordo.000webhostapp.com";  // mediante servidor remoto


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alumno);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        edi = findViewById(R.id.Edi);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DiseaseAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getall(edi.getText().toString());
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInsertarDatos(null,1);
            }
        });
    }

    private void fnInsertarDatos(final Integer ID, final Integer Operacion) {
        try {
            String titulo = "Agregando alumno";
            final EditText nombre       = new EditText(this);
            final EditText domicilio    = new EditText(this);
            final LinearLayout Layout   = new LinearLayout(this);
            final TextView nombreT      = new TextView(this);
            final TextView domicilioT   = new TextView(this);

            nombreT.setText("Nombre:");
            nombreT.setTextColor(Color.BLACK);
            domicilioT.setText("Domicilio:");
            domicilioT.setTextColor(Color.BLACK);

            if (Operacion==2) {
                titulo = "Actualizar Datos";
                nombre.setText(nombre2);
                domicilio.setText(direccion2);
            }
            if(Operacion==3){
                titulo = "Eliminacion de alumno";
                nombreT.setText("¿esta seguro?");
                nombreT.setPadding(100,20,0,0);
            }

            Layout.setOrientation(LinearLayout.VERTICAL);
            Layout.addView(nombreT);
            if (Operacion==1 || Operacion==2){
                Layout.addView(nombre);
                Layout.addView(domicilioT);
                Layout.addView(domicilio);
            }
            AlertDialog.Builder mens = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setView(Layout)
                    .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog2, int which) {
                            try {
                                if (Operacion==2) {
                                    try {
                                        conexionWeb = new ConexionWeb(MenuAlumno.this, "POST");
                                        conexionWeb.agregarVariable("idalumno",ID+"");
                                        if(nombre.getText().toString().equals("")){
                                            conexionWeb.agregarVariable("nombre"," ");
                                        }else {
                                            conexionWeb.agregarVariable("nombre",nombre.getText().toString());
                                        }
                                        if(domicilio.getText().toString().equals("")){
                                            conexionWeb.agregarVariable("direccion"," ");
                                        }else {
                                            conexionWeb.agregarVariable("direccion",domicilio.getText().toString());
                                        }
                                        URL direccion = new URL("http://"+url+"/datos1/actualizar_alumno.php");
                                        conexionWeb.execute(direccion);
                                    } catch (MalformedURLException e) { }
                                }
                                if (Operacion==1){
                                    try {
                                        conexionWeb = new ConexionWeb(MenuAlumno.this, "POST");
                                        if(nombre.getText().toString().equals("")){
                                            conexionWeb.agregarVariable("nombre"," ");
                                        }else {
                                            conexionWeb.agregarVariable("nombre",nombre.getText().toString());
                                        }
                                        if(domicilio.getText().toString().equals("")){
                                            conexionWeb.agregarVariable("direccion"," ");
                                        }else {
                                            conexionWeb.agregarVariable("direccion",domicilio.getText().toString());
                                        }
                                        URL direccion = new URL("http://"+url+"/datos1/insertar_alumno.php");
                                        conexionWeb.execute(direccion);
                                    } catch (MalformedURLException e) { }
                                }
                                if (Operacion==3){
                                    try {
                                        conexionWeb = new ConexionWeb(MenuAlumno.this, "POST");
                                        conexionWeb.agregarVariable("idalumno",ID+"");
                                        URL direccion = new URL("http://"+url+"/datos1/borrar_alumno.php");
                                        conexionWeb.execute(direccion);
                                    } catch (MalformedURLException e) { }
                                }
                            } catch (Exception e) {
                                Toast.makeText(MenuAlumno.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            dialog2.dismiss();
                        }
                    })
                    .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog2, int which) {
                            dialog2.cancel();
                        }
                    });
            mens.show();
        }catch (Exception e){
        }
    }
    public void Opciones(final Integer ID){
        Button Modificar      = new Button(this);
        Button Eliminar       = new Button(this);
        LinearLayout Layout   = new LinearLayout(this);
        Layout.setOrientation(LinearLayout.VERTICAL);
        Modificar.setText("Modificar");
        Eliminar.setText("Eliminar");
        Layout.addView(Modificar);
        Layout.addView(Eliminar);

        AlertDialog.Builder mens = new AlertDialog.Builder(this)
                .setTitle("Opciones")
                .setView(Layout)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        mens.show();
        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    update=true;
                    conexionWeb = new ConexionWeb(MenuAlumno.this, "GET");
                    URL direccion = new URL("http://"+url+"/datos1/obtener_alumno_por_id.php?idalumno="+ID);
                    conexionWeb.execute(direccion);
                } catch (MalformedURLException e) { }
            }
        });
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInsertarDatos(ID,3);
            }
        });
    }
    public void getall(String parametro) {
        try {
            conexionWeb = new ConexionWeb(MenuAlumno.this, "GET");
            URL direccion;
            if(!parametro.equals("")){
                direccion = new URL("http://"+url+"/datos1/obtener_alumno_por_id.php?idalumno="+parametro);
            }else {
                direccion = new URL("http://"+url+"/datos1/obtener_alumnos.php");
            }
            conexionWeb.execute(direccion);
        } catch (MalformedURLException e) { }
    }

    public void procesarRespuesta(String r) {
        String str = r.replace("[", "");
        str = str.replace("&", "");
        str = str.replace("}]}", "");
        str = str.replace("\"", "");
        str = str.replace("{estado:1,alumnos:{", "");
        str = str.replace("{estado:1,alumno:{", "");
        str = str.replace("},{", "&");
        str = str.replace("}}", "");
        str = str.replace("idalumno:", "");
        str = str.replace("idAlumno:", "");
        str = str.replace("nombre:", "");
        str = str.replace("direccion:", "");
        str = str.replace(",,", ", ,");
        String array[] = str.split("&");
        if(update==false){
            if (!str.equals("{estado:2,mensaje:No se obtuvo el registro}") && !str.equals("{estado:1,mensaje:Creacion correcta}") && !str.equals("{estado:1,mensaje:Eliminacion exitosa}") && !str.equals("{estado:1,mensaje:Actualizacion correcta}")) {
                datos.clear();
                for (int j = 0; j < array.length; j++) {
                    String array2[]=array[j].split(",");
                    datos.add(new Datos(array2[1], array2[2],Integer.parseInt(array2[0])));
                }
            }else {
                datos.clear();
                str = str.replace("{estado:2,mensaje:","");
                str = str.replace("{estado:1,mensaje:","");
                str = str.replace("}","");
                Toast.makeText(MenuAlumno.this, str, Toast.LENGTH_SHORT).show();
                if(str.equals("No se obtuvo el registro")){
                    datos.clear();
                }else {
                    getall("");
                }
            }
            mAdapter.setmDataSet(datos);
        }else {
            String array2[]=array[0].split(",");
            nombre2=array2[1];
            direccion2=array2[2];
            update=false;
            fnInsertarDatos(Integer.parseInt(array2[0]),2);
        }
    }
}

