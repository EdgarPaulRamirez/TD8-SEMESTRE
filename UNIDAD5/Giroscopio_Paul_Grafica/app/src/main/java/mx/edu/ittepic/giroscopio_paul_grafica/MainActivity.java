package mx.edu.ittepic.giroscopio_paul_grafica;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView salida;
    Sensor sensor;
    SensorManager sensorManager;
    String cadena ="";
    BarChart graficaBarras;
    float x,y,z,l = 3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salida = findViewById(R.id.txtsalida);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        graficaBarras = findViewById(R.id.graficaBarras);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                salida.setText("X "+event.values[0]+"\n"+"Y "+event.values[1]+"\n"+"Z "+event.values[2]);
                if (event.values[2]>0.5f){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }else if (event.values[2]<-0.5f){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }

                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                List<BarEntry> entradas = new ArrayList<>();

                entradas.add(new BarEntry(0f,x));
                entradas.add(new BarEntry(1f,y));
                entradas.add(new BarEntry(2f,z));

                BarDataSet datos = new BarDataSet(entradas, "Grafica de Barras Giroscopio");
                BarData data = new BarData(datos);// mandamos los datos de la grafica

                datos.setColors(ColorTemplate.COLORFUL_COLORS);//ponemos color a la grafica

                data.setBarWidth(0.7f);//separacion a cada una de las barras

                graficaBarras.setData(data);

                graficaBarras.setFitBars(true);//ponemos las barras centradas
                graficaBarras.invalidate();//hacer refresh
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }
}