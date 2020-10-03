package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button startButton = findViewById(R.id.start_button);
        ImageButton facebook =findViewById(R.id.facebook);
        ImageButton instagram =findViewById(R.id.instagram);
        ImageButton github =findViewById(R.id.github);

        github.setOnClickListener(onClickListener);
        instagram.setOnClickListener(onClickListener);
        facebook.setOnClickListener(onClickListener);



        YoYo.with(Techniques.Shake)
                .duration(3000)
                .repeat(2)
                .playOn(startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseDifficultIntent = new Intent(Home.this, ChooseDifficultActivity.class);
                startActivity(chooseDifficultIntent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
            }
        });


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



            }
        }
    };
}