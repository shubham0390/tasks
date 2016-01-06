package com.mmt.shubh.taskmanager.ui.injection.component;

import com.mmt.shubh.taskmanager.ui.fragments.TaskListFragment;
import com.mmt.shubh.taskmanager.ui.injection.ActivityContext;
import com.mmt.shubh.taskmanager.ui.injection.module.HomeActivityModule;
import com.mmt.shubh.taskmanager.ui.injection.PerActivity;
import com.mmt.shubh.taskmanager.ui.presenter.TaskListPresenter;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = HomeActivityModule.class)
public interface HomeActivityComponent {

    void inject(TaskListFragment taskListFragment);

}