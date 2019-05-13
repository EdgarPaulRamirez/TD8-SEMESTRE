package mx.edu.ittepic.acelerometro_52;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private Sensor mySensor;
    private SensorManager senMan;
    TextView txt_X,txt_Y,txt_Z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        senMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor= senMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        txt_X=(TextView)findViewById(R.id.txtX);
        txt_Y=(TextView)findViewById(R.id.txtY);
        txt_Z=(TextView)findViewById(R.id.txtZ);
        senMan.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txt_X.setText(""+event.values[0]);
        txt_Y.setText(""+event.values[1]);
        txt_Z.setText(""+event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
