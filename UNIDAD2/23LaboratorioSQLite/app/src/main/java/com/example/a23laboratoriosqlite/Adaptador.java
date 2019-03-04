package com.example.a23laboratoriosqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderClientes> {

    ArrayList<Paciente> listaPaciente;

    Context context;

    public Adaptador(ArrayList<Paciente> listaPaciente, Context context){
        this.listaPaciente = listaPaciente;
        this.context = context;
    }

    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup viewGroup, int i) {
        int layout = R.layout.vista;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, null, false);
        return new ViewHolderClientes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderClientes viewHolderClientes, int i) {

        viewHolderClientes.id.setText("ID: "+listaPaciente.get(i).getIdPaciente());
        viewHolderClientes.nombre.setText("Nombre: "+listaPaciente.get(i).getNombre());
        viewHolderClientes.rfc.setText("RFC: "+listaPaciente.get(i).getRfc());
        viewHolderClientes.cel.setText("Celular: "+listaPaciente.get(i).getCel());
        viewHolderClientes.mail.setText("E- MAIL: "+listaPaciente.get(i).getMail());
        viewHolderClientes.fecha.setText("Fecha: "+listaPaciente.get(i).getFecha());
        
    }

    @Override
    public int getItemCount() {
        return listaPaciente.size();
    }

    public class ViewHolderClientes extends RecyclerView.ViewHolder{

        TextView id, nombre, rfc, cel, mail, fecha;

        public ViewHolderClientes(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nombre = itemView.findViewById(R.id.nombre);
            rfc = itemView.findViewById(R.id.rfc);
            cel = itemView.findViewById(R.id.cel);
            mail  = itemView.findViewById(R.id.email);
            fecha = itemView.findViewById(R.id.fecha);
        }
    }
}
