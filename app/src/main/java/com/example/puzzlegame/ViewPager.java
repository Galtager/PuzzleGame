package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class ViewPager extends AppCompatActivity implements View.OnClickListener{
    TextView tvLeft;
    TextView tvRight;
    ImageButton leftNavigate,rightNavigate,backBtn;
    androidx.viewpager.widget.ViewPager viewPager;
    ViewPageFragmentCollectionAdapter adapter;
    String temp;
    int lastScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        leftNavigate = this.findViewById(R.id.leftBtn);
        rightNavigate = this.findViewById(R.id.rightBtn);
        backBtn = this.findViewById(R.id.bBackMenuPager);

        leftNavigate.setOnClickListener(onClickListener);
        rightNavigate.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);



        viewPager =findViewById(R.id.ViewPager);
        adapter = new ViewPageFragmentCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);



/*        tvLeft=(TextView)findViewById(R.id.left1);
        tvRight=(TextView)findViewById(R.id.right1);
        tvRight.setOnClickListener(this);*/

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

    @Override
    public void onClick(View view) {
        viewPager.arrowScroll(View.FOCUS_LEFT);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tab;
            switch (v.getId()){
                case R.id.rightBtn:
                    tab = viewPager.getCurrentItem();
                    tab++;
                    viewPager.setCurrentItem(tab);
                    Sound.menuClickSound.start();
                    break;
                case R.id.leftBtn:
                    tab = viewPager.getCurrentItem();
                    tab--;
                    viewPager.setCurrentItem(tab);
                    Sound.menuClickSound.start();
                    break;
                case R.id.bBackMenuPager:
                    Sound.menuClickSound.start();
                    finish();
                    break;


            }
        }
    };
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
