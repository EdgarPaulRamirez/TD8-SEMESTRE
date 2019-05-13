package mx.edu.ittepic.u4_frameanimation_paul;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button start, stop;
    ImageView imagen;
    AnimationDrawable anim;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.Run);
        stop = findViewById(R.id.Stop);

        imagen = findViewById(R.id.imagen);

        if (imagen == null) throw new  AssertionError();
        imagen.setBackgroundResource(R.drawable.frames_animation);
        imagen.setVisibility(View.INVISIBLE);

        anim = (AnimationDrawable) imagen.getBackground();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagen.setVisibility(View.VISIBLE);
                if (anim.isRunning())
                    anim.stop();
                    musicaInicio();
                anim.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.stop();
                musicaPausa();
            }
        });

    }

    public void musicaInicio(){
        mp = MediaPlayer.create(this, R.raw.opening);
        mp.start();
    }

    public void musicaPausa(){
        mp = MediaPlayer.create(this, R.raw.opening);
        mp.stop();
    }
}
