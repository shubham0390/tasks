package com.mmt.shubh.owsmtasks.ui.injection.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.JsonParser;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shubham on 1/6/16.
 */
@Module
public class SplashActivityModule {

    @Provides
    @PerActivity
    public JsonParser provideJsonParser(TaskDataAdapter adapter, Context context, TaskBoardDataAdapter boardDataAdapter) {
        return new JsonParser(adapter, context, boardDataAdapter);
    }

    @Provides
    @PerActivity
    public SplashPresenter provideSplashPresenter(SharedPreferences sharedPreferences, JsonParser jsonParser) {
        return new SplashPresenter(sharedPreferences, jsonParser);
    }


}
