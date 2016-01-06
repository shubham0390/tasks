package com.mmt.shubh.taskmanager.ui.injection.component;

import com.mmt.shubh.taskmanager.ui.activities.SplashActivity;
import com.mmt.shubh.taskmanager.ui.injection.PerActivity;
import com.mmt.shubh.taskmanager.ui.injection.module.SplashActivityModule;

import dagger.Component;

/**
 * Created by shubham on 1/6/16.
 */
@Component(dependencies = ApplicationComponent.class
        , modules = SplashActivityModule.class)
@PerActivity
public interface SplashActivityComponent {

    void inject(SplashActivity splashActivity);
}
