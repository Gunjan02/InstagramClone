package com.example.admin.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM =4 ;
    private ImageView profilephoto;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //setupBottomNavigationView();
        setupToolbar();
        gridSetup();
        setProfileImage();
        setupActivityWidgets();
    }
    private void setupToolbar(){
        Toolbar toolbar=findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profileItem:

                        break;
                }
                return false;
            }
        });
        ImageView imageView=findViewById(R.id.profile_menu);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this,AccountSettingsActivity.class);
                startActivity(i);
            }
        });

    }

    private void gridSetup(){
        ArrayList<String> imgurls=new ArrayList<>();
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgurls.add("https://i.redd.it/59kjlxxf720z.jpg");
        setupImageGrid(imgurls);
    }
    private void setupImageGrid(ArrayList<String> imgUrls){
        GridView gridView=(GridView)findViewById(R.id.gridView);
        GridImageAdapter imageAdapter=new GridImageAdapter(ProfileActivity.this,R.layout.layout_grid_imageview,"",imgUrls);
        gridView.setAdapter(imageAdapter);
    }
    private void setProfileImage(){
        String imgUrl="www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgUrl,profilephoto,progressBar,"https://");
    }
    private void setupActivityWidgets(){
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        profilephoto=(ImageView)findViewById(R.id.profile_image);
    }
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationView=(BottomNavigationViewEx) findViewById(R.id.bottom_navview);
        BottomNavViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavViewHelper.enableNavigation(ProfileActivity.this,bottomNavigationView);
        Menu menu=bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }

}
