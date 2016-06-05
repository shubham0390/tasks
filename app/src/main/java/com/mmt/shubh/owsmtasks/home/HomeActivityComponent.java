package com.mmt.shubh.owsmtasks.home;

import com.mmt.shubh.owsmtasks.task.list.TaskListDataView;
import com.mmt.shubh.owsmtasks.dagger.PerActivity;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;
import com.mmt.shubh.owsmtasks.task.list.TaskListModule;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                HomeActivityModule.class,
                TaskListModule.class
        }
)
public interface HomeActivityComponent {

    void inject(HomeActivity taskListDataView);
    void inject(TaskListDataView taskListDataView);

}