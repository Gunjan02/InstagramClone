package com.example.admin.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
    }

    private void setupSettingsList(){

        ArrayList<String> options=new ArrayList<>();
        options.add("Edit Profile");
        options.add("Sign Out");
    }
}
