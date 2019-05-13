package mx.edu.ittepic.listadesensores_51;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    TextView textView = null;


    ArrayList<Datos> listaUsuarios;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorVista adapter;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle( Build.MODEL);

        listaObjetos= findViewById(R.id.lista);
        listaObjetos.setLayoutManager(new LinearLayoutManager(this));
        listaUsuarios=new ArrayList<>();

        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder data = new StringBuilder();
        StringBuilder datas = new StringBuilder();
        for(int i=1; i<lista.size(); i++){
            try{
                data.append("Fabricante:"+lista.get(i).getVendor() + "\n"+
                        "Nombre:"+lista.get(i).getName() + "\n"+
                        "Version:"+lista.get(i).getVersion() + "\n"+
                        "MaximunRange:"+lista.get(i).getMaximumRange() + "\n"+
                        "MinDelay:"+lista.get(i).getMinDelay() + "\n"+
                        "Power:"+lista.get(i).getPower() + "\n"
                );
                datas.append("");
            }catch (Exception e){
                data.append("Fabricante: Sin fabricante");

            }
            listaUsuarios.add(new Datos(
                    data.toString(),
                    datas.toString(),
                    lista.get(i).getVersion(),
                    lista.get(i).getMaximumRange(),
                    lista.get(i).getMinDelay()
                    ,lista.get(i).getPower()
            ));
            data.setLength(0);
        }

        adapter = new AdaptadorVista(listaUsuarios, getApplicationContext());

        listaObjetos.setAdapter(adapter);
    }
}