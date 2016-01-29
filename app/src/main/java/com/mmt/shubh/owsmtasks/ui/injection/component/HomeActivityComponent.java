package com.mmt.shubh.owsmtasks.ui.injection.component;

import com.mmt.shubh.owsmtasks.ui.activities.HomeActivity;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.injection.module.HomeActivityModule;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = HomeActivityModule.class)
public interface HomeActivityComponent {

    void inject(HomeActivity taskListDataView);

}