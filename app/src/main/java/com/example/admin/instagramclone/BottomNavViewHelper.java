package com.example.admin.instagramclone;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavViewHelper {
    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationView) {
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context,BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        Intent home=new Intent(context,MainActivity.class);
                        context.startActivity(home);
                        break;
                    case R.id.menu_search:
                        Intent search=new Intent(context,SearchActivity.class);
                        context.startActivity(search);
                        break;
                    case R.id.menu_circle:
                        Intent add=new Intent(context,ShareActivity.class);
                        context.startActivity(add);
                        break;
                    case R.id.menu_notification:
                        Intent notif=new Intent(context,LikesActivity.class);
                        context.startActivity(notif);
                        break;
                    case R.id.menu_profile:
                        Intent profile=new Intent(context,ProfileActivity.class);
                        context.startActivity(profile);
                        break;
                }
                return false;
            }
        });
    }
}
