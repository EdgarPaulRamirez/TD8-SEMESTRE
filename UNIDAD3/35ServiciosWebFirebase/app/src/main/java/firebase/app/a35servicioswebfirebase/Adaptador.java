package firebase.app.a35servicioswebfirebase;

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

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import firebase.app.a35servicioswebfirebase.model.Cliente;

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

        viewHolderClientes.clienteSeleccionado = listaClientes.get(i);

        consultarDato(viewHolderClientes, viewHolderClientes.clienteSeleccionado);

        viewHolderClientes.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadena = viewHolderClientes.licencia.getText().toString() + "&" + viewHolderClientes.clienteSeleccionado.getMonto() + "&" + viewHolderClientes.clienteSeleccionado.getPuntos() + "&" + viewHolderClientes.cel.getText().toString() + "&" + viewHolderClientes.mail.getText().toString();
                Intent pantalla = new Intent(context, Main3Activity.class);
                pantalla.putExtra("cadena", cadena);
                context.startActivity(pantalla);
            }
        });

        viewHolderClientes.btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar(viewHolderClientes.licencia.getText().toString(), viewHolderClientes.clienteSeleccionado);
            }
        });

        viewHolderClientes.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaClientes.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, listaClientes.size());
                eliminarCliente(viewHolderClientes.licencia.getText().toString(), viewHolderClientes.databaseReference);
            }
        });
    }


    private void eliminarCliente(String licencia, DatabaseReference databaseReference) {
        Cliente c = new Cliente();
        c.setLicencia(licencia);
        databaseReference.child("Cliente").child(c.getLicencia()).removeValue();
        Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_LONG).show();
    }

    private void consultar(String licencia, Cliente clienteSeleccionado) {

        String monto = clienteSeleccionado.getMonto();
        String puntos = clienteSeleccionado.getPuntos();

        Toast.makeText(context, "La licencia es: "+licencia+"\nMonto: "+ monto+"\nPuntos: "+ puntos, Toast.LENGTH_LONG).show();
    }

    private void consultarDato(ViewHolderClientes viewHolderClientes, Cliente clienteSeleccionado){
        int intPuntos = Integer.parseInt(clienteSeleccionado.getPuntos());

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
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        Cliente clienteSeleccionado;

        public ViewHolderClientes(View itemView) {
            super(itemView);
            layo = itemView.findViewById(R.id.layoPricipal);
            licencia = itemView.findViewById(R.id.licencia);
            cel = itemView.findViewById(R.id.cel);
            mail = itemView.findViewById(R.id.mail);

            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnConsultar = itemView.findViewById(R.id.btnConsultar);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);

            FirebaseApp.initializeApp(context);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();
        }
    }
}
