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

public class Publicidad2Activity extends AppCompatActivity {

    private ViewPager mViewPager;
    String user, mail, sex, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicidad2);

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

        ActionBar.Tab tab = actionBar.newTab().setText("Atún").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Italianíssimo").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Italiano B.M.T").setTabListener(tabListener);
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
                Intent intent2 = new Intent(this,PublicidadActivity.class);
                intent2.putExtra("Name", user);
                intent2.putExtra("Mail", mail);
                intent2.putExtra("Sex", sex);
                intent2.putExtra("Date", date);
                startActivity(intent2);
            case R.id.mSanduches:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
