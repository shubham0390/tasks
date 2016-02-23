package com.mmt.shubh.owsmtasks.ui.injection.module;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.presenter.TaskListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 1/30/16.
 */
@Module
public class TaskListModule {

    @PerActivity
    @Provides
    public TaskListPresenter provideTaskListPresenter(TaskDataAdapter taskDataAdapter){
        return new TaskListPresenter(taskDataAdapter);
    }
}
