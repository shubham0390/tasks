package com.mmt.shubh.owsmtasks.ui.Home;

import android.app.Activity;
import android.content.Context;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private Activity mActivity;

    public HomeModule(Activity activity) {
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
    HomePresenter provideTaskListPresenter(TaskBoardDataAdapter adapter) {
        return new HomePresenter(adapter);
    }


}