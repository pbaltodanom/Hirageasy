package com.example.administrador.nuevomenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class Pantalla_Inicial extends Activity implements View.OnClickListener{

    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicial);

        AudioPlay.isPlaying();

        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"Toms_Handwriting.ttf");
        TextView elijeIdioma = (TextView)findViewById(R.id.elijeIdioma);
        elijeIdioma.setTypeface(myTypeFace);

        Button btnIngles = (Button) findViewById(R.id.btnIngles);
        Button btnEspanol = (Button) findViewById(R.id.btnEspanol);
        Button btnPortuges = (Button) findViewById(R.id.btnPortugues);

        btnIngles.setOnClickListener(this);
        btnEspanol.setOnClickListener(this);
        btnPortuges.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btnIngles: {
                setLocale("en",1);
                break;
            }

            case R.id.btnEspanol: {
                setLocale("es",2);
                break;
            }

            case R.id.btnPortugues: {
                setLocale("pt",3);
                break;
            }

            //.... etc
        }
    }

    public void setLocale(String lang,int valor) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtra("valor", valor);
        startActivity(refresh);
    }


}
