package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.chip.Chip;

import java.util.Locale;

public class Home extends AppCompatActivity {

    SwitchCompat soundSwitch;
    boolean langFlag = false;
    Sound sound=new Sound();
    String tempLang = "";
    Dialog dialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sound.realese();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startButton = findViewById(R.id.start_button);
        Button settingbutton = findViewById(R.id.setting_button);
        Button leaderBoard = findViewById(R.id.highscore_button);

        ImageButton facebook =findViewById(R.id.facebook);
        ImageButton instagram =findViewById(R.id.instagram);
        ImageButton github =findViewById(R.id.github);
        final ImageView doctorImageHome = this.findViewById(R.id.doctorImageHome);
        github.setOnClickListener(onClickListener);
        instagram.setOnClickListener(onClickListener);
        facebook.setOnClickListener(onClickListener);
        settingbutton.setOnClickListener(onClickListener);
        startButton.setOnClickListener(onClickListener);
        leaderBoard.setOnClickListener(onClickListener);

        Sound.backgroundMusic.start();

        YoYo.with(Techniques.Shake)
                .duration(3000)
                .repeat(2)
                .playOn(startButton);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doctorImageHome.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(3000)
                        .repeat(0)
                        .playOn(doctorImageHome);

            }
        },1000);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent browserIntent;
            switch (v.getId()) {
                case R.id.facebook:
                    Sound.buttonGameSound.start();
                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                    startActivity(browserIntent);
                    break;
                case R.id.instagram:
                    Sound.buttonGameSound.start();
                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com"));
                    startActivity(browserIntent);
                    break;
                case R.id.github:
                    Sound.buttonGameSound.start();
                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Galtager/PuzzleGame"));
                    startActivity(browserIntent);
                    break;
                case R.id.setting_button:
                    openDialog();
                    Sound.menuClickSound.start();
                    break;
                case R.id.start_button:
                    Sound.menuClickSound.start();
                    Intent chooseDifficultIntent = new Intent(Home.this, ChooseDifficultActivity.class);
                    startActivity(chooseDifficultIntent);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    break;
                case R.id.highscore_button:
                    Sound.menuClickSound.start();
                    Intent leaderBoardIntent = new Intent(Home.this,ViewPager.class);
                    startActivity(leaderBoardIntent);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    break;
            }
        }
    };
    public void openDialog(){
        langFlag=false;
        dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.dialog_setting);
        dialog.show();
        Chip hebrewChip = dialog.findViewById(R.id.hebrewChip);
        Chip englishChip = dialog.findViewById(R.id.englishChip);
        soundSwitch = dialog.findViewById(R.id.sound_switch);
        soundSwitch.setChecked(Sound.check);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    sound.check=true;
                else
                    sound.check=false;
            }
        });

        Button submitButton = dialog.findViewById(R.id.submitButton);

        hebrewChip.setOnClickListener(dialogClickListener);
        englishChip.setOnClickListener(dialogClickListener);
        submitButton.setOnClickListener(dialogClickListener);

    }
    View.OnClickListener dialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.hebrewChip:
                    Sound.buttonGameSound.start();
                    langFlag = true;
                    tempLang = "iw";
                    break;
                case R.id.englishChip:
                    Sound.buttonGameSound.start();
                    langFlag=true;
                    tempLang ="en";
                    break;
                case R.id.submitButton:
                    Sound.menuClickSound.start();
                    sound.setSounds();
                    sound.setMusic();
                    if(langFlag)
                    {
                        setAppLocale(tempLang);
                        restartActivity();
                    }
                    dialog.dismiss();
            }
        }
    };

    private void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(localeCode.toLowerCase()));
        resources.updateConfiguration(configuration,displayMetrics);
    }
    private void restartActivity()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}