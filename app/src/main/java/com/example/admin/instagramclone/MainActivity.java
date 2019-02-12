package com.example.admin.instagramclone;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity {
    //private static final String TAG="Main Activity";
    private static final int ACTIVITY_NUM=0;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        setupFirebase();
        imageLoader();
        setupBottomNavigationView();
        setViewPager();
        mAuth.signOut();
    }



    private void imageLoader(){
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(MainActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
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

    private void checkCurrentUser(FirebaseUser user){
        if(user==null){
            Intent login=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(login);
        }
    }
    private void setupFirebase(){

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                checkCurrentUser(user);
                if(user!=null){
                    //Log.d(TAG, "onAuthStateChanged: signedIn()"+user.getUid());
                }else{
                    //Log.d(TAG,"onAuthStateChanged: SignedOut()");
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
