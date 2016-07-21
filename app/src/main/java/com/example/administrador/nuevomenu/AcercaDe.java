package com.example.administrador.nuevomenu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.widget.TextView;

public class AcercaDe extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        //Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"Toms_Handwriting.ttf");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(Html.fromHtml(getString(R.string.my_text)));


    }

}
