package mx.edu.ittepic.juegobotella_u4_proyecto47;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    Button start, siguiente;
    ImageView imagen;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        start = findViewById(R.id.Run);
        siguiente = findViewById(R.id.siguiente);
        imagen = findViewById(R.id.imagen);

        if (imagen == null) throw new  AssertionError();
        imagen.setBackgroundResource(R.drawable.frames_animations);
        imagen.setVisibility(View.INVISIBLE);

        anim = (AnimationDrawable) imagen.getBackground();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagen.setVisibility(View.VISIBLE);
                if (anim.isRunning())
                    anim.stop();
                anim.start();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otraventana = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(otraventana);
            }
        });
    }
}
