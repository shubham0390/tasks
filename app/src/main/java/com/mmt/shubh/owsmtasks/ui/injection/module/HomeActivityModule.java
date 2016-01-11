package com.mmt.shubh.owsmtasks.ui.injection.module;

import android.app.Activity;
import android.content.Context;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.presenter.TaskListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private Activity mActivity;

    public HomeActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    TaskListPresenter provideTaskListPresenter(TaskDataAdapter adapter) {
        return new TaskListPresenter(adapter);
    }



}