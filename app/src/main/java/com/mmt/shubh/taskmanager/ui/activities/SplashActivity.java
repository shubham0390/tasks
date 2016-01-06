package com.mmt.shubh.taskmanager.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mmt.shubh.taskmanager.JsonParser;
import com.mmt.shubh.taskmanager.R;
import com.mmt.shubh.taskmanager.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.taskmanager.ui.injection.component.DaggerSplashActivityComponent;
import com.mmt.shubh.taskmanager.ui.injection.component.SplashActivityComponent;
import com.mmt.shubh.taskmanager.ui.injection.module.SplashActivityModule;
import com.mmt.shubh.taskmanager.ui.presenter.SplashPresenter;
import com.mmt.shubh.taskmanager.ui.view.SplashView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter.attachView(this);

    }

    @Override
    protected void injectDependency(ApplicationComponent component) {
        SplashActivityComponent activityComponent = DaggerSplashActivityComponent.builder()
                .applicationComponent(component).splashActivityModule(new SplashActivityModule()).build();
        activityComponent.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.addSeedData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


    @Override
    public void startHomeActivity() {
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String s) {

    }
}
