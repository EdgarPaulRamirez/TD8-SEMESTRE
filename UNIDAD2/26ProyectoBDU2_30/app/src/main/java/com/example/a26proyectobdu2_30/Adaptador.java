package com.example.a26proyectobdu2_30;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderClientes> {

    ArrayList<Cliente> listaClientes;

    Context context;

    public Adaptador(ArrayList<Cliente> listaClientes, Context context) {
        this.listaClientes = listaClientes;
        this.context = context;
    }

    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup viewGroup, int i) {
        int layout = R.layout.vista;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, null, false);
        return new ViewHolderClientes(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderClientes viewHolderClientes, final int i) {
        viewHolderClientes.licencia.setText(""+listaClientes.get(i).getLicencia());
        viewHolderClientes.cel.setText(""+listaClientes.get(i).getCel());
        viewHolderClientes.mail.setText(""+listaClientes.get(i).getMail());

        consultarDato(viewHolderClientes.licencia.getText().toString(), viewHolderClientes);

        viewHolderClientes.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadena = viewHolderClientes.licencia.getText().toString() + "&" + viewHolderClientes.cel.getText().toString() + "&" + viewHolderClientes.mail.getText().toString();
                Intent pantalla = new Intent(context, Main3Activity.class);
                pantalla.putExtra("cadena", cadena);
                context.startActivity(pantalla);
            }
        });

        viewHolderClientes.btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar(viewHolderClientes.licencia.getText().toString());
            }
        });

        viewHolderClientes.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaClientes.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, listaClientes.size());
                eliminarCliente(viewHolderClientes.licencia.getText().toString());
            }
        });
    }

    /*private void eliminar(final String licencia) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle("ELIMINAR....").setMessage("Seguro que quieres eliminar el registro con la licencia: "+licencia+" ?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    eliminarCliente(licencia);
                }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }*/

    private void eliminarCliente(String licencia) {
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(context);
        String mensaje = db.eliminar(licencia);
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }

    private void consultar(String txtLicencia) {
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(context);
        String[] datos;
        datos = db.buscarCliente(txtLicencia);

        Toast.makeText(context, "La licencia es: "+datos[1] + "\nMonto: "+datos[2] + "\nPuntos: "+datos[3], Toast.LENGTH_LONG).show();
    }

    private void consultarDato(String txtLicencia, ViewHolderClientes viewHolderClientes){
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(context);
        String[] datos;
        datos = db.buscarCliente(txtLicencia);
        int intPuntos = Integer.parseInt(datos[3]);

        if (intPuntos > 10){
            viewHolderClientes.layo.setBackgroundColor(Color.parseColor("#F16243"));
        }

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ViewHolderClientes extends RecyclerView.ViewHolder{

        TextView licencia, cel, mail;
        ImageButton btnEditar, btnConsultar, btnBorrar;
        CardView layo;

        public ViewHolderClientes(View itemView) {
            super(itemView);
            layo = itemView.findViewById(R.id.layoPricipal);
            licencia = itemView.findViewById(R.id.licencia);
            cel = itemView.findViewById(R.id.cel);
            mail = itemView.findViewById(R.id.mail);

            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnConsultar = itemView.findViewById(R.id.btnConsultar);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
        }
    }
}
