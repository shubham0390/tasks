package com.mmt.shubh.owsmtasks.splash;

import com.mmt.shubh.owsmtasks.dagger.PerActivity;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;

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
