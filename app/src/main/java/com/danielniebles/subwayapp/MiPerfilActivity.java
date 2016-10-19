package com.danielniebles.subwayapp;

import android.content.Intent;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MiPerfilActivity extends NavExActivity {

    private ViewPager mViewPager;
    FrameLayout contentFrameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mi_perfil);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_mi_perfil, contentFrameLayout);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager2);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout2);

        mViewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(pageAdapter);
    }

    public class PageAdapter extends FragmentPagerAdapter{
        public PageAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new MiperfilFragment();
                case 1: return new FavoritosFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title=" ";
            switch (position){
                case 0:
                    title="Mi perfil";
                    break;
                case 1:
                    title="Favoritos";
                    break;
            }
            return title;
        }
    }
}
