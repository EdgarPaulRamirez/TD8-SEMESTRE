package mx.edu.ittepic.pulsortarea2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnjuego,reiniciar;
    TextView numero;
    float random,numeroFinal,numAvance,cap;
    String numRam1,numRam2,numRam3;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnjuego = findViewById(R.id.btn1);
        numero = findViewById(R.id.txtnumero);
        reiniciar = findViewById(R.id.btn2);
        numAvance = 0;

        final DecimalFormat df = new DecimalFormat("#.0");
        random = (float) (Math.random()*3);
        numRam1 = df.format(random);
        numero.setText(""+numRam1);
        numeroFinal = Float.parseFloat(numRam1);

        timer = new CountDownTimer(10000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                numAvance = (float) (numAvance + 0.1);
                numRam2 = df.format(numAvance);
                btnjuego.setText(""+numRam2);

                if (numAvance >= 2.9){
                    numAvance = (float) 0.0;
                }

            }

            @Override
            public void onFinish() {
                timer.start();

            }
        };timer.start();

        btnjuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numRam3 = numRam2;
                cap = Float.parseFloat(numRam3);
                if (numeroFinal == cap){
                    Toast.makeText(MainActivity.this, "¡BIEN HECHO, LE ATINASTE!", Toast.LENGTH_SHORT).show();
                    timer.cancel();
                }else {
                    Toast.makeText(MainActivity.this, "¡FALLASTE! :(", Toast.LENGTH_SHORT).show();
                }


            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random = (float) (Math.random()*3);
                numRam1 = df.format(random);
                numero.setText(""+numRam1);
                numeroFinal = Float.parseFloat(numRam1);
                timer.start();

            }
        });
    }
}
