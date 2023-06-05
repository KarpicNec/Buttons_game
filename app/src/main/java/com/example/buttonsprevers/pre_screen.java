package com.example.buttonsprevers;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class pre_screen extends AppCompatActivity {
    @Override
    public void onBackPressed(){}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_screen);
        //музыка
        MediaPlayer music = MediaPlayer.create(pre_screen.this, R.raw.menu_thingy);
        music.setLooping(true);
        music.start();
        //объявления
        ImageButton startButt = (ImageButton) findViewById(R.id.imagestartbutt);
        startButt.setBackgroundResource(R.drawable.startbutt);
        LinearLayout phonc = (LinearLayout) findViewById(R.id.prescreen);
        phonc.setBackgroundResource(R.drawable.start_fon);
        //случай если игрок не справился
        if (Ciferki.waker > 1)
        {
            startButt.setVisibility(View.INVISIBLE);
            phonc.setBackgroundResource(R.drawable.defeat);
            AnimationDrawable frameAnimation = (AnimationDrawable) phonc.getBackground();
            frameAnimation.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    frameAnimation.stop();
                    phonc.setBackgroundResource(R.drawable.start_fon);
                    startButt.setVisibility(View.VISIBLE);
                }
            }, 9500);
        }
        else
        {
            Ciferki.waker = 2;
        }
        //переход к основе
        Intent intent = new Intent(this, MainActivity.class);
        startButt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Log.d("BUTTONS", "We are goind to the game :D");
                startButt.setClickable(false);
                startButt.setBackgroundResource(R.drawable.dark_start_butt);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        music.stop();
                        startActivity(intent);
                    }
                }, 300);
            }
        });
    }
}