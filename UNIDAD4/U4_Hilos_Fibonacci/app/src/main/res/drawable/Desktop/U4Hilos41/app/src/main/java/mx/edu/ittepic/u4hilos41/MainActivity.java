package mx.edu.ittepic.u4hilos41;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

public class MainActivity extends AppCompatActivity {

    //Button boton1;
    Button boton2;
    TextView txtContador,txtContador2;
    EditText edtNumero;
    ProgressBar progressBar1;
    //ProgressBar progressBar2;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //boton1 =  findViewById(R.id.btn1);
        boton2 =  findViewById(R.id.btn2);
        txtContador =  findViewById(R.id.textView);
        //txtContador2 =  findViewById(R.id.textView2);
        edtNumero =  findViewById(R.id.edtNumero);
        progressBar1 =  findViewById(R.id.progressBar1);
        //progressBar2 =  findViewById(R.id.progressBar2);

       /**
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNumero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No se ha ingresado numero...", Toast.LENGTH_SHORT).show();
                }
                else {
                    hilos(Integer.parseInt(edtNumero.getText().toString()));
                }
            }
        });*/

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNumero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese un numero ...", Toast.LENGTH_SHORT).show();
                }
                else{
                    AsyncTarea asyncTarea = new AsyncTarea();
                    asyncTarea.execute(Integer.parseInt(edtNumero.getText().toString()));
                }
            }
        });
    }

    private void UnSegundo() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  /**  public void hilos(final int numero) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                i =0;
                progressBar2.setMax(numero);
                progressBar2.setProgress(0);
                while(i<=numero-1){
                    UnSegundo();
                    i++;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            progressBar2.setProgress(i);
                            txtContador2.setText(""+i);
                        }
                    });
                }
            }
        }).start();
    }
**/

    private class  AsyncTarea extends AsyncTask<Integer, Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            progressBar1.setMax(params[0]);
            for (int i=1; i<=params[0]; i++){
                UnSegundo();

                publishProgress(i);

                if (isCancelled()){
                    break;
                }
            }
            return true;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //Actualiza la barra de progreso
            progressBar1.setProgress(values[0].intValue());
            txtContador.setText("... "+((values[0].intValue())));
        }

    }
}
