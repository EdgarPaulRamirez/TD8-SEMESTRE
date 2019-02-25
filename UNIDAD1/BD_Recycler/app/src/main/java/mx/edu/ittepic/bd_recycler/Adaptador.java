package mx.edu.ittepic.bd_recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.edu.ittepic.bd_recycler.entidades.Usuario;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderUsuarios> {
    ArrayList<Usuario> listaUsuarioss;

    Context context;

    public Adaptador(ArrayList<Usuario> listaUsuarioss, Context context) {
        this.listaUsuarioss = listaUsuarioss;
        this.context = context;
    }
    public void onBindViewHolder(ViewHolderUsuarios holder, int position) {
        holder.id.setText(""+listaUsuarioss.get(position).getClave());
        holder.nombre.setText(listaUsuarioss.get(position).getNombre());
        holder.sueldo.setText(""+listaUsuarioss.get(position).getSueldo());

    }
    public ViewHolderUsuarios onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout=R.layout.vista;
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        return new ViewHolderUsuarios(view);

    }
    public int getItemCount() {
        return listaUsuarioss.size();
    }

    /*Se crean elementos configurados*/
    public class ViewHolderUsuarios extends RecyclerView.ViewHolder{
        TextView id,nombre,sueldo;

        public ViewHolderUsuarios(View itemView) {
            super(itemView);
            id= (TextView) itemView.findViewById(R.id.clave);
            nombre =(TextView) itemView.findViewById(R.id.nombre);
            sueldo =(TextView) itemView.findViewById(R.id.sueldo);
        }
    }

}
