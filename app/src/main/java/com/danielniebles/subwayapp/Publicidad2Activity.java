package com.danielniebles.subwayapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class Publicidad2Activity extends NavExActivity {

    private ViewPager mViewPager;
    String user, mail, sex, date;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_publicidad2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_publicidad2, contentFrameLayout);

        /*Bundle extras = getIntent().getExtras();
        user = extras.getString("Name");
        mail = extras.getString("Mail");
        sex = extras.getString("Sex");
        date = extras.getString("Date");*/

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager2);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout2);

        mViewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(pageAdapter);
    }

    public class PageAdapter extends FragmentPagerAdapter {
        public PageAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new SubatunFragment();
                case 1: return new SubitalianoFragment();
                case 2: return new SubitalianobmtFragment();
                default: return null;
            }
        }
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title=" ";
            switch (position){
                case 0:
                    title="Atún";
                    break;
                case 1:
                    title="Italianíssimo";
                    break;
                case 2:
                    title="Italiano B.M.T";
                    break;
            }
            return title;
        }
    }

}
