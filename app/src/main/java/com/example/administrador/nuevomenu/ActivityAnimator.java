package com.example.administrador.nuevomenu;

import android.app.Activity;
import com.example.administrador.nuevomenu.R;


public class ActivityAnimator
{
    public void flipHorizontalAnimation(Activity a)
    {
        a.overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
    }

}