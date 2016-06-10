package com.mmt.shubh.owsmtasks.splash;

import android.content.Intent;
import android.os.Bundle;

import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.home.HomeActivity;
import com.mmt.shubh.owsmtasks.base.BaseActivity;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView {

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
        //mPresenter.addSeedData();
        startHomeActivity();
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
