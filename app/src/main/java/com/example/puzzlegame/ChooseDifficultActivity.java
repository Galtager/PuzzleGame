package com.example.puzzlegame;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ChooseDifficultActivity extends AppCompatActivity {
    private ImageButton soundBtn;
    Sound sound=new Sound();
    private ImageView card24;
    private  ImageView card15;
    private ImageView card9;
    private int counter;
    private TextView boardMsg;
    @Override
    protected void onStart() {
        super.onStart();
        if(!Sound.backFromGame){
            if(!Sound.backgroundMusic.isPlaying())
                Sound.backgroundMusic.start();}
        Sound.backFromGame=false;


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!Sound.activitySwitchFlag)
            Sound.backgroundMusic.pause();
        Sound.activitySwitchFlag = false;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        counter=3;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficult);
        boardMsg = findViewById(R.id.boardMsg);
        Button game9Btn = findViewById(R.id.btn1);
        Button game15Btn = findViewById(R.id.btn2);
        Button game24Btn = findViewById(R.id.btn3);
        final TextView puzzelTextChoose = findViewById(R.id.Choose);
        final TextView puzzelextLevels = findViewById(R.id.Levels);
        ImageButton infoBtn = findViewById(R.id.howToPlayBtn);
        ImageButton backBtn = findViewById(R.id.levelBackMenu);
        soundBtn = findViewById(R.id.bSoundOffOnChoose);
        card9 = findViewById(R.id.card908);
        card15=findViewById(R.id.card1515);
        card24 = findViewById(R.id.card2424);

        final ImageView doctorImage = findViewById(R.id.doctorImage);

        game9Btn.setOnClickListener(onClickListener);
        game15Btn.setOnClickListener(onClickListener);
        game24Btn.setOnClickListener(onClickListener);

        soundBtn.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);






        if(Sound.check)
            soundBtn.setImageResource(R.drawable.soundon);
        else
            soundBtn.setImageResource(R.drawable.soundoff);


        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sound.menuClickSound.start();
                openDialog();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doctorImage.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(3000)
                        .repeat(0)
                        .playOn(doctorImage);
                puzzelTextChoose.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInRight)
                        .duration(2000)
                        .repeat(0)
                        .playOn(puzzelTextChoose);
                puzzelextLevels.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft)
                        .duration(2000)
                        .repeat(0)
                        .playOn(puzzelextLevels);
            }
        },1000);
        play24();
        playMsg();
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    Sound.menuClickSound.start();
                    Sound.activitySwitchFlag=true;
                    sound.switchMusic(sound.gameMusic,sound.backgroundMusic);
                    newGame(3);
                    break;
                case R.id.btn2:
                    Sound.menuClickSound.start();
                    sound.switchMusic(sound.gameMusic,sound.backgroundMusic);
                    newGame(4);
                    break;
                case R.id.btn3:
                    Sound.menuClickSound.start();
                    sound.switchMusic(sound.gameMusic,sound.backgroundMusic);
                    newGame(5);
                    break;
                case R.id.levelBackMenu:
                    Sound.menuClickSound.start();
                    back();
                    break;
                case R.id.bSoundOffOnChoose:
                    soundOffOn();
                    Sound.menuClickSound.start();
                    break;
            }
        }
    };

    private  void playMsg()
    {
        boardMsg.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(10000)
                .repeat(0)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        if(counter==1)
                        {
                            boardMsg.setText(R.string.secondMsg);
                            counter=2;
                        }
                        else if(counter==2)
                        {
                            boardMsg.setText(R.string.lastMsg);

                            counter=3;
                        }
                        else if(counter==3)
                        {
                            boardMsg.setText(R.string.firstMsg);
                            counter=1;
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        boardMsg.setVisibility(View.INVISIBLE);
                        playMsg();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .playOn(boardMsg);
    }
    private  void play9()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        card9.setVisibility(View.VISIBLE);


        YoYo.with(Techniques.SlideOutUp)
                .duration(20000)
                .repeat(0)
                .interpolate(new AccelerateDecelerateInterpolator())
                .interpolate(new BounceInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        card9.setVisibility(View.INVISIBLE);
                        play15();

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .playOn( card9);
        YoYo.with(Techniques.RotateIn)
                .duration(20000)
                .repeat(10)
                .playOn( card9);
            }
        },100);
    }
    private  void play15()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                card15.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutRight)
                        .duration(15000)
                        .repeat(0)
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                card15.setVisibility(View.INVISIBLE);
                                play24();

                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        })
                        .playOn( card15);
                YoYo.with(Techniques.RotateIn)
                        .duration(15000)
                        .repeat(10)
                        .playOn( card15);
            }
        },100);
    }
    private  void play24()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                card24.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutDown)
                        .duration(20000)
                        .repeat(0)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .interpolate(new BounceInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                card24.setVisibility(View.INVISIBLE);
                                play9();

                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        })
                        .playOn(card24);
                YoYo.with(Techniques.RotateIn)
                        .duration(20000)
                        .repeat(10)
                        .playOn(card24);
            }
        },100);
    }



    private void newGame(int level) {
        Intent gameChooserIntent = new Intent();
        switch (level){
            case 3:
                gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity9.class);
                break;
            case 4:
                gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity15.class);
                break;
            case 5:
                gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity24.class);
                break;
        }
        Sound.activitySwitchFlag=true;
        Sound.backFromGame=true;
        startActivity(gameChooserIntent);
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
    }
    public void back() {

        finish();
    }

    public void openDialog(){
        final Dialog infoDialog = new Dialog(ChooseDifficultActivity.this);
        infoDialog.setContentView(R.layout.dialog_info);
        Button okBtn = infoDialog.findViewById(R.id.gotItButton);
        infoDialog.show();
        final ImageView imageViewCard1=infoDialog.findViewById(R.id.card1);
        final ImageView imageViewCard2=infoDialog.findViewById(R.id.card2);
        final ImageView imageViewCard3=infoDialog.findViewById(R.id.card3);
        final ImageView imageViewCard4=infoDialog.findViewById(R.id.card4);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewCard1.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft)
                        .duration(1000)
                        .repeat(0)
                        .playOn(imageViewCard1);
                imageViewCard2.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInRight)
                        .duration(3000)
                        .repeat(0)
                        .playOn(imageViewCard2);
                imageViewCard3.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInUp)
                        .duration(5000)
                        .repeat(0)
                        .playOn(imageViewCard3);
                imageViewCard4.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(7000)
                        .repeat(0)
                        .playOn(imageViewCard4);
            }
        },1000);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.buttonGameSound.start();
                infoDialog.dismiss();
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    public void soundOffOn(){
        if(Sound.check)
        {
            Sound.check=false;
            soundBtn.setImageResource(R.drawable.soundoff);
        }
        else
        {
            Sound.check=true;
            soundBtn.setImageResource(R.drawable.soundon);
        }
        sound.setSounds();
        sound.setMusic();
    }

}
