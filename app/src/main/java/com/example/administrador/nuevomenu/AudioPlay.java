package com.example.administrador.nuevomenu;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AudioPlay {
    public static MediaPlayer mediaPlayer;
    private static SoundPool soundPool;
    public static boolean isplayingAudio=false;
    public static void playAudio(Context c, int id){
        mediaPlayer = MediaPlayer.create(c,id);
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        if(!mediaPlayer.isPlaying())
        {
            isplayingAudio=true;
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }

    public static void isPlaying(){
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }
    }

    public static void pauseAudio(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    public static void stopAudio(){
        isplayingAudio=false;
        mediaPlayer.stop();
    }
}
