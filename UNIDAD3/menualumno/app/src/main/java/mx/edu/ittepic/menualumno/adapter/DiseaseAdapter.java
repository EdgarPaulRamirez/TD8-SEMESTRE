package mx.edu.ittepic.menualumno.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mx.edu.ittepic.menualumno.MenuAlumno;
import mx.edu.ittepic.menualumno.R;

import java.util.ArrayList;
import java.util.List;


public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.ViewHolder> {
    private List<Datos> mDataSet;
    private MenuAlumno menuCliente;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, Colonia, Domicilio;
        public CardView cardView;
        public ImageView img;

        public ViewHolder(View iteView) {
            super(iteView);
            cardView  = itemView.findViewById(R.id.cv);
            name  = itemView.findViewById(R.id.textView);
            Domicilio = itemView.findViewById(R.id.Domicilio);
        }
    }

    public DiseaseAdapter(MenuAlumno menuCliente) {
        mDataSet         = new ArrayList<>();
        this.menuCliente = menuCliente;
    }
    public void setmDataSet(List<Datos> dataSet){
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public DiseaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView cv    = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_view, parent, false);
        ViewHolder vh  = new ViewHolder(cv);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(mDataSet.get(position).name);
        holder.Domicilio.setText(mDataSet.get(position).Domicilio);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuCliente.Opciones(mDataSet.get(position).ID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}