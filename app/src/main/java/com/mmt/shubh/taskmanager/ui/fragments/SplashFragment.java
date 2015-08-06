package com.mmt.shubh.taskmanager.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.mmt.shubh.taskmanager.JsonParser;
import com.mmt.shubh.taskmanager.scheduler.TaskAlarmReceiver;
import com.mmt.shubh.taskmanager.ui.activities.HomeActivity;

public class SplashFragment extends Fragment {

    public static final String PREFS_NAME = "TaskPrefsFile";
    public static final String KEY_SCHEDULER_STARTED = "schedulerStarted";
    public static final String KEY_SEED_DATA_ADDED = "seedDataAdded";

    SharedPreferences mSharedPreferences;
    private AsyncTask<Void, String, Void> task;


    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mSharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, 0);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startScheduler();
        addSeedData();
    }

    private void addSeedData() {
        boolean seedDataStatus = mSharedPreferences.getBoolean(KEY_SEED_DATA_ADDED, false);
        if (seedDataStatus) {
            task = new UpdateAsyncTask();
            task.execute();
            Log.i("Seed Data", "Adding seed ata");
        } else {
            Log.i("Seed Data", "Seed Data already added");
        }
    }

    private void startScheduler() {
        boolean schedulerStatus = mSharedPreferences.getBoolean(KEY_SCHEDULER_STARTED, false);
        if (!schedulerStatus) {
            TaskAlarmReceiver taskAlarmReceiver = new TaskAlarmReceiver();
            taskAlarmReceiver.setAlarm(getActivity().getApplicationContext());
            Log.i("Task Alarm", "Alarm set successful");
            mSharedPreferences.edit().putBoolean(KEY_SCHEDULER_STARTED, true).apply();
        } else {
            Log.i("Task Alarm", "Alarm already set");
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        if (task != null) {
            task.cancel(true);
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            JsonParser jsonParser = new JsonParser();
            jsonParser.seedData(getActivity().getApplicationContext());
            mSharedPreferences.edit().putBoolean(KEY_SEED_DATA_ADDED, true).apply();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getActivity().finish();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    }

}