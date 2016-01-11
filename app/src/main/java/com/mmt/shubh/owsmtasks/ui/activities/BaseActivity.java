package com.mmt.shubh.owsmtasks.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mmt.shubh.owsmtasks.TaskApplication;
import com.mmt.shubh.owsmtasks.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.owsmtasks.ui.mvpviews.MvpView;
import com.mmt.shubh.owsmtasks.ui.presenter.Presenter;

import javax.inject.Inject;


public abstract class BaseActivity<V extends MvpView, P extends Presenter<V>> extends AppCompatActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency(TaskApplication.get(getApplicationContext()).getComponent());
        mPresenter.attachView((V) this);
    }

    protected abstract void injectDependency(ApplicationComponent component);
}