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
    View view;
    SharedPreferences.Editor editor;
    String playerName;
    Boolean difrentName;
    Button btnRight;
    Button btnLeft;
    String msg;
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
    int pos;
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


        view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        btnRight = (Button) view.findViewById(R.id.right1);
        btnLeft = (Button) view.findViewById(R.id.left1);
        pos = getArguments().getInt("page");

        final View finalView = view;
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pos == 3) {
                    pos = pos - 1;
                    posss(pos);

                } else if (pos == 2) {
                    pos = pos - 1;
                    posss(pos);
                }
                else if(pos == 1)
                {
                    pos = 3;
                    posss(pos);
                }
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 1) {
                    pos = 1 + pos;

                    posss(pos);
                } else if (pos == 2) {
                    pos = 1 + pos;
                    posss(pos);
                }
                 else if (pos == 3)
            {
                pos = 1;
                posss(pos);
            }

            }
        });


        backToMainMenuButton = view.findViewById(R.id.backToMainMenu1);


        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackToMainMenuIntent = new Intent(getActivity(), Home.class);
                startActivity(BackToMainMenuIntent);
            }
        });
        textView = view.findViewById(R.id.SizeOfBoard);
        textViewScore = view.findViewById(R.id.textViewScore1);
        textViewName = view.findViewById(R.id.textViewName1);
        textViewScore1 = view.findViewById(R.id.textViewScore2);
        textViewName1 = view.findViewById(R.id.textViewName2);
        textViewScore2 = view.findViewById(R.id.textViewScore3);
        textViewName2 = view.findViewById(R.id.textViewName3);
        difrentName = true;
        posss(pos);
/*
        if(pos== 1)
        {
            btnLeft.setVisibility(view.GONE);
            preferences =this.getActivity().getSharedPreferences("PRES",0);
            editor = preferences.edit();
            name1 =preferences.getString("Name1","");
            name2 =preferences.getString("Name2","");
            name3 =preferences.getString("Name3","");
             rank1=preferences.getInt("Rank1",0);
            rank2=preferences.getInt("Rank2",0);
             rank3=preferences.getInt("Rank3",0);
            rank4=preferences.getInt("Rank4",0);
             rank5=preferences.getInt("Rank5",0);
            rank6=preferences.getInt("Rank6",0);
             rank7=preferences.getInt("Rank7",0);
            rank8=preferences.getInt("Rank8",0);
             rank9=preferences.getInt("Rank9",0);
            rank10=preferences.getInt("Rank10",0);
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
*/
        return view;
    }

    private  void posss(int pos)
    {
        if(pos== 1)
        {
/*           btnRight.setVisibility(view.VISIBLE);
            btnLeft.setVisibility(view.GONE);*/
            preferences =this.getActivity().getSharedPreferences("PRES",0);
            editor = preferences.edit();
            name1 =preferences.getString("Name1","");
            name2 =preferences.getString("Name2","");
            name3 =preferences.getString("Name3","");
            rank1=preferences.getInt("Rank1",0);
            rank2=preferences.getInt("Rank2",0);
            rank3=preferences.getInt("Rank3",0);
            msg="3X3";

        }
        else if(pos== 2)
        {
/*            btnLeft.setVisibility(view.VISIBLE);
            btnRight.setVisibility(view.VISIBLE);*/
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
            msg="4X4";
        }
        else if(pos== 3)
        {
/*            btnLeft.setVisibility(view.VISIBLE);
            btnRight.setVisibility(view.GONE);*/
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
            msg="5X5";
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
    }
}