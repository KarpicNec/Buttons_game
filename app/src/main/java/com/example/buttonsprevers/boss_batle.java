package com.example.buttonsprevers;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class boss_batle extends AppCompatActivity
{
    @Override
    public void onBackPressed(){}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boss_fight);
        //музыка
        MediaPlayer music = MediaPlayer.create(boss_batle.this, R.raw.azulie_battle_full);
        music.setLooping(true);
        music.start();
        //фон и полоски здоровий
        LinearLayout phonc = (LinearLayout) findViewById(R.id.castle);
        phonc.setBackgroundResource(R.drawable.castle_boss);
        TextView wint = (TextView) findViewById(R.id.txt);
        ProgressBar hp = (ProgressBar) findViewById(R.id.BossHpBar);
        ProgressBar myhp = (ProgressBar) findViewById(R.id.MyHpBar);

        //объявления
        ImageView azulie = (ImageView) findViewById(R.id.boss);
        ImageView phon = (ImageView) findViewById(R.id.phon);
        ImageButton butt1 = (ImageButton) findViewById(R.id.mainb);
        butt1.setBackgroundResource(R.drawable.button_of_win);
        //анимация маг
        azulie.setBackgroundResource(R.drawable.azulie_simp_anim);
        AnimationDrawable anim = (AnimationDrawable) azulie.getBackground();
        anim.start();
        //постоянный урон, гонка с врагом
        ObjectAnimator.ofInt(myhp, "progress", myhp.getProgress() - 100)
                .setDuration(97000)
                .start();
        //кнопка для сражения
        butt1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(myhp.getProgress() == 0)
                {
                    music.stop();
                    Intent intenti = new Intent(boss_batle.this, pre_screen.class);
                    startActivity(intenti);
                    onDestroy();
                }
                if (hp.getProgress() == 0)
                {
                    music.stop();
                    azulie.setVisibility(View.INVISIBLE);
                    butt1.setClickable(false);
                    butt1.setVisibility(View.INVISIBLE);
                    hp.setVisibility(View.INVISIBLE);
                    myhp.setProgress(100);
                    myhp.setVisibility(View.INVISIBLE);
                    phonc.setBackgroundResource(R.drawable.win_phon);
                    wint.setText("YOU...");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            wint.setText("YOU     WIN");
                            phonc.setBackgroundResource(R.drawable.full_win_phon);
                        }
                    }, 2000);
                    final Handler handlere = new Handler();
                    handlere.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            System.exit(0);
                        }
                    }, 10000);
                }
                butt1.setBackgroundResource(R.drawable.off_button_of_win);
                butt1.setClickable(false);
                hp.setProgress(hp.getProgress() - 2);
                phon.setBackgroundResource(R.drawable.boss_gets_damage_anim);
                AnimationDrawable damag = (AnimationDrawable) phon.getBackground();
                damag.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        butt1.setClickable(true);
                        damag.stop();
                        butt1.setBackgroundResource(R.drawable.button_of_win);
                    }
                }, 1360);
            }
        });

    }
}
