package com.example.administrador.nuevomenu;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import java.util.concurrent.TimeUnit;

public class AdivinaActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables de textos
    TextView mostrarPalabra;
    TextView txtCronometro;
    TextView txtAciertos;
    TextView txtNumeroIntentos;

    ImageButton mImageView1;
    ImageButton mImageView2;
    ImageButton mImageView3;
    ImageButton mImageView4;

    //Imagenes
    ImageView imgStar;

    //Variables del cronometro
    private static final String FORMAT = "%02d:%02d:%02d";
    int seconds , minutes;

    //logical del juego
    int[] images = new int[] {R.mipmap.aire, R.mipmap.fuerza,R.mipmap.tierra,R.mipmap.sol,R.mipmap.persona,R.mipmap.perro,R.mipmap.mano,R.mipmap.katana, R.mipmap.agua, R.mipmap.arbol};
    String[] palabras_español = {"Aire", "Fuerza", "Tierra", "Sol", "Persona", "Perro", "Mano", "Katana", "Agua", "Árbol"};
    int[] respuestasBotones = new int[10];
    int indice = 0;
    int cantidadBuenas = 0;
    int cantidadIntentos = 0;
    private boolean stopTImer = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina);

        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"my-epic-selfie-demo.regular.ttf");
        mostrarPalabra = (TextView) findViewById(R.id.mostrarPalabra);
        mostrarPalabra.setTypeface(myTypeFace);

        txtCronometro  = (TextView) findViewById(R.id.txtCronometro);
        txtCronometro.setTypeface(myTypeFace);

        txtAciertos = (TextView) findViewById(R.id.txtAciertos);
        txtNumeroIntentos  = (TextView) findViewById(R.id.txtNumeroIntentos);
        txtAciertos.setTypeface(myTypeFace);
        txtNumeroIntentos.setTypeface(myTypeFace);


        imgStar = (ImageView)findViewById(R.id.imgStar);

        mostrarPalabras(indice);
        generarRespuesta();
        setRespuesta(indice);
        reverseTimer(180, txtCronometro);
    }

    public void setRespuesta(int indice){
        int opcion = 0;
        int[ ] opciones = new int[4];
        boolean flag = true;
        mImageView1 = (ImageButton)findViewById(R.id.btn1);
        mImageView1.setOnClickListener(this);
        mImageView2 = (ImageButton)findViewById(R.id.btn2);
        mImageView2.setOnClickListener(this);
        mImageView3 = (ImageButton)findViewById(R.id.btn3);
        mImageView3.setOnClickListener(this);
        mImageView4 = (ImageButton)findViewById(R.id.btn4);
        mImageView4.setOnClickListener(this);

        if (indice == images.length){
            mImageView1.setVisibility(View.INVISIBLE);
            mImageView2.setVisibility(View.INVISIBLE);
            mImageView3.setVisibility(View.INVISIBLE);
            mImageView4.setVisibility(View.INVISIBLE);
            mostrarPalabra.setVisibility(View.INVISIBLE);
            resultadosFinales();
        } else {
            txtAciertos.setVisibility(View.INVISIBLE);
            txtNumeroIntentos.setVisibility(View.INVISIBLE);
            mostrarPalabras(indice);
            if (respuestasBotones[indice] == 1) {
                for (int j = 1; j <= 4; j++) {
                    opcion = generaNumeroAleatorio(0, 10 - 1);
                    opciones[j-1] = opcion;
                    if (j!=1){
                        while (flag != false) {
                            flag = false;
                            for (int comprobar = 0; comprobar < 3; comprobar++) {
                                if (opciones[comprobar] == opcion) {
                                    comprobar = 4;
                                } else {
                                    flag = false;
                                }
                            }
                            opcion = generaNumeroAleatorio(0, 10 - 1);
                        }
                    }
                    switch (j) {
                        case 1:
                            mImageView1.setImageResource(images[indice]);
                            break;
                        case 2:
                            mImageView2.setImageResource(images[opcion]);
                            break;
                        case 3:
                            mImageView3.setImageResource(images[opcion]);
                            break;
                        case 4:
                            mImageView4.setImageResource(images[opcion]);
                            break;
                        default:
                            break;
                    }
                }
            } else if (respuestasBotones[indice] == 2) {
                for (int j = 1; j <= 4; j++) {
                    opcion = generaNumeroAleatorio(0, 10 - 1);
                    opciones[j-1] = opcion;
                    if (j!=1){
                        while (flag != false) {
                            flag = false;
                            for (int comprobar = 0; comprobar < 3; comprobar++) {
                                if (opciones[comprobar] == opcion) {
                                    comprobar = 4;
                                } else {
                                    flag = false;
                                }
                            }
                            opcion = generaNumeroAleatorio(0, 10 - 1);
                        }
                    }
                    switch (j) {
                        case 1:
                            mImageView1.setImageResource(images[opcion]);
                            break;
                        case 2:
                            mImageView2.setImageResource(images[indice]);
                            break;
                        case 3:
                            mImageView3.setImageResource(images[opcion]);
                            break;
                        case 4:
                            mImageView4.setImageResource(images[opcion]);
                            break;
                        default:
                            break;
                    }
                }
            } else if (respuestasBotones[indice] == 3) {

                for (int j = 1; j <= 4; j++) {
                    opcion = generaNumeroAleatorio(0, 10 - 1);
                    opciones[j-1] = opcion;
                    if (j!=1){
                        while (flag != false) {
                            flag = false;
                            for (int comprobar = 0; comprobar < 3; comprobar++) {
                                if (opciones[comprobar] == opcion) {
                                    comprobar = 4;
                                } else {
                                    flag = false;
                                }
                            }
                            opcion = generaNumeroAleatorio(0, 10 - 1);
                        }
                    }
                    switch (j) {
                        case 1:
                            mImageView1.setImageResource(images[opcion]);
                            break;
                        case 2:
                            mImageView2.setImageResource(images[opcion]);
                            break;
                        case 3:
                            mImageView3.setImageResource(images[indice]);
                            break;
                        case 4:
                            mImageView4.setImageResource(images[opcion]);
                            break;
                        default:
                            break;
                    }
                }
            } else if (respuestasBotones[indice] == 4) {
                for (int j = 1; j <= 4; j++) {
                    opcion = generaNumeroAleatorio(0, 10 - 1);
                    opciones[j-1] = opcion;
                    if (j!=1){
                        while (flag != false) {
                            flag = false;
                            for (int comprobar = 0; comprobar < 3; comprobar++) {
                                if (opciones[comprobar] == opcion) {
                                    comprobar = 4;
                                } else {
                                    flag = false;
                                }
                            }
                            opcion = generaNumeroAleatorio(0, 10 - 1);
                        }
                    }
                    switch (j) {
                        case 1:
                            mImageView1.setImageResource(images[opcion]);
                            break;
                        case 2:
                            mImageView2.setImageResource(images[opcion]);
                            break;
                        case 3:
                            mImageView3.setImageResource(images[opcion]);
                            break;
                        case 4:
                            mImageView4.setImageResource(images[indice]);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    int[] generarRespuesta(){
        for (int x = 0; x < images.length; x++){
            respuestasBotones[x] = generaNumeroAleatorio(1,4);
        }
        return respuestasBotones;
    }


    public static int generaNumeroAleatorio(int minimo, int maximo){
        int num = (int)Math.floor(Math.random()*(minimo-(maximo+1))+(maximo+1));
        return num;
    }

    public void mostrarPalabras(int x){
        if (indice != images.length){
            mostrarPalabra.setText(palabras_español[x]);
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case  R.id.btn1: {
                if (1 == respuestasBotones[indice]){
                    alertView("Has adivinado correctamente");
                    cantidadBuenas++;
                    if (indice != images.length){
                        indice++;
                        setRespuesta(indice);
                    }
                } else {
                    alertView("No has adivinado correctamente");
                    cantidadIntentos++;
                    ObjectAnimator animY = ObjectAnimator.ofFloat(mImageView1, "translationY", -60f, 0f);
                    animY.setDuration(400);//1sec
                    animY.setInterpolator(new BounceInterpolator());
                    animY.setRepeatCount(0);
                    animY.start();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                }
                //i++;
                break;
            }

            case R.id.btn2: {
                if (2 == respuestasBotones[indice]){
                    alertView("Has adivinado correctamente");
                    cantidadBuenas++;
                    if (indice != images.length){
                        indice++;
                        setRespuesta(indice);
                    }

                }else {
                    alertView("No has adivinado correctamente");
                    cantidadIntentos++;
                    ObjectAnimator animY = ObjectAnimator.ofFloat(mImageView2, "translationY", -60f, 0f);
                    animY.setDuration(400);//1sec
                    animY.setInterpolator(new BounceInterpolator());
                    animY.setRepeatCount(0);
                    animY.start();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                }
                //i++;
                break;
            }

            case R.id.btn3: {
                if (3 == respuestasBotones[indice]){
                    alertView("Has adivinado correctamente");
                    cantidadBuenas++;
                    if (indice != images.length){
                        indice++;
                        setRespuesta(indice);
                    }
                    //setRespuesta(indice);
                }else {
                    alertView("No has adivinado correctamente");
                    cantidadIntentos++;
                    ObjectAnimator animY = ObjectAnimator.ofFloat(mImageView3, "translationY", -60f, 0f);
                    animY.setDuration(400);//1sec
                    animY.setInterpolator(new BounceInterpolator());
                    animY.setRepeatCount(0);
                    animY.start();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                }
                //i++;
                break;
            }

            case R.id.btn4: {
                if (4 == respuestasBotones[indice]){
                    alertView("Has adivinado correctamente");
                    cantidadBuenas++;
                    if (indice != images.length){
                        indice++;
                        setRespuesta(indice);
                    }
                    //setRespuesta(indice);
                }else {
                    alertView("No has adivinado correctamente");
                    cantidadIntentos++;
                    ObjectAnimator animY = ObjectAnimator.ofFloat(mImageView4, "translationY", -60f, 0f);
                    animY.setDuration(400);//1sec
                    animY.setInterpolator(new BounceInterpolator());
                    animY.setRepeatCount(0);
                    animY.start();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                }
                //i++;
                break;
            }
        }
    }

    private void alertView( String message ) {
        Context context = this;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void alertFinalizo( String message ) {
        Context context = this;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Continuar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dialog.cancel();
                        restartActivity();
                    }
                });

        builder1.setNegativeButton(
                "",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert12 = builder1.create();
        alert12.show();
    }

    public void reverseTimer(int Seconds,final TextView tv){
        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText("TIEMPO : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Se acabó!");
                alertFinalizo("Se ha acabado el tiempo!");
            }
        }.start();

    }

    public void resultadosFinales(){
        txtAciertos.setVisibility(View.VISIBLE);
        txtNumeroIntentos.setVisibility(View.VISIBLE);

        txtAciertos.setText("Tus aciertos fueron: " + cantidadBuenas);

        txtNumeroIntentos.setText("Tus intentos fueron: " + cantidadIntentos);

        double promedio = Math.ceil(((cantidadBuenas - cantidadIntentos)*5)/images.length);

        switch((int) promedio){
            case 1:
                imgStar.setImageResource(R.drawable.star1);
                break;
            case 2:
                imgStar.setImageResource(R.drawable.star2);
                break;
            case 3:
                imgStar.setImageResource(R.drawable.star3);
                break;
            case 4:
                imgStar.setImageResource(R.drawable.star4);
                break;
            case 5:
                imgStar.setImageResource(R.drawable.star5);
                break;
        }

    }
    public void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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

