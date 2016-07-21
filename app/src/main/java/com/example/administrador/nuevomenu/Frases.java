package com.example.administrador.nuevomenu;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Frases extends Activity {
    MediaPlayer mp = null;
    Button play_ohayo;
    Button play_konnichiwa;
    Button play_konbanwa;
    Button play_sayonara;
    Button play_genkidesuka;

    int[] sounds = new int[] {R.raw.ohayoubnsds, R.raw.konnichiwabnstrds, R.raw.konbanwabnsnchs, R.raw.sayonaradios, R.raw.genkidesukacmsts};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);

        play_ohayo = (Button) this.findViewById(R.id.ohayo_sound);
        play_konnichiwa = (Button) this.findViewById(R.id.konnichiwa_sound);
        play_konbanwa = (Button) this.findViewById(R.id.Konbanwa_sound);
        play_sayonara = (Button) this.findViewById(R.id.sayonara);
        play_genkidesuka = (Button) this.findViewById(R.id.genkidesuka);

        play_ohayo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AudioPlay.pauseAudio();
                play_ohayo.setEnabled(false);
                onStop();
                managerOfSound(0);
                //saludo1.start();
            }
        });

        play_konnichiwa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AudioPlay.pauseAudio();
                play_konnichiwa.setEnabled(false);
                onStop();
                managerOfSound(1);
                //saludo2.start();
            }
        });

        play_konbanwa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AudioPlay.pauseAudio();
                play_konbanwa.setEnabled(false);
                onStop();
                managerOfSound(2);
                //saludo3.start();
            }
        });

        play_sayonara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AudioPlay.pauseAudio();
                play_sayonara.setEnabled(false);
                onStop();
                managerOfSound(3);
                //saludo4.start();
            }
        });

        play_genkidesuka.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AudioPlay.pauseAudio();
                play_genkidesuka.setEnabled(false);
                onStop();
                managerOfSound(4);
                //saludo5.start();
            }
        });

    }

    protected void managerOfSound(int imageId) {
        if (mp != null) {
            mp.reset();
            mp.release();
        }
        mp = MediaPlayer.create(this, sounds[imageId]);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer player) {
                AudioPlay.pauseAudio();
                if (!play_ohayo.isEnabled()) {
                    play_ohayo.setEnabled(true);
                } else if (!play_konnichiwa.isEnabled()) {
                    play_konnichiwa.setEnabled(true);
                } else if (!play_konbanwa.isEnabled()) {
                    play_konbanwa.setEnabled(true);
                } else if (!play_sayonara.isEnabled()) {
                    play_sayonara.setEnabled(true);
                } else if (!play_genkidesuka.isEnabled()) {
                    play_genkidesuka.setEnabled(true);
                }
            }});
        mp.start();
    }

    protected void onStop()
    {
        if (mp != null)
            mp.stop();
        super.onStop();
    }

    public void back(View v)
    {
        this.finish();
        try
        {
            ActivityAnimator anim = new ActivityAnimator();
            anim.getClass().getMethod(this.getIntent().getExtras().getString("backAnimation") + "Animation", Activity.class).invoke(anim, this);
        }
        catch (Exception e) { Toast.makeText(this, "An error occured " + e.toString(), Toast.LENGTH_LONG).show(); }
    }

    @Override
    public void onBackPressed()
    {
        back(null);
    }
}