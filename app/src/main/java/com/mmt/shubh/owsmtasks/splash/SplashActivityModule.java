package com.mmt.shubh.owsmtasks.splash;

import android.content.Context;
import android.content.SharedPreferences;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskboardDataAdapter;
import com.mmt.shubh.owsmtasks.utility.JsonParser;
import com.mmt.shubh.owsmtasks.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shubham on 1/6/16.
 */
@Module
public class SplashActivityModule {

    @Provides
    @PerActivity
    public JsonParser provideJsonParser(TaskDataAdapter adapter, Context context, TaskboardDataAdapter dataAdapter) {
        return new JsonParser(adapter, context, dataAdapter);
    }

    @Provides
    @PerActivity
    public SplashPresenter provideSplashPresenter(SharedPreferences sharedPreferences, JsonParser jsonParser) {
        return new SplashPresenter(sharedPreferences, jsonParser);
    }


}
