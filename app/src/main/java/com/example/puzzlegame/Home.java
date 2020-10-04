package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.chip.Chip;

import java.util.Locale;

public class Home extends AppCompatActivity {

    boolean[] langFlag = {false};
    boolean[] soundFlag = {false};
    String[] tempLang = {""};
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startButton = findViewById(R.id.start_button);
        Button settingbutton = findViewById(R.id.setting_button);
        ImageButton facebook =findViewById(R.id.facebook);
        ImageButton instagram =findViewById(R.id.instagram);
        ImageButton github =findViewById(R.id.github);

        github.setOnClickListener(onClickListener);
        instagram.setOnClickListener(onClickListener);
        facebook.setOnClickListener(onClickListener);
        settingbutton.setOnClickListener(onClickListener);
        startButton.setOnClickListener(onClickListener);





        YoYo.with(Techniques.Shake)
                .duration(3000)
                .repeat(2)
                .playOn(startButton);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent browserIntent;
            switch (v.getId()) {
                case R.id.facebook:
                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                    startActivity(browserIntent);
                    break;
                case R.id.instagram:
                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com"));
                    startActivity(browserIntent);
                    break;
                case R.id.github:
                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Galtager/PuzzleGame"));
                    startActivity(browserIntent);
                    break;
                case R.id.setting_button:
                    openDialog();
                    break;
                case R.id.start_button:
                    Intent chooseDifficultIntent = new Intent(Home.this, ChooseDifficultActivity.class);
                    startActivity(chooseDifficultIntent);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    break;
            }
        }
    };
    public void openDialog(){
        dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.dialog_setting);
        dialog.show();
        Chip hebrewChip = dialog.findViewById(R.id.hebrewChip);
        Chip englishChip = dialog.findViewById(R.id.englishChip);
        Chip soundOnChip = dialog.findViewById(R.id.soundOnChip);
        Chip soundOffChip = dialog.findViewById(R.id.soundOffChip);

        Button submitButton = dialog.findViewById(R.id.submitButton);

        hebrewChip.setOnClickListener(dialogClickListener);
        englishChip.setOnClickListener(dialogClickListener);
        submitButton.setOnClickListener(dialogClickListener);
        soundOffChip.setOnClickListener(dialogClickListener);
        soundOnChip.setOnClickListener(dialogClickListener);


//        hebrewChip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setAppLocale("iw");
//                dialog.dismiss();
//                restartActivity();
//            }
//        });
//        englishChip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setAppLocale("en");
//                dialog.dismiss();
//                restartActivity();
//            }
//        });

    }
    View.OnClickListener dialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.hebrewChip:
                    langFlag[0] = true;
                    tempLang[0] = "iw";
                    break;
                case R.id.englishChip:
                    langFlag[0]=true;
                    tempLang[0]="en";
                    break;
                case R.id.soundOnChip:
                    soundFlag[0] = true;
                    break;
                case R.id.soundOffChip:
                    soundFlag[0] = true;
                    break;
                case R.id.submitButton:
                    if (soundFlag[0])
                        Toast.makeText(Home.this, "Music Changes", Toast.LENGTH_SHORT).show();
                    if(langFlag[0])
                    {

                        setAppLocale(tempLang[0]);
                        dialog.dismiss();
                        restartActivity();}


            }
        }
    };

    private void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else {
            configuration.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(configuration,displayMetrics);
    }
    private void restartActivity()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}