package com.mmt.shubh.owsmtasks.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.ui.Home.HomeActivity;
import com.mmt.shubh.owsmtasks.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.owsmtasks.ui.injection.component.DaggerSplashActivityComponent;
import com.mmt.shubh.owsmtasks.ui.injection.component.SplashActivityComponent;
import com.mmt.shubh.owsmtasks.ui.injection.module.SplashActivityModule;
import com.mmt.shubh.owsmtasks.ui.mvpviews.SplashView;
import com.mmt.shubh.owsmtasks.ui.presenter.SplashPresenter;

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
