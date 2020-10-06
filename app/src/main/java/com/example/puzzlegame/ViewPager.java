package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class ViewPager extends AppCompatActivity{
    TextView tvLeft;
    TextView tvRight;
    androidx.viewpager.widget.ViewPager viewPager;
    ViewPageFragmentCollectionAdapter adapter;
    String temp;
    int lastScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager =findViewById(R.id.ViewPager);
        adapter = new ViewPageFragmentCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
/*        tvLeft=(TextView)findViewById(R.id.left);
        tvRight=(TextView)findViewById(R.id.right);
        tvRight.setOnClickListener(this);
        tvLeft.setOnClickListener(this);*/
/*        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });*/
/*        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() > viewPager.getLeft())
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() < viewPager.getRight())
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
            }
        });*/
    }

}