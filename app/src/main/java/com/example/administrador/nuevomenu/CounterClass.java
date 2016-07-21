package com.example.administrador.nuevomenu;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by vsabo on 4/7/2016.
 */
public interface CounterClass {
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        void onTick(long millisUntilFinished);

        void onFinish();
}
