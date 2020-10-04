package com.example.puzzlegame;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ChooseDifficultActivity extends AppCompatActivity {
    
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficult);

        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        ImageButton howToPlayBtn = findViewById(R.id.howToPlayBtn);
        ImageButton backMenu = (ImageButton) this.findViewById(R.id.levelBackMenu);
        final ImageView doctorImage = this.findViewById(R.id.doctorImage);

        backMenu.setOnClickListener(onClickListener);


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
        howToPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    newGame(3);
                    break;
                case R.id.btn2:
                    newGame(4);
                    break;
                case R.id.btn3:
                    newGame(5);
                    break;
                case R.id.levelBackMenu:
                    backMenu();
            }
        }
    };
    private void newGame(int level) {
        Intent gameIntent = new Intent();
        switch (level){
            case 3:
                gameIntent = new Intent(ChooseDifficultActivity.this, GameActivity9.class);
                break;
            case 4:
                gameIntent = new Intent(ChooseDifficultActivity.this, GameActivity15.class);
                break;
            case 5:
                gameIntent = new Intent(ChooseDifficultActivity.this, GameActivity24.class);

                break;
        }
        gameIntent.putExtra("keylevel",level);
        gameIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(gameIntent);
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
    }
    public void backMenu() {
        finish();
    }
    public void openDialog(){
        final Dialog dialog = new Dialog(ChooseDifficultActivity.this);
        dialog.setContentView(R.layout.dialog_info);
        Button gotItButton = dialog.findViewById(R.id.gotItButton);
        dialog.show();
        gotItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
