package com.ejemplo.rcview2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 20/02/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private String[] nombres;
    private Context contexto;

    public RecyclerAdapter(String[] nombres,Context contexto) {
        this.nombres = nombres;
        this.contexto = contexto;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) { //determina cuales seran los contenidos segun la posicion de la lista en la que se encuentre
        holder.campo_nombre.setText(nombres[position]);
        holder.imagenBasura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto,"Se ELIMINÓ A:"+nombres[position],Toast.LENGTH_LONG).show();
            }
        });

        holder.imagenUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto,"Se ACTUALIZÓ A:"+nombres[position],Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return nombres.length;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView campo_nombre;
        TextView campo_NoControl;
        TextView campo_Carrera;
        ImageView imagenUsuario;
        ImageView imagenBasura;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            campo_nombre = itemView.findViewById(R.id.alumno_id);
            campo_NoControl = itemView.findViewById(R.id.IdNumeroControl);
            campo_Carrera = itemView.findViewById(R.id.IdCarrera);
            imagenUsuario = itemView.findViewById(R.id.idImagen);
            imagenBasura = itemView.findViewById(R.id.idBasura);


        }
    }

}
