package com.example.puzzlegame;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


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

        backMenu.setOnClickListener(onClickListener);
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
        Dialog dialog = new Dialog(ChooseDifficultActivity.this);
        dialog.setContentView(R.layout.dialog_info);
        dialog.show();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
