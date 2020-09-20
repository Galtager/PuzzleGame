package com.example.puzzlegame;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PupUpWindowActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pup_up_window);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int hight=dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(hight*.5));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;

        Bundle myBundle = getIntent().getExtras();
        int res_image =myBundle.getInt("image");
        int res_txt=myBundle.getInt("difficult");
        ImageView imageView = findViewById(R.id.imagePupUp);
        imageView.setImageResource(res_image);
        TextView textView = findViewById(R.id.userDifficultChoose);
        textView.setText(res_txt);
        getWindow().setAttributes(params);

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////Button listener for new activity PLAY the Chosen Difficult level///////////////
/*        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PupUpWindowActivity,)
                startActivity(intent);
            }
        });*/
       //////////////////////////////////////////////////////////////////////////////////////////////
    }
}