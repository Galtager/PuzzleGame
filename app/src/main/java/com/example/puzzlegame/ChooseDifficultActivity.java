package com.example.puzzlegame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ChooseDifficultActivity extends AppCompatActivity {
    private ImageButton soundBtn;
    Sound sound=new Sound();

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficult);

        Button game9Btn = findViewById(R.id.btn1);
        Button game15Btn = findViewById(R.id.btn2);
        Button game24Btn = findViewById(R.id.btn3);

        ImageButton infoBtn = findViewById(R.id.howToPlayBtn);
        ImageButton backBtn = findViewById(R.id.levelBackMenu);
        soundBtn = findViewById(R.id.bSoundOffOnChoose);

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

            }
        },1000);

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
