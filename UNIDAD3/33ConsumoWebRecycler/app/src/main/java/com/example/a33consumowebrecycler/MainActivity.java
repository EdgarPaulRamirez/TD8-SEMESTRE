package com.example.a33consumowebrecycler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a33consumowebrecycler.Helper.Common;
import com.example.a33consumowebrecycler.Helper.RecyclerItemTouchHelper;
import com.example.a33consumowebrecycler.Helper.RecyclerItemTouchHelperListener;
import com.example.a33consumowebrecycler.Remote.IMenuRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    //private final String URL_API = "https://api.myjson.com/bins/1hiyyi";
    //private final String URL_API = "https://api.myjson.com/bins/13bdiq";
    private final String URL_API = "https://api.myjson.com/bins/8zjyq";
    private RecyclerView recyclerView;
    private List<Item> list;
    private CardListAdapter adapter;
    private CoordinatorLayout rootLayout;

    IMenuRequest mServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServicio = Common.getMenuRequest();

        //Configuracion del Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recycler Consumo Web");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        rootLayout = findViewById(R.id.rootLayout);

        list = new ArrayList<>();
        adapter = new CardListAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        /*ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);*/

        //Recuperar los datos del API
        agregarDatosAlCard();

    }

    private void agregarDatosAlCard() {
        mServicio.getMenuList(URL_API).enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                list.clear();
                list.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direccion, int posicion) {
        if (viewHolder instanceof CardListAdapter.MyViewHolder){
            String nombreAlumno = list.get(viewHolder.getAdapterPosition()).getName();

            final Item deleteItem = list.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.removeItem(deleteIndex);

            Snackbar snackbar = Snackbar.make(rootLayout, nombreAlumno + " ---> Retirado del carrito", Snackbar.LENGTH_LONG);
            snackbar.setAction("RECUPERAR", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(deleteItem, deleteIndex);
                }
            });
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.show();
        }
    }
}
