package com.mmt.shubh.owsmtasks.ui.injection.component;

import com.mmt.shubh.owsmtasks.ui.fragments.TaskListDataView;
import com.mmt.shubh.owsmtasks.ui.injection.module.HomeActivityModule;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = HomeActivityModule.class)
public interface HomeActivityComponent {

    void inject(TaskListDataView taskListDataView);

}