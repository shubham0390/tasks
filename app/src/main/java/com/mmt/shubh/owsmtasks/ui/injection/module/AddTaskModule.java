package com.mmt.shubh.owsmtasks.ui.injection.module;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.presenter.AddTaskPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
@Module
public class AddTaskModule {

    @Provides
    @PerActivity
    AddTaskPresenter provideAddTaskPresenter(TaskDataAdapter taskDataAdapter) {
        return new AddTaskPresenter(taskDataAdapter);
    }

}
