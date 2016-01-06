package com.mmt.shubh.taskmanager.ui.injection.module;

import android.app.Activity;
import android.content.Context;

import com.mmt.shubh.datastore.adapter.TaskDataAdapter;
import com.mmt.shubh.taskmanager.ui.injection.PerActivity;
import com.mmt.shubh.taskmanager.ui.presenter.TaskListPresenter;
import com.squareup.sqlbrite.BriteDatabase;

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