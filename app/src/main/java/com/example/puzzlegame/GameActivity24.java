package com.example.puzzlegame;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class GameActivity24 extends Activity {

    private final int N = 5;
    Cards cards;
    private ImageButton[][] button;
    private final int BUT_ID[][] = {{R.id.b2400, R.id.b2401, R.id.b2402, R.id.b2403, R.id.b2404},
            {R.id.b2410, R.id.b2411, R.id.b2412, R.id.b2413, R.id.b2414},
            {R.id.b2420, R.id.b2421, R.id.b2422, R.id.b2423, R.id.b2424},
            {R.id.b2430, R.id.b2431, R.id.b2432, R.id.b2433, R.id.b2434},
            {R.id.b2440, R.id.b2441, R.id.b2442, R.id.b2443, R.id.b2444}};
    private final int CARD_ID[] = {R.drawable.card2400, R.drawable.card2401, R.drawable.card2402, R.drawable.card2403, R.drawable.card2404,
            R.drawable.card2405, R.drawable.card2406, R.drawable.card2407, R.drawable.card2408, R.drawable.card2409,
            R.drawable.card2410, R.drawable.card2411, R.drawable.card2412, R.drawable.card2413, R.drawable.card2414,
            R.drawable.card2415, R.drawable.card2416, R.drawable.card2417, R.drawable.card2418, R.drawable.card2419,
            R.drawable.card2420, R.drawable.card2421, R.drawable.card2422, R.drawable.card2423, R.drawable.card2424};

    private TextView scoreTV;
    private int numOfSteps;
    private TextView recordTV;
    private int recordSteps;

//    private Sound sound;
    private int clickSound;
    private int victorySound;
    private int setButtonSound;

    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game24);

        button = new ImageButton[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                button[i][j] = (ImageButton) this.findViewById(BUT_ID[i][j]);
                button[i][j].setOnClickListener(onClickListener);
            }
        Typeface digitalFont = Typeface.createFromAsset(this.getAssets(), "font.ttf");

        ImageButton bNewGame = findViewById(R.id.bNewGame24);
        ImageButton bBackMenu = findViewById(R.id.bBackMenu24);
        ImageButton ibSound = findViewById(R.id.bSoundOffOn24);


        TextView titleTV = findViewById(R.id.gameTitle24);
        TextView textScoreTV = findViewById(R.id.tSScore24);
        scoreTV = findViewById(R.id.tScore24);
        TextView textRecordTV = findViewById(R.id.tBestSScore24);
        recordTV =findViewById(R.id.tBestScore24);

        titleTV.setTypeface(digitalFont);
        textScoreTV.setTypeface(digitalFont);
        scoreTV.setTypeface(digitalFont);
        textRecordTV.setTypeface(digitalFont);
        recordTV.setTypeface(digitalFont);

        bBackMenu.setOnClickListener(navigateBtnsClickListener);
        bNewGame.setOnClickListener(navigateBtnsClickListener);
        ibSound.setOnClickListener(navigateBtnsClickListener);

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
            switch (v.getId()) {
                case R.id.bNewGame24:
//                    sound.playSound(setButtonSound);
                    newGame();
                    break;
                case R.id.bBackMenu24:
//                    sound.playSound(setButtonSound);
                    backMenu();
                    break;
                case R.id.bSoundOffOn24:
                    soundOffOn();
//                    sound.playSound(setButtonSound);
                    break;
                default:
                    break;
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!check) {
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++)
                        if (v.getId() == BUT_ID[i][j])
                            butFunc(i, j);
            }
            else
                Toast.makeText(GameActivity24.this, R.string.you_won_24, Toast.LENGTH_SHORT).show();

        }
    };

    public void butFunc(int row, int columb) {
        cards.moveCards(row, columb);
        if (cards.resultMove()) {
//            sound.playSound(clickSound);
            numOfSteps++;
            showGame();
            checkFinish();
        }
    }

    public void newGame() {
        cards.getNewCards();
        numOfSteps = 0;
        recordSteps = Integer.parseInt(readFile("fbs24"));
        recordTV.setText(Integer.toString(recordSteps));
        showGame();
        check = false;
    }


    public void backMenu() {
        finish();
    }


    public void showGame() {
        scoreTV.setText(Integer.toString(numOfSteps));

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                button[i][j].setImageResource(CARD_ID[cards.getValueBoard(i, j)]);

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
                writeFile(Integer.toString(numOfSteps), "fbs24");
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
        final Dialog dialog = new Dialog(GameActivity24.this);
        dialog.setContentView(R.layout.dialog_finished);


        Button finishButton = dialog.findViewById(R.id.finishButton);
        final EditText finishName = dialog.findViewById(R.id.finishName);
        TextView finishSteps = dialog.findViewById(R.id.finishSteps);
        finishSteps.setText(numOfSteps + " Steps");
        dialog.show();
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity24.this, finishName.getText()+"", Toast.LENGTH_SHORT).show();
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
