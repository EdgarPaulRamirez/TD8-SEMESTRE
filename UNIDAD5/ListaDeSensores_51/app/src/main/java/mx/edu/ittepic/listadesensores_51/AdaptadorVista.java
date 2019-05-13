package mx.edu.ittepic.listadesensores_51;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorVista extends RecyclerView.Adapter<AdaptadorVista.ViewHolderUsuarios>  {
    ArrayList<Datos> listaUsuarioss;
    Context context;
    int position;


    public AdaptadorVista(ArrayList<Datos> listaUsuarioss, Context context) {
        this.listaUsuarioss = listaUsuarioss;
        this.context = context;
    }
    public void onBindViewHolder(ViewHolderUsuarios holder, final int position) {
        holder.nombre.setText(""+listaUsuarioss.get(position).getNombre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("nombre",listaUsuarioss.get(position).getNombre());
                intent.putExtra("fabricante",listaUsuarioss.get(position).getFabricante());
                intent.putExtra("version",""+listaUsuarioss.get(position).getVersion());
                intent.putExtra("rango",""+listaUsuarioss.get(position).getRango());
                intent.putExtra("delay",""+listaUsuarioss.get(position).getDelay());
                intent.putExtra("poder",""+listaUsuarioss.get(position).getPoder());
                context.startActivity(intent);
            }
        });
    }

    public ViewHolderUsuarios onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout=R.layout.lista;
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        return new ViewHolderUsuarios(view);
    }
    public int getItemCount() {
        return listaUsuarioss.size();
    }

    public class ViewHolderUsuarios extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;

        public ViewHolderUsuarios(View itemView) {
            super(itemView);
            nombre =(TextView) itemView.findViewById(R.id.clave);
        }

        @Override
        public void onClick(View view) {
            position=getAdapterPosition();
        }
    }

}
