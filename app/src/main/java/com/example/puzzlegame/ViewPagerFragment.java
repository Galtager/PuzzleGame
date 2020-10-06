package com.example.puzzlegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPagerFragment extends Fragment {
    SharedPreferences preferences;
    /*   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());*/
    SharedPreferences.Editor editor;
    String playerName;
    Boolean difrentName;
    int lastScore;
    int rank1;
    int rank2;
    int rank3;
    int rank4;
    int rank5;
    int rank6;
    int rank7;
    int rank8;
    int rank9;
    int rank10;

    String name1;
    String name2;
    String name3;
    Button backToMainMenuButton;
    TextView textView;
    TextView textViewName;
    TextView textViewScore;
    TextView textViewName1;
    TextView textViewScore1;
    TextView textViewName2;
    TextView textViewScore2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPagerFragment newInstance(String param1, String param2) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*
        preferences =this.getActivity().getSharedPreferences("PRES",0);
        editor = preferences.edit();
*/


        View view= inflater.inflate(R.layout.fragment_view_pager, container, false);
        backToMainMenuButton= view.findViewById(R.id.backToMainMenu1);

        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackToMainMenuIntent = new Intent(getActivity(),Home.class);
                startActivity(BackToMainMenuIntent);
            }
        });
        textView =view.findViewById(R.id.SizeOfBoard);
        String msg=getArguments().getString("massage");
        int pos = getArguments().getInt("page");

        textViewScore=view.findViewById(R.id.textViewScore1);
        textViewName=view.findViewById(R.id.textViewName1);
        textViewScore1=view.findViewById(R.id.textViewScore2);
        textViewName1=view.findViewById(R.id.textViewName2);
        textViewScore2=view.findViewById(R.id.textViewScore3);
        textViewName2=view.findViewById(R.id.textViewName3);
        difrentName =true;

        if(pos== 1)
        {
            preferences =this.getActivity().getSharedPreferences("PRES",0);
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
            rank10=preferences.getInt("Rank10",0);*//*
            lastScore=preferences.getInt("lastScore",0);
            playerName=preferences.getString("playerName","");
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

*//*
            textViewName.setText(playerName);
            textViewScore.setText(Integer.toString(lastScore));*/
        }
        else if(pos== 2)
        {
            textViewScore=view.findViewById(R.id.textViewScore1);
            textViewName=view.findViewById(R.id.textViewName1);
            preferences =this.getActivity().getSharedPreferences("PRES1",0);
            editor = preferences.edit();
            lastScore=preferences.getInt("lastScore",0);
            playerName=preferences.getString("playerName","");
            name1 =preferences.getString("Name1","");
            name2 =preferences.getString("Name2","");
            name3 =preferences.getString("Name3","");
            rank1=preferences.getInt("Rank1",0);
            rank2=preferences.getInt("Rank2",0);
            rank3=preferences.getInt("Rank3",0);

        }
        else if(pos== 3)
        {
            textViewScore=view.findViewById(R.id.textViewScore1);
            textViewName=view.findViewById(R.id.textViewName1);
            preferences =this.getActivity().getSharedPreferences("PRES2",0);
            editor = preferences.edit();
            lastScore=preferences.getInt("lastScore",0);
            playerName=preferences.getString("playerName","");
            name1 =preferences.getString("Name1","");
            name2 =preferences.getString("Name2","");
            name3 =preferences.getString("Name3","");
            rank1=preferences.getInt("Rank1",0);
            rank2=preferences.getInt("Rank2",0);
            rank3=preferences.getInt("Rank3",0);

        }
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
        textView.setText(msg);
        return view;
    }
}