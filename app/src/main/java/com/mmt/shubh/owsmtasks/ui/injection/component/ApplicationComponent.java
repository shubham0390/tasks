package com.mmt.shubh.owsmtasks.ui.injection.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.module.ApplicationModule;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    Application application();

    BriteDatabase database();

    SharedPreferences sharedPreferences();

    TaskDataAdapter taskDataAdapter();

    TaskBoardDataAdapter taskBoardDataAdapter();

}