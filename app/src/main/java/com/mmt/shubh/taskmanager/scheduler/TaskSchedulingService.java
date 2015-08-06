package com.mmt.shubh.taskmanager.scheduler;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.mmt.shubh.taskmanager.TaskNotification;
import com.mmt.shubh.taskmanager.db.TaskDataAdapter;


public class TaskSchedulingService extends IntentService {
    public TaskSchedulingService() {
        super("SchedulingService");
    }

    public static final String TAG = "Scheduling Demo";

    @Override
    protected void onHandleIntent(Intent intent) {
        TaskDataAdapter taskDataAdapter = TaskDataAdapter.getInstance(getApplicationContext());
        int pendingTaskCount = taskDataAdapter.getPendingTaskCount();
        if (pendingTaskCount > 0) {
            TaskNotification.notify(getApplicationContext(), pendingTaskCount);
        } else {
            Log.i(TAG, "No Pending tasks");
        }
    }
}
