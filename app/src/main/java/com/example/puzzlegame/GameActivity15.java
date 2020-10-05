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

public class GameActivity15 extends AppCompatActivity {

    private final int N = 4;
    Cards cards;
    private ImageButton[][] button;
    private final int BUTTON_ID[][] = {{R.id.b1500, R.id.b1501, R.id.b1502, R.id.b1503},
            {R.id.b1510, R.id.b1511, R.id.b1512, R.id.b1513},
            {R.id.b1520, R.id.b1521, R.id.b1522, R.id.b1523},
            {R.id.b1530, R.id.b1531, R.id.b1532, R.id.b1533}};
    private final int CADRS_ID[] = {R.drawable.card1500, R.drawable.card1501, R.drawable.card1502, R.drawable.card1503,
            R.drawable.card1504, R.drawable.card1505, R.drawable.card1506, R.drawable.card1507,
            R.drawable.card1508, R.drawable.card1509, R.drawable.card1510, R.drawable.card1511,
            R.drawable.card1512, R.drawable.card1513, R.drawable.card1514, R.drawable.card1515};

    private TextView scoreTV;
    private int numOfSteps;
    private TextView recordTV;
    private int recordSteps;

//    private Sound setOnClickListenerund;
    private int clickSound;
    private int victorySound;
    private int setButtonSound;

    private ImageButton soundBtn;;

    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game15);

        button = new ImageButton[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                button[i][j] = (ImageButton) this.findViewById(BUTTON_ID[i][j]);
                button[i][j].setOnClickListener(onClickListener);
            }
        Typeface digitalFont = Typeface.createFromAsset(this.getAssets(), "font.ttf");


        ImageButton bNewGame =findViewById(R.id.bNewGame15);
        ImageButton bBackMenu =findViewById(R.id.bBackMenu15);
        soundBtn =findViewById(R.id.bSoundOffOn15);


        TextView titleTV = findViewById(R.id.gameTitle15);
        TextView textScoreTV =findViewById(R.id.tSScore15);
        scoreTV = findViewById(R.id.tScore15);
        TextView textRecordTV =findViewById(R.id.textBestScore15);
        recordTV =findViewById(R.id.tBestScore15);

        titleTV.setTypeface(digitalFont);
        textScoreTV.setTypeface(digitalFont);
        scoreTV.setTypeface(digitalFont);
        textRecordTV.setTypeface(digitalFont);
        recordTV.setTypeface(digitalFont);


        bNewGame.setOnClickListener(navigateBtnsClickListener);
        bBackMenu.setOnClickListener(navigateBtnsClickListener);
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
                case R.id.bNewGame24:
//                    sound.playSound(setButtonSound);
                    newGame();
                    break;
                case R.id.bBackMenu24:
//                    sound.playSound(setButtonSound);
                    backMenu();
                    break;
//                case R.id.bSoundOffOn24:
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
                Toast.makeText(GameActivity15.this, R.string.you_won_toast, Toast.LENGTH_SHORT).show();


        }
    };

    public void buttonFunction(int row, int column) {
        cards.moveCards(row, column);
        if(cards.resultMove()) {
//            sound.playSound(clickSound);
            numOfSteps++;
            showGame();
            checkFinish();
        }
    }

    public void newGame() {
        cards.getNewCards();
        numOfSteps = 0;
        recordSteps = Integer.parseInt(readFile("fbs15"));
        recordTV.setText(Integer.toString(recordSteps));
        showGame();
        check = false;
    }

    public void backMenu() {
        finish();
    }

//    public void aboutOnClick() {
//        startActivity(new Intent(GameActivity15.this, AboutActivity.class));
//    }

    public void showGame() {
        scoreTV.setText(Integer.toString(numOfSteps));

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                button[i][j].setImageResource(CADRS_ID[cards.getValueBoard(i, j)]);

//        if (sound.getCheckSound())
//            ibSound.setImageResource(R.drawable.soundon);
//        else ibSound.setImageResource(R.drawable.soundoff);
    }

    public void checkFinish(){
        if(cards.finished(N, N)){
            showGame();
            openDialog();
//            sound.playSound(victorySound);
            if ((numOfSteps < recordSteps) || (recordSteps == 0)) {
                writeFile(Integer.toString(numOfSteps), "fbs15");
                recordTV.setText(Integer.toString(numOfSteps));
            }
            check = true;
        }
    }

    public void soundOffOn() {
//        sound.setCheckSound(!sound.getCheckSound());
        showGame();
    }
    private void openDialog() {
        final Dialog dialog = new Dialog(GameActivity15.this);
        dialog.setContentView(R.layout.dialog_finished);


        Button finishButton = dialog.findViewById(R.id.finishButton);
        final EditText finishName = dialog.findViewById(R.id.finishName);
        TextView finishSteps = dialog.findViewById(R.id.finishSteps);
        finishSteps.setText(numOfSteps + " Steps");
        dialog.show();
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity15.this, finishName.getText()+"", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }

    public void writeFile(String text, String FILENAME) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String FILENAME) {
        String text;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
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
