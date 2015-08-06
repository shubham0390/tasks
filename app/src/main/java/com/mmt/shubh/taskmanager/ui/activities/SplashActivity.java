package com.mmt.shubh.taskmanager.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mmt.shubh.taskmanager.R;
import com.mmt.shubh.taskmanager.ui.fragments.SplashFragment;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashFragment fragment = SplashFragment.newInstance();
        getFragmentManager().beginTransaction().add(fragment, "updateText").commit();
    }

}
