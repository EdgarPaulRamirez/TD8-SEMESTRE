package mx.edu.ittepic.clima_tepic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AsyncResponse  {
    ConexionWeb conexionWeb;
    TextView clima1,clima2,climat,clima3;
    EditText ciudad;
    String ciudadchidad;
    Button actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clima1=findViewById(R.id.Clima1);
        clima2=findViewById(R.id.Clima2);
        climat=findViewById(R.id.climat);
        clima3=findViewById(R.id.Clima3);
        actualizar = findViewById(R.id.btnACT);
        ciudad = findViewById(R.id.ciudad);
        CargarClima();

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarClima();
               ciudadchidad = ciudad.getText().toString();
            }
        });
    }
    public void CargarClima() {
        try {
            conexionWeb = new ConexionWeb(MainActivity.this);
            URL direcciopn = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ciudadchidad+",mx&APPID=043746939e7268316c8bbe640799c06d&units=metric");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void procesarRespuesta(String r) {
        try {
            JSONObject object = new JSONObject(r);
            JSONArray clima =           object.getJSONArray("weather");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < clima.length(); i++) {
                JSONObject objeto = clima.getJSONObject(i);

                String main = objeto.getString("main");
                String des = objeto.getString("description");
                //String coord = objeto.getString("coord");
                sb.append(main+" : "+des+"         ");
            }
            JSONObject  clima3j= object.getJSONObject("wind");
            clima1.setText(sb+"visibilidad: "+ object.getString("id")+"\n"+"Velocidad : "+clima3j.getString("speed")+"\n"+" Grados: "+clima3j.getString("deg"));
            JSONObject  clima2j= object.getJSONObject("main");
            JSONObject  climaj= object.getJSONObject("coord");
            clima2.setText("\n"
                    +"Temperatura: "+clima2j.getString("temp")+ "\n"
                    +"Humedad: "+clima2j.getString("humidity")+ "\n"
                    +"temperatura minima: "+clima2j.getString("temp_min")+"\n"
                    +"temperatura maxima: "+clima2j.getString("temp_max"));

            clima3.setText("\n"
                    +"Longitud: "+climaj.getString("lon")+ "\n"
                    +"Latitud: "+climaj.getString("lat"));


        }catch (JSONException e){
            System.out.println(""+e);
        }


    }
}