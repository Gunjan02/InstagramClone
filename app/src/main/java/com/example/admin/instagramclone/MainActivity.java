package com.example.admin.instagramclone;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();
        setViewPager();
    }

    private void setViewPager(){
        PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new CameraFragment());
        pagerAdapter.addFragment(new MessagesFragment());
        pagerAdapter.addFragment(new HomeFragment());
        ViewPager viewPager1=(ViewPager)findViewById(R.id.containerView);
        viewPager1.setAdapter(pagerAdapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager1);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_house);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_message);
    }
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationView=(BottomNavigationViewEx) findViewById(R.id.bottom_navview);
        BottomNavViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavViewHelper.enableNavigation(MainActivity.this,bottomNavigationView);
        Menu menu=bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);


    }
}
