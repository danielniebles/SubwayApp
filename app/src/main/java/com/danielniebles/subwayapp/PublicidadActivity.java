package com.danielniebles.subwayapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PublicidadActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    String user, mail, sex, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicidad);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("Name");
        mail = extras.getString("Mail");
        sex = extras.getString("Sex");
        date = extras.getString("Date");

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(pageAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Jamón").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Pavo y Jamón").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Pavo").setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.mMiPerfil:
                Intent intent = new Intent(this,MiPerfilActivity.class);
                intent.putExtra("Name", user);
                intent.putExtra("Mail", mail);
                intent.putExtra("Sex", sex);
                intent.putExtra("Date", date);
                startActivity(intent);
                break;
            case R.id.mPrincipal:
                Intent intent3 = new Intent(this,MainActivity.class);
                intent3.putExtra("Name", user);
                intent3.putExtra("Mail", mail);
                intent3.putExtra("Sex", sex);
                intent3.putExtra("Date", date);
                startActivity(intent3);
                break;
            case R.id.mLigeros:
                break;
            case R.id.mSanduches:
                Intent intent2 = new Intent(this,Publicidad2Activity.class);
                intent2.putExtra("Name", user);
                intent2.putExtra("Mail", mail);
                intent2.putExtra("Sex", sex);
                intent2.putExtra("Date", date);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
