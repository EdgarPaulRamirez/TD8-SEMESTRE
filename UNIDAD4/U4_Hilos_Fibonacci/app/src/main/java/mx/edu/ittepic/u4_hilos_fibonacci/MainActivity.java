package mx.edu.ittepic.u4_hilos_fibonacci;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNumero;
    TextView txtResultado;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumero = findViewById(R.id.edtNumero);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNumero.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "NO SE HA INGRESADO NINGUN NUMERO", Toast.LENGTH_SHORT).show();
                }
                else{
                    AsyncTarea asyncTarea = new AsyncTarea();
                    asyncTarea.execute(Integer.parseInt(edtNumero.getText().toString()));
                }
            }
        });
    }


    private class  AsyncTarea extends AsyncTask<Integer, Integer,Boolean> {
        String resultado="0,1";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Integer... params) {//SEGUNDO PLANO
            int n1 = 0;
            int n2 = 1;
            int aux;
            int limite = params[0];
            int i=3;
            while ((i) <= limite) {

                aux = n1;
                n1 = n2;
                n2 = aux + n1;
                i++;
                resultado += n2+",";
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {//ACTUALIZACIONES
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Boolean aVoid) {//MOSTRANDO RESULTADO
            //super.onPostExecute(aVoid);
            txtResultado.setText(resultado);
        }


    }
}
