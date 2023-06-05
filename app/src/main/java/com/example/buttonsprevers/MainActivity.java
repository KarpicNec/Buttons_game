package com.example.buttonsprevers;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onBackPressed(){}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.dense_forest_day);
        music.setLooping(true);
        music.start();
//объявки
        ImageButton imageButtsword = (ImageButton) findViewById(R.id.buttSword);
        imageButtsword.setBackgroundResource(R.drawable.sword_butt);
        ImageButton imageButtshield = (ImageButton) findViewById(R.id.buttShield);
        imageButtshield.setBackgroundResource(R.drawable.butt_shield);
        ImageButton imageButtbow = (ImageButton) findViewById(R.id.buttBow);
        imageButtbow.setBackgroundResource(R.drawable.bow_butt);
        ImageButton imageButtMedic = (ImageButton) findViewById(R.id.buttMedic);
        imageButtMedic.setBackgroundResource(R.drawable.helfing_but);
        ImageButton imageButtMagic = (ImageButton) findViewById(R.id.buttMagic);
        imageButtMagic.setBackgroundResource(R.drawable.water_but);
        ImageButton imageButtrun = (ImageButton) findViewById(R.id.buttRun);
        imageButtrun.setBackgroundResource(R.drawable.run_butt);

        LinearLayout phon = (LinearLayout) findViewById(R.id.all);
        phon.setBackgroundResource(R.drawable.outcare_day);

        ProgressBar hp = (ProgressBar) findViewById(R.id.HpBar);
        TextView hpV = (TextView) findViewById(R.id.text_hp);
        ProgressBar myhp = (ProgressBar) findViewById(R.id.MyHpBar);

        ImageView image = (ImageView) findViewById(R.id.enemy);
        ImageView enemfon = ((ImageView) findViewById(R.id.enem_eff));
        enemfon.setVisibility(View.INVISIBLE);

        ImageView centranim = ((ImageView) findViewById(R.id.centanim));
        centranim.setBackgroundResource(R.drawable.podskaska);
        final int[] monstercol = {0};

//действия врага
        class atakerer
        {
            int situat, enemyis, time, uron;
            public atakerer(int situat, int enemyis)
            {
                this.situat = situat;
                this.enemyis = enemyis;
                uron = 20;
            }
            void replace(int situat, int enemyis)
            {
                if (situat == 1) //день
                {
                    if(enemyis == 1) //пугало
                    {
                        image.setBackgroundResource(R.drawable.stone_enem_anim);
                        AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                        frameAnimation.start();
                        uron = 20;
                    }
                    else if (enemyis == 2) //близнецы
                    {
                        image.setBackgroundResource(R.drawable.sibling_stone_anim);
                        AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                        frameAnimation.start();
                        uron = 25;
                    }
                    else if (enemyis == 3) //гигант
                    {
                        image.setBackgroundResource(R.drawable.stone_boss_anim);
                        AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                        frameAnimation.start();
                        uron = 30;
                    }
                }
                else if (situat == 2) //ночь
                {
                    if (enemyis == 1) //паук
                    {
                        image.setBackgroundResource(R.drawable.spider_anim);
                        AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                        frameAnimation.start();
                        uron = 34;
                    }
                    else if (enemyis == 2) //призрак
                    {
                        image.setBackgroundResource(R.drawable.goast_anim);
                        AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                        frameAnimation.start();
                        uron = 25;
                    }
                    else if (enemyis == 3) //леди
                    {
                        image.setBackgroundResource(R.drawable.spider_lady);
                        AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                        frameAnimation.start();
                        uron = 25;
                    }
                }
            }
            void atack (int situat, int enemyis)
            {
                if (situat == 1) //день
                {
                    if(enemyis == 1) //пугало
                    {
                        image.setBackgroundResource(R.drawable.stone_enemy_atack_anim);
                        time = 2250;
                    }
                    else if (enemyis == 2) //близнецы
                    {
                        image.setBackgroundResource(R.drawable.stone_siblings_atack_anim);
                        time = 2650;
                    }
                    else if (enemyis == 3) //гигант
                    {
                        image.setBackgroundResource(R.drawable.stone_giant_atack);
                        time = 1500;
                    }
                }
                else if (situat == 2) //ночь
                {
                    if (enemyis == 1) //паук
                    {
                        image.setBackgroundResource(R.drawable.spider_atack_anim);
                        time = 1800;
                    }
                    else if (enemyis == 2) //призрак
                    {
                        image.setBackgroundResource(R.drawable.goast_atack_anim);
                        time = 3240;
                    }
                    else if (enemyis == 3) //леди
                    {
                        image.setBackgroundResource(R.drawable.spider_lady_atack_anim);
                        time = 3060;
                    }
                }
            }
        }
        MediaPlayer musica = MediaPlayer.create(MainActivity.this, R.raw.under_the_sky);
        //атака врагом
        atakerer atakerer = new atakerer(1, 2);
        atakerer.replace(atakerer.situat, atakerer.enemyis);
        Random atacktime = new Random();
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run()
            {
                atakerer.atack(atakerer.situat, atakerer.enemyis);
                Log.d("BUTTONS", "Atack is going");
                AnimationDrawable atacka = (AnimationDrawable) image.getBackground();
                atacka.start();
                scheduler.schedule(new Runnable() {
                    @Override
                    public void run()
                    {
                        atacka.stop();
                        atakerer.replace(atakerer.situat, atakerer.enemyis);
                        if(Ciferki.buttis == 1)
                        {
                            myhp.setProgress(myhp.getProgress() - atakerer.uron);
                        }
                        myhp.setProgress(myhp.getProgress());
                        //смерть
                        if (myhp.getProgress() <= 0)
                        {
                            music.stop();
                            musica.stop();
                            Intent intenti = new Intent(MainActivity.this, pre_screen.class);
                            startActivity(intenti);
                            myhp.setProgress(100);
                            scheduler.shutdown();
                            onDestroy();
                        }

                    }
                }, atakerer.time, TimeUnit.MILLISECONDS);
            }
        }, 0, atacktime.nextInt(9 - 5) + 5, TimeUnit.SECONDS);
        atakerer.replace(atakerer.situat, atakerer.enemyis);
