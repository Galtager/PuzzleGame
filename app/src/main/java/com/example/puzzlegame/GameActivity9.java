package com.example.puzzlegame;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;


public class GameActivity9 extends AppCompatActivity {

    private final int N = 3;
    Cards cards;
    private ImageButton[][] button;
    private final int BUTTON_ID[][] = {{R.id.b900, R.id.b901, R.id.b902},
            {R.id.b910, R.id.b911, R.id.b912},
            {R.id.b920, R.id.b921, R.id.b922}};
    private final int CARDS_ID[] = {R.drawable.card900, R.drawable.card901, R.drawable.card902,
            R.drawable.card903, R.drawable.card904, R.drawable.card905,
            R.drawable.card906, R.drawable.card907, R.drawable.card908};

    private TextView scoreTV;
    private int numOfSteps;
    private TextView recordTV;
    private int recordSteps;

    private int clickSound;
    private int victorySound;
    private int setButtonSound;

    private ImageButton soundBtn;;

    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game9);

        button = new ImageButton[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                button[i][j] = findViewById(BUTTON_ID[i][j]);
                button[i][j].setOnClickListener(onClickListener);
            }
        Typeface digitalFont = Typeface.createFromAsset(this.getAssets(), "font.ttf");

        soundBtn = findViewById(R.id.bSoundOffOn9);
        ImageButton newGameBtn =findViewById(R.id.bNewGame9);
        ImageButton backBtn = findViewById(R.id.bBackMenu9);



        TextView titleTV = findViewById(R.id.gameTitle9);
        TextView textScoreTV =findViewById(R.id.tSScore9);
        scoreTV = findViewById(R.id.tScore9);
        TextView textRecordTV = findViewById(R.id.tBestSScore9);
        recordTV =findViewById(R.id.tBestScore9);

        titleTV.setTypeface(digitalFont);
        textScoreTV.setTypeface(digitalFont);
        scoreTV.setTypeface(digitalFont);
        textRecordTV.setTypeface(digitalFont);
        recordTV.setTypeface(digitalFont);




        newGameBtn.setOnClickListener(navigateBtnsClickListener);
        backBtn.setOnClickListener(navigateBtnsClickListener);
        soundBtn.setOnClickListener(navigateBtnsClickListener);



//        AssetManager AM = getAssets();
//        sound = new Sound(AM);
//        clickSound = sound.loadSound("Schelchok.mp3");
//        victorySound = sound.loadSound("Victory.mp3");
//        setButtonSound = sound.loadSound("Schelchok1.mp3");
//
//        try {
//            sound.setCheckSound(getIntent().getExtras().getBoolean("checkSound"));
//        } catch(Exception e) {
//            sound.setCheckSound(true);
//        }

        cards = new Cards(N, N);
        newGame();
    }
    View.OnClickListener navigateBtnsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.bNewGame9:
//                    sound.playSound(setButtonSound);
                    newGame();
                    break;
                case R.id.bBackMenu9:
//                    sound.playSound(setButtonSound);
                    backMenu();
                    break;
//                case R.id.bSoundOffOn9:
//                    soundOffOn();
//                    sound.playSound(setButtonSound);
//                    break;
                default:
                    break;
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!check) {
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++)
                        if (v.getId() == BUTTON_ID[i][j])
                            buttonFunction(i, j);
            }
            else
                Toast.makeText(GameActivity9.this, R.string.you_won_toast, Toast.LENGTH_SHORT).show();

        }
    };

    public void buttonFunction(int row, int column) {
        cards.moveCards(row, column); // re arrange the board when clicking button
        if(cards.resultMove()) { // if the move is done correct
//            sound.playSound(clickSound);
            numOfSteps++;
            showGame(); // place the new images on the buttons
            checkFinish();
        }
    }

    public void newGame() {
        cards.getNewCards();
        numOfSteps = 0;
        recordSteps = Integer.parseInt(readFile("fbs9"));
        recordTV.setText(Integer.toString(recordSteps));
        showGame();
        check = false;
    }

    public void backMenu() {
        finish();
    }

    public void showGame() {
        scoreTV.setText(Integer.toString(numOfSteps));

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                button[i][j].setImageResource(CARDS_ID[cards.getValueBoard(i, j)]);
//        if (sound.getCheckSound())
//            ibSound.setImageResource(R.drawable.soundon);
//        else ibSound.setImageResource(R.drawable.soundoff);
    }

    public void checkFinish(){
        if(cards.finished(N, N)){
            showGame();
//            sound.playSound(victorySound);
            openDialog();
            if ((numOfSteps < recordSteps) || (recordSteps == 0)) {
                writeFile(Integer.toString(numOfSteps), "fbs9");
                recordTV.setText(Integer.toString(numOfSteps));
            }

            check = true;
        }
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(GameActivity9.this);
        dialog.setContentView(R.layout.dialog_finished);


        Button finishButton = dialog.findViewById(R.id.finishButton);
        final EditText finishName = dialog.findViewById(R.id.finishName);
        TextView finishSteps = dialog.findViewById(R.id.finishSteps);
        finishSteps.setText(numOfSteps + " Steps");
        dialog.show();
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity9.this, finishName.getText()+"", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }

//    public void soundOffOn() {
//        sound.setCheckSound(!sound.getCheckSound());
//        showGame();
//    }

    public void writeFile(String text, String file) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(file, MODE_PRIVATE)));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String file) {
        String text;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(file)));
            text = br.readLine();
        } catch(IOException e) {
            text = "0";
        }
        return text;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
