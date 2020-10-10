package com.example.puzzlegame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


public class ChooseDifficultActivity extends AppCompatActivity {
    private ImageButton soundBtn,lionBtn;
    Sound sound=new Sound();
    ImageButton[] zooImages,zooImages2;
    int[] imagesDraw = {R.drawable.lion,R.drawable.dolphin,R.drawable.bird,R.drawable.elephant,R.drawable.dog,R.drawable.horse};
    Drawable imageChoose;
    SlicingImage slicingImage = new SlicingImage();

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
        Button gameZoo = findViewById(R.id.btn4);

        AnimationDrawable animationDrawable = (AnimationDrawable)gameZoo.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        ImageButton infoBtn = findViewById(R.id.howToPlayBtn);
        ImageButton backBtn = findViewById(R.id.levelBackMenu);
        soundBtn = findViewById(R.id.bSoundOffOnChoose);

        final ImageView doctorImage = findViewById(R.id.doctorImage);

        game9Btn.setOnClickListener(onClickListener);
        game15Btn.setOnClickListener(onClickListener);
        game24Btn.setOnClickListener(onClickListener);
        gameZoo.setOnClickListener(onClickListener);

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
                    newGame(3,"Cards");
                    break;
                case R.id.btn2:
                    Sound.menuClickSound.start();
                    sound.switchMusic(sound.gameMusic,sound.backgroundMusic);
                    newGame(4,"Cards");
                    break;
                case R.id.btn3:
                    Sound.menuClickSound.start();
                    sound.switchMusic(sound.gameMusic,sound.backgroundMusic);
                    newGame(5,"Cards");
                    break;
                case R.id.btn4:
                    Sound.menuClickSound.start();
                    openZooDialog();
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



    private void newGame(int level,String isImageChoosen) {
        Intent gameChooserIntent = new Intent();
            switch (level){
                case 3:
                    gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity9.class);
                    gameChooserIntent.putExtra("whatToShow",isImageChoosen);
                    break;
                case 4:
                    gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity15.class);
                    gameChooserIntent.putExtra("whatToShow",isImageChoosen);
                    break;
                case 5:
                    gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity24.class);
                    gameChooserIntent.putExtra("whatToShow",isImageChoosen);
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

    private void openZooDialog() {
        final Dialog zooDialog = new Dialog(ChooseDifficultActivity.this);
        zooDialog.setContentView(R.layout.dialog_zoo);

        final int[] levelChoosed = {3,135,405};
        imageChoose =getResources().getDrawable(imagesDraw[1]);
        zooImages = new ImageButton[6];
        Button startBtn = zooDialog.findViewById(R.id.startButton);
        zooImages[0]=zooDialog.findViewById(R.id.lion);
        zooImages[1]=zooDialog.findViewById(R.id.dolphin);
        zooImages[2]=zooDialog.findViewById(R.id.bird);
        zooImages[3]=zooDialog.findViewById(R.id.elephant);
        zooImages[4]=zooDialog.findViewById(R.id.dog);
        zooImages[5]=zooDialog.findViewById(R.id.horse);

        for(int i=0;i<6;i++)
        {
            zooImages[i].setOnClickListener(zooClickListener);
            BitmapDrawable drawable = (BitmapDrawable) zooImages[i].getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 135, 135, true);
            zooImages[i].setImageBitmap(scaledBitmap);
        }
        final ChipGroup level = zooDialog.findViewById(R.id.levelChipGroup);
        level.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.level3:
                        levelChoosed[0] =3;
                        levelChoosed[1]=135;
                        levelChoosed[2]=405;
                        break;
                    case R.id.level4:
                        levelChoosed[0] =4;
                        levelChoosed[1]=105;
                        levelChoosed[2]=420;
                        break;
                    case R.id.level5:
                        levelChoosed[0]=5;
                        levelChoosed[1]=84;
                        levelChoosed[2]=420;
                        break;
                }
            }
        });

        zooDialog.show();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.buttonGameSound.start();
                zooDialog.dismiss();
                slicingImage.splitImage(imageChoose,levelChoosed[0],levelChoosed[0],levelChoosed[2],levelChoosed[1]);
                newGame(levelChoosed[0],"Zoo");
            }
        });

    }
    View.OnClickListener zooClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int i=0;
            while (zooImages[i].getId()!=v.getId())
            {
                i++;
            }
            imageChoose =getResources().getDrawable(imagesDraw[i]);
        }
    };
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
