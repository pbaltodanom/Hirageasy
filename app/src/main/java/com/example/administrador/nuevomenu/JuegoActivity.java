package com.example.administrador.nuevomenu;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Matrix;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class JuegoActivity extends Activity implements
        OnTouchListener, View.OnClickListener {

    Button btnStart, btnStop;
    int number;
    TextView textViewTime;
    TextView textViewPalabra;
    final CounterClass timer = new CounterClass(10000, 1000);

    ImageView choosenImageView;
    Bitmap bmp;
    Bitmap alteredBitmap;
    Canvas canvas;
    Paint paint;
    Matrix matrix;
    float downx = 0;
    float downy = 0;
    float upx = 0;
    float upy = 0;
    int[] images = new int[] {R.drawable.agua, R.drawable.fuerza,R.drawable.tierra,R.drawable.sol,R.drawable.persona,R.drawable.perro,R.drawable.mano,R.drawable.katana,
            R.drawable.agua, R.drawable.arbol};
    String[] palabras_español = {"Aire", "Fuerza", "Tierra", "Sol", "Persona", "Perro", "Mano", "Katana", "Agua", "Árbol"};
    String[] palabras_ingles = {"Air", "Strength", "Earth", "Sun", "Person", "Dog", "Hand", "Katana", "Water", "Tree"};
    String[] palabras_portugues = {"Ar","Força","Terra", "Sol", "Pessoa", "Cão", "Mão", "Katana", "Água", "Árvore"};
    MediaPlayer mp = null;
    int[] sounds = new int[] {R.raw.aire, R.raw.fuerza,R.raw.tierra,R.raw.sol,R.raw.persona,R.raw.perro,R.raw.mano,R.raw.katana,
            R.raw.agua, R.raw.arbol};

    ImageButton reproducirAudioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"my-epic-selfie-demo.regular.ttf");
        textViewPalabra = (TextView) findViewById(R.id.textViewPalabra);
        textViewPalabra.setTypeface(myTypeFace);

        choosenImageView = (ImageView) this.findViewById(R.id.ChoosenImageView);
        textViewPalabra = (TextView) findViewById(R.id.textViewPalabra);
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(this);
        choosenImageView.setOnTouchListener(this);

        final int imageId = (int)(Math.random() * images.length);
        cargar_imagen(imageId);

        reproducirAudioBtn = (ImageButton) findViewById(R.id.imageButton3);
        reproducirAudioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlay.pauseAudio();
                reproducirAudioBtn.setEnabled(false);
                onStop();
                managerOfSound(imageId);
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
                reproducirAudioBtn.setEnabled(true);
            }});
        mp.start();
    }

    protected void onStop()
    {
        if (mp != null)
            mp.stop();
        super.onStop();
    }

    public void onClick (View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void cargar_imagen(int imageId){
        number = getIntent().getExtras().getInt("valor");

        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        bmp = BitmapFactory.decodeResource(getResources(), images[imageId]);
        bmpFactoryOptions.inJustDecodeBounds = false;

        bmp = BitmapFactory.decodeResource(getResources(), images[imageId]);

        alteredBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp
                .getHeight(), bmp.getConfig());
        canvas = new Canvas(alteredBitmap);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        matrix = new Matrix();
        canvas.drawBitmap(bmp, 0, 0, paint);

        choosenImageView.setImageBitmap(alteredBitmap);
        choosenImageView.setOnTouchListener(this);

        if (number == 1){
            textViewPalabra.setText(palabras_ingles[imageId]);
        } else if (number == 2) {
            textViewPalabra.setText(palabras_español[imageId]);
        } else {
            textViewPalabra.setText(palabras_portugues [imageId]);
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer implements com.example.administrador.nuevomenu.CounterClass {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            textViewTime.setText("Se termino.");
        }

    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                upy = event.getY();
                canvas.drawLine(downx, downy, upx, upy, paint);
                choosenImageView.invalidate();
                downx = upx;
                downy = upy;
                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                upy = event.getY();
                canvas.drawLine(downx, downy, upx, upy, paint);
                choosenImageView.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
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
