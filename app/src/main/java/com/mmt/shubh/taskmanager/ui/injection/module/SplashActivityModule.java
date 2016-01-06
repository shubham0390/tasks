package com.mmt.shubh.taskmanager.ui.injection.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.mmt.shubh.datastore.adapter.TaskDataAdapter;
import com.mmt.shubh.taskmanager.JsonParser;
import com.mmt.shubh.taskmanager.ui.injection.PerActivity;
import com.mmt.shubh.taskmanager.ui.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shubham on 1/6/16.
 */
@Module
public class SplashActivityModule {

    @Provides
    @PerActivity
    public JsonParser provideJsonParser(TaskDataAdapter adapter, Context context) {
        return new JsonParser(adapter, context);
    }

    @Provides
    @PerActivity
    public SplashPresenter provideSplashPresenter(SharedPreferences sharedPreferences,JsonParser jsonParser) {
        return new SplashPresenter(sharedPreferences, jsonParser);
    }


}
