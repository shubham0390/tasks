package com.mmt.shubh.owsmtasks.ui.injection.component;

import com.mmt.shubh.owsmtasks.ui.activities.HomeActivity;
import com.mmt.shubh.owsmtasks.ui.fragments.TaskListDataView;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.injection.module.HomeActivityModule;
import com.mmt.shubh.owsmtasks.ui.injection.module.TaskListModule;
import com.mmt.shubh.owsmtasks.ui.mvpviews.TaskListView;

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