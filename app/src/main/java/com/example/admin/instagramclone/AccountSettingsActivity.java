package com.example.admin.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    ImageView back;
    private Context mcontext;
    SectionsStatePagerAdapter sectionsStatePagerAdapter;
    private ViewPager viewPager;
    RelativeLayout mRelLayout;
    private static final int ACTIVITY_NUM=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        mcontext=AccountSettingsActivity.this;
        viewPager=findViewById(R.id.containerView);
        mRelLayout=findViewById(R.id.RelLayout1);
        back=findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AccountSettingsActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });
        setupAccountSettingsList();
        setupFragments();
        setupBottomNavigationView();
    }

    private void setupFragments(){
        sectionsStatePagerAdapter=new SectionsStatePagerAdapter(getSupportFragmentManager());
        sectionsStatePagerAdapter.addFragment(new EditProfileFragment(),"Edit Profile");
        sectionsStatePagerAdapter.addFragment(new LogOutFragment(),"Log Out");
    }

    private void setViewPager(int FragmentNumber){
        mRelLayout.setVisibility(View.GONE);
        viewPager.setAdapter(sectionsStatePagerAdapter);
        viewPager.setCurrentItem(FragmentNumber);
    }
    private void setupAccountSettingsList(){
        ListView listView=(ListView)findViewById(R.id.listSettings);
        ArrayList<String> options=new ArrayList<>();
        options.add("Edit Profile");
        options.add("Log Out");
        ArrayAdapter adapter=new ArrayAdapter(mcontext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setViewPager(i);
            }
        });

    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationView=(BottomNavigationViewEx) findViewById(R.id.bottom_navview);
        BottomNavViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavViewHelper.enableNavigation(AccountSettingsActivity.this,bottomNavigationView);
        Menu menu=bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);


    }

}