//здоровье врага
        final int[] damag = {10};
        class HpConterer
        {
            int hpc;
            HpConterer()
            {
                hpc = 100;
                hp.setProgress(hpc);
            }
            void getDamage(int uron)
            {
                int bilo = hp.getProgress();
                bilo -= ((uron + 0.0) / hpc * 100);
                if (bilo > 0)
                {
                    hp.setProgress(bilo);
                    String sec = hpV.getText().toString();
                    int now = 0, i = 0;
                    while(sec.charAt(i) != ' ')
                    {
                        now = now * 10 + (sec.charAt(i) - '0');
                        i++;
                    }
                    now -= uron;
                    sec = Integer.toString(now) + " / " + Integer.toString(hpc);
                    hpV.setText(sec);
                }
                else
                {
                    hp.setProgress(100);
                    hpc += 10;
                    damag[0] += 2;
                    monstercol[0]++;
                    hpV.setText(Integer.toString(hpc) + " / " + Integer.toString(hpc));
                    //облако смены врага
                    enemfon.setBackgroundResource(R.drawable.replace_perc);
                    AnimationDrawable rep = (AnimationDrawable) enemfon.getBackground();
                    rep.start();
                    enemfon.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            if (monstercol[0] == 10) //смена на ночь
                            {
                                phon.setBackgroundResource(R.drawable.outcare_night);
                                atakerer.situat = 2;
                                music.stop();
                                musica.start();
                                musica.setLooping(true);
                            }
                            if (monstercol[0] == 20) //смена на босса
                            {
                                musica.stop();
                                Intent intentu = new Intent(MainActivity.this, boss_batle.class);
                                startActivity(intentu);
                                myhp.setProgress(100);
                                scheduler.shutdown();
                                onDestroy();
                            }
                            atakerer.enemyis = (atakerer.enemyis % 3) + 1;
                            atakerer.replace(atakerer.situat, atakerer.enemyis);
                            rep.stop();
                            enemfon.setVisibility(View.INVISIBLE);
                        }
                    }, 700);
                }
            }
        }
        HpConterer laun = new HpConterer();
