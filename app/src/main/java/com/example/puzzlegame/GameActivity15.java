package com.example.puzzlegame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences1;
    SharedPreferences.Editor editor1;
    Sound sound=new Sound();

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


        ImageButton newGameBtn =findViewById(R.id.bNewGame15);
        ImageButton backBtn =findViewById(R.id.bBackMenu15);
        soundBtn = findViewById(R.id.bSoundOffOn15);


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


        newGameBtn.setOnClickListener(navigateBtnsClickListener);
        backBtn.setOnClickListener(navigateBtnsClickListener);
        soundBtn.setOnClickListener(navigateBtnsClickListener);

        if(Sound.check)
            soundBtn.setImageResource(R.drawable.soundon);


        cards = new Cards(N, N);
        newGame();
    }
    View.OnClickListener navigateBtnsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.bNewGame15:
                    Sound.menuClickSound.start();
                    newGame();
                    break;
                case R.id.bBackMenu15:
                    Sound.menuClickSound.start();
                    backMenu();
                    break;
                case R.id.bSoundOffOn15:
                    soundOffOn();
                    Sound.menuClickSound.start();
                    break;
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
            Sound.buttonGameSound.start();
            numOfSteps++;
            showGame();
            checkFinish();
        }
    }

    public void newGame() {
        cards.getNewCards();
        numOfSteps = 0;
        preferences = getSharedPreferences("PRES1",0);
        recordSteps=preferences.getInt("Rank1",0);
/*        recordSteps = Integer.parseInt(readFile("fbs15"));
        recordTV.setText(Integer.toString(recordSteps));*/
        showGame();
        check = false;
    }

    public void backMenu() {
        sound.switchMusic(sound.backgroundMusic,sound.gameMusic);
        finish();
    }

    public void showGame() {
        scoreTV.setText(Integer.toString(numOfSteps));

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                button[i][j].setImageResource(CADRS_ID[cards.getValueBoard(i, j)]);
    }

    public void checkFinish(){
        if(true){
        /*if(cards.finished(N, N)){*/
            showGame();
            Sound.winningSound.start();
            openDialog();
            if ((numOfSteps < recordSteps) || (recordSteps == 0)) {
                writeFile(Integer.toString(numOfSteps), "fbs15");
                recordTV.setText(Integer.toString(numOfSteps));
            }
            check = true;
        }
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
                preferences = getSharedPreferences("PRES1",0);
                editor=preferences.edit();
                editor.putInt("lastScore",numOfSteps);
                editor.putString("playerName",finishName.getText().toString());
                preferences1=getSharedPreferences("BOARD",0);
                editor1=preferences1.edit();
                editor1.putInt("board",2);
                editor1.apply();
                editor.apply();
                Intent HighScoreIntent = new Intent(GameActivity15.this,LeaderBoard.class);
/*                String value="1";
                HighScoreIntent.putExtra("board",value);*/
                startActivity(HighScoreIntent);
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
    public void soundOffOn(){
        if(Sound.check)
        {
            Sound.check=false;
            soundBtn.setImageResource(R.drawable.soundoff);
        }
        else
        {
            Sound.check=true;
            soundBtn.setImageResource(R.drawable.soundon);
        }
        sound.setSounds();
        sound.setMusic();
    }

}
