package com.mmt.shubh.owsmtasks.task.list;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.dagger.PerActivity;

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