//меч
        imageButtsword.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Log.d("BUTTONS", "atack butt is going");
                imageButtsword.setClickable(false);
                imageButtMagic.setClickable(false);
                imageButtsword.setBackgroundResource(R.drawable.off_sword_but);
                laun.getDamage(damag[0]);
                if (hp.getProgress() != 100)
                {
                    enemfon.setBackgroundResource(R.drawable.sword_atack_anim);
                    AnimationDrawable rep = (AnimationDrawable) enemfon.getBackground();
                    rep.start();
                    enemfon.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            rep.stop();
                            enemfon.setVisibility(View.INVISIBLE);
                        }
                    }, 780);
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        imageButtsword.setClickable(true);
                        imageButtMagic.setClickable(true);
                        imageButtsword.setBackgroundResource(R.drawable.sword_butt);
                    }
                }, 740);
            }
        });
//щит
        imageButtshield.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Ciferki.buttis = 1;
                imageButtshield.setBackgroundResource(R.drawable.off_shield);
                imageButtshield.setClickable(false);
                imageButtbow.setClickable(false);
                ObjectAnimator.ofInt(myhp, "progress", myhp.getProgress() + 2)
                        .setDuration(2000)
                        .start();
                centranim.setBackgroundResource(R.drawable.shield_prot_anim);
                AnimationDrawable protection = (AnimationDrawable) centranim.getBackground();
                Log.d("BUTTONS", "Shield butt is going");
                protection.start();
                final Handler handlere = new Handler();
                handlere.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        Ciferki.buttis = 2;
                        protection.stop();
                        imageButtbow.setClickable(true);
                        myhp.setProgress(myhp.getProgress());
                    }
                }, 1750);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        Ciferki.buttis = 1;
                        imageButtshield.setBackgroundResource(R.drawable.butt_shield);
                        imageButtshield.setClickable(true);
                    }
                }, 7000);
            }
        });
//лук
        imageButtbow.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                imageButtbow.setClickable(false);
                imageButtshield.setClickable(false);
                imageButtbow.setBackgroundResource(R.drawable.off_bow_butt);
                centranim.setBackgroundResource(R.drawable.bow_animatic_atack);
                AnimationDrawable bowatack = (AnimationDrawable) centranim.getBackground();
                Log.d("BUTTONS", "Bow butt is going");
                bowatack.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        bowatack.stop();
                        imageButtbow.setBackgroundResource(R.drawable.bow_butt);
                        laun.getDamage(damag[0] + damag[0] / 5);
                        imageButtbow.setClickable(true);
                        imageButtshield.setClickable(true);
                    }
                }, 2790);
            }
        });
//лечение
        imageButtMedic.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                imageButtMedic.setClickable(false);
                imageButtMedic.setBackgroundResource(R.drawable.off_helfing_but);
                ObjectAnimator.ofInt(myhp, "progress", myhp.getProgress() + 20)
                        .setDuration(3000)
                        .start();
                Log.d("BUTTONS", "Medic butt is going");
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        imageButtMedic.setClickable(true);
                        imageButtMedic.setBackgroundResource(R.drawable.helfing_but);
                    }
                }, 10000);
            }
        });
//побег
        imageButtrun.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                imageButtrun.setClickable(false);
                imageButtrun.setBackgroundResource(R.drawable.off_run_butt);
                Intent intenti = new Intent(MainActivity.this, pre_screen.class);
                startActivity(intenti);
                myhp.setProgress(100);
                scheduler.shutdown();
                onDestroy();
            }
        });
//магия
        imageButtMagic.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                imageButtsword.setClickable(false);
                imageButtMagic.setClickable(false);
                imageButtMagic.setBackgroundResource(R.drawable.off_water_butt);
                laun.getDamage(damag[0] + damag[0] / 2);
                if (hp.getProgress() != 100)
                {
                    enemfon.setBackgroundResource(R.drawable.water_atack);
                    AnimationDrawable rep = (AnimationDrawable) enemfon.getBackground();
                    rep.start();
                    enemfon.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            rep.stop();
                            enemfon.setVisibility(View.INVISIBLE);
                        }
                    }, 3190);
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        imageButtMagic.setClickable(true);
                        imageButtsword.setClickable(true);
                        imageButtMagic.setBackgroundResource(R.drawable.water_but);
                    }
                }, 3190);
            }
        });
    }
}