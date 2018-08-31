package com.example.zff.mediaplayer_textureview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.zff.mediaplayer_textureview.Fragment.ActivitiesFragment;
import com.example.zff.mediaplayer_textureview.Fragment.MainFragment;
import com.example.zff.mediaplayer_textureview.Fragment.SortFragment;
import com.example.zff.mediaplayer_textureview.component.NoScrollViewPager;
import com.example.zff.mediaplayer_textureview.component.Tablayout_item;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private NoScrollViewPager viewPager;
    private TabLayout tabLayout;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        viewPager = (NoScrollViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        editText = (EditText)findViewById(R.id.EditText_toolbar);
        editText.setOnClickListener(this);
        initViewPager();
        initTabLayout();
    }

    private void initViewPager(){
       viewPager.setAdapter(this.pagerAdapter);
    }

    FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if(position == 0){
                 fragment = new MainFragment();
            }else if(position == 1){
                 fragment = new SortFragment();
            }else {
                 fragment = new ActivitiesFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    private void initTabLayout(){
        for(int i =0;i<3;i++){
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        for(int i =0;i<3;i++){
            Tablayout_item tablayout_item = new Tablayout_item(this);
            tablayout_item.setImage_text(i);
            tabLayout.getTabAt(i).setCustomView(tablayout_item);
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.EditText_toolbar:
                SearchActivity.actionStartSearchActivity(MainActivity.this);
                break;
                default:
                    break;
        }

    }
}
