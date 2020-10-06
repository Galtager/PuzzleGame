package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeaderBoard extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SharedPreferences preferences1;
    SharedPreferences.Editor editor1;
    String playerName;
    Boolean difrentName;
    int lastScore;
    int rank1;
    int rank2;
    int rank3;

    String name1;
    String name2;
    String name3;

    TextView textView;
    TextView textViewName;
    TextView textViewScore;
    TextView textViewName1;
    TextView textViewScore1;
    TextView textViewName2;
    TextView textViewScore2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        textViewScore=findViewById(R.id.textViewScore1);
        textViewName=findViewById(R.id.textViewName1);
        textViewScore1=findViewById(R.id.textViewScore2);
        textViewName1=findViewById(R.id.textViewName2);
        textViewScore2=findViewById(R.id.textViewScore3);
        textViewName2=findViewById(R.id.textViewName3);

        Button backToMenuButton = findViewById(R.id.backToMainMenu1);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackToMainMenuIntent = new Intent(LeaderBoard.this,Home.class);
                startActivity(BackToMainMenuIntent);
            }
        });
        textView =findViewById(R.id.SizeOfBoard);

        int value1=1;
        int value2=2;
        int value3=3;
        int board;
/*        Intent intent = getIntent();
        *//*Intent extras = getIntent().getExtras();*//*
        board=intent.getStringExtra("boardSize");*/
        preferences1=getSharedPreferences("BOARD",0);
        board=preferences1.getInt("board",0);
if(board==value1)
{
    preferences =getSharedPreferences("PRES",0);
    textView.setText("3X3");
}
else if(board==value2)
{
    preferences =getSharedPreferences("PRES1",0);
    textView.setText("4X4");
}
else if(board==value3)
{
    preferences =getSharedPreferences("PRES2",0);
    textView.setText("5X5");
}

        editor = preferences.edit();
        name1 =preferences.getString("Name1","");
        name2 =preferences.getString("Name2","");
        name3 =preferences.getString("Name3","");
        rank1=preferences.getInt("Rank1",0);
        rank2=preferences.getInt("Rank2",0);
        rank3=preferences.getInt("Rank3",0);
/*            rank4=preferences.getInt("Rank4",0);
             rank5=preferences.getInt("Rank5",0);
            rank6=preferences.getInt("Rank6",0);
             rank7=preferences.getInt("Rank7",0);
            rank8=preferences.getInt("Rank8",0);
             rank9=preferences.getInt("Rank9",0);
            rank10=preferences.getInt("Rank10",0);*/
        lastScore=preferences.getInt("lastScore",0);
        playerName=preferences.getString("playerName","");
        difrentName=true;
        if(lastScore>rank1)
        {
            if(playerName==name2)
            {
                difrentName=false;
            }
            if(difrentName==true)
            {
                int temp =rank1;
                String tempStr = name1;
                name1=playerName;
                rank1=lastScore;
                rank2=temp;
                name2=tempStr;
                editor.putInt("Rank2",rank2);
                editor.putInt("Rank1",rank1);
                editor.putString("Name2",name2);
                editor.putString("Name1",name1);
            }
            else
            {
                rank1=lastScore;
                editor.putInt("Rank1",rank1);
            }
            editor.apply();

        }
        else if(lastScore>rank2)
        {
            if(playerName==name2)
            {
                difrentName=false;
            }
            if(difrentName==true) {
                int temp = rank2;
                String tempStr = name2;
                name2 = playerName;
                rank2 = lastScore;
                rank3 = temp;
                name3 = tempStr;
                editor.putInt("Rank3", rank3);
                editor.putInt("Rank2", rank2);
                editor.putString("Name3", name3);
                editor.putString("Name2", name2);
            }
            else
            {
                rank2=lastScore;
                editor.putInt("Rank2",rank2);

            }


        }
        else if(lastScore>rank3)
        {

            rank3=lastScore;
            editor.putInt("Rank3",rank3);
            editor.putString("Name3",playerName);


        }
        editor.apply();

/*
            textViewName.setText(playerName);
            textViewScore.setText(Integer.toString(lastScore));*/
        name1 =preferences.getString("Name1","");
        name2 =preferences.getString("Name2","");
        name3 =preferences.getString("Name3","");
        rank1=preferences.getInt("Rank1",0);
        rank2=preferences.getInt("Rank2",0);
        rank3=preferences.getInt("Rank3",0);
        textViewName.setText(name1);
        textViewScore.setText(Integer.toString(rank1));
        textViewName1.setText(name2);
        textViewScore1.setText(Integer.toString(rank2));
        textViewName2.setText(name3);
        textViewScore2.setText(Integer.toString(rank3));
    }

}