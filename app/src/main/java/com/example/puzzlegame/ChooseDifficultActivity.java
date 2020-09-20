package com.example.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseDifficultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficult);

        ImageButton imageButton1 = findViewById(R.id.btn1);
        ImageButton imageButton2 = findViewById(R.id.btn2);
        ImageButton imageButton3 = findViewById(R.id.btn3);
        imageButtonClickListener imageButtonClickListener = new imageButtonClickListener();
        imageButton1.setOnClickListener(imageButtonClickListener);
        imageButton2.setOnClickListener(imageButtonClickListener);
        imageButton3.setOnClickListener(imageButtonClickListener);

    }
    private class imageButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Intent pupUpWindow = new Intent(ChooseDifficultActivity.this,PupUpWindowActivity.class);
            if(view.getId()== R.id.btn1)
            {
                pupUpWindow.putExtra("image", R.drawable.image3);
                pupUpWindow.putExtra("difficult", R.string.begginer);
            }
            if(view.getId()== R.id.btn2)
            {
                pupUpWindow.putExtra("image", R.drawable.image1);
                pupUpWindow.putExtra("difficult", R.string.amature);
            }
            if(view.getId()== R.id.btn3)
            {
                pupUpWindow.putExtra("image", R.drawable.image2);
                pupUpWindow.putExtra("difficult", R.string.professional);
            }
            startActivity(pupUpWindow);
        }
    }
}