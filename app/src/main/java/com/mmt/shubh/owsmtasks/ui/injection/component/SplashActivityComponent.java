package com.mmt.shubh.owsmtasks.ui.injection.component;

import com.mmt.shubh.owsmtasks.ui.activities.SplashActivity;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.injection.module.SplashActivityModule;

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
