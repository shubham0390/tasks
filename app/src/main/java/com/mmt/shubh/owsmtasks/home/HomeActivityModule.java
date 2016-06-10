package com.mmt.shubh.owsmtasks.home;

import android.app.Activity;
import android.content.Context;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.owsmtasks.dagger.PerActivity;

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
    public Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public HomePresenter provideTaskListPresenter(TaskBoardDataAdapter dataAdapter) {
        return new HomePresenter(dataAdapter);
    }


}