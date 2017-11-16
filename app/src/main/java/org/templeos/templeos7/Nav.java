package org.templeos.templeos7;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
// import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Nav extends AppCompatActivity {

    Fragment godwords = new GodWords();
    Fragment livestream = new LiveStream();
    Fragment chat = new Chat();
    FragmentManager fm = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_godwords:
                    fm.beginTransaction().show(godwords).commit();
                    fm.beginTransaction().hide(livestream).commit();
                    fm.beginTransaction().hide(chat).commit();
                    return true;
                case R.id.navigation_livestream:
                    fm.beginTransaction().show(livestream).commit();
                    fm.beginTransaction().hide(godwords).commit();
                    fm.beginTransaction().hide(chat).commit();
                    return true;
                case R.id.navigation_chat:
                    fm.beginTransaction().show(chat).commit();
                    fm.beginTransaction().hide(livestream).commit();
                    fm.beginTransaction().hide(godwords).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.content, livestream, "2").commit();
        fm.beginTransaction().add(R.id.content, godwords, "1").commit();
        fm.beginTransaction().add(R.id.content, chat, "3").commit();

        fm.beginTransaction().show(godwords).commit();
        fm.beginTransaction().hide(livestream).commit();
        fm.beginTransaction().hide(chat).commit();
    }
}
