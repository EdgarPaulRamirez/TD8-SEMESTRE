package mx.edu.ittepic.listadesensores_51;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView[] textViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){}
        textViews= new TextView[6];
        textViews[0]= (TextView) findViewById(R.id.dato1);
        textViews[1]= (TextView) findViewById(R.id.dato2);
        textViews[2]= (TextView) findViewById(R.id.dato3);
        textViews[3]= (TextView) findViewById(R.id.dato4);
        textViews[4]= (TextView) findViewById(R.id.dato5);
        textViews[5]= (TextView) findViewById(R.id.dato6);
        setTitle(getIntent().getExtras().get("nombre").toString());
        textViews[0].setText(getIntent().getExtras().get("nombre").toString());
        textViews[1].setText(getIntent().getExtras().get("fabricante").toString());
        textViews[2].setText(getIntent().getExtras().get("version").toString());
        textViews[3].setText(getIntent().getExtras().get("rango").toString());
        textViews[4].setText(getIntent().getExtras().get("delay").toString());
        textViews[5].setText(getIntent().getExtras().get("poder").toString());
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}