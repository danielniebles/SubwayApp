package com.danielniebles.subwayapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class PublicidadActivity extends NavExActivity {

    private ViewPager mViewPager;
    String user, mail, sex, date;
    FrameLayout contentFrameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_publicidad);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_publicidad, contentFrameLayout);

        /*Bundle extras = getIntent().getExtras();
        user = extras.getString("Name");
        mail = extras.getString("Mail");
        sex = extras.getString("Sex");
        date = extras.getString("Date");*/



        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);

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
                case 0: return new Subjamon6Fragment();
                case 1: return new Subpavoyjamon6Fragment();
                case 2: return new Subpechugapavo6Fragment();
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
                    title="Jamón";
                    break;
                case 1:
                    title="Pavo y jamón";
                    break;
                case 2:
                    title="Pechuga de pavo";
                    break;
            }
            return title;
        }
    }
}
