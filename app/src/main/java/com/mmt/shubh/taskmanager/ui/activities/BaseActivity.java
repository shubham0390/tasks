package com.mmt.shubh.taskmanager.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mmt.shubh.taskmanager.TaskApplication;
import com.mmt.shubh.taskmanager.ui.injection.component.ApplicationComponent;

import javax.inject.Inject;


public abstract class BaseActivity<P> extends AppCompatActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency(TaskApplication.get(getApplicationContext()).getComponent());
    }

    protected abstract void injectDependency(ApplicationComponent component);
}