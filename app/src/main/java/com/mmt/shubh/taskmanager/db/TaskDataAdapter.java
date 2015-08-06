package com.mmt.shubh.taskmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mmt.shubh.taskmanager.TaskStatus;
import com.mmt.shubh.taskmanager.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subham Tyagi,
 * on 05/Aug/2015,
 * 1:27 PM
 * TODO:Add class comment.
 */
public class TaskDataAdapter {

    SQLiteDatabase mSQLiteDatabase;
    private Context mContext;
    private static TaskDataAdapter ourInstance;

    public static TaskDataAdapter getInstance(Context context) {
        synchronized (TaskDataAdapter.class) {
            if (ourInstance == null) {
                synchronized (TaskDataAdapter.class) {
                    ourInstance = new TaskDataAdapter(context);
                }
            }
        }
        return ourInstance;
    }

    private TaskDataAdapter(Context context) {
        mContext = context;
        TasksSQLiteOpenHelper helper = new TasksSQLiteOpenHelper(mContext);
        mSQLiteDatabase = helper.getWritableDatabase();

    }


    public List<Task> getTaskByStatus(String taskStatus) {
        List<Task> taskList = new ArrayList<>();
    /*    Cursor cursor = mSQLiteDatabase.query(TaskContract.TaskColumn.TASKS_TABLE, TaskContract.TASK_PROJECTION,
                TaskContract.TaskColumn.TASK_STATUS + "=?", new String[]{taskStatus}, null, null, null);*/
        Cursor cursor = mSQLiteDatabase.query(TaskContract.TaskColumn.TASKS_TABLE, TaskContract.TASK_PROJECTION,
                null, null, null, null, null);

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    taskList.add(restoreFromCursor(cursor));
                }
            } finally {
                cursor.close();
            }
        }
        return taskList;
    }

    private Task restoreFromCursor(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTitle(cursor.getString(1));
        task.setTaskStatus(TaskStatus.valueOf(cursor.getString(2)));
        task.setDescription(cursor.getString(3));
        task.setStartDate(cursor.getLong(4));
        task.setEndDate(cursor.getLong(5));
        return task;
    }

    private ContentValues toContentValues(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskContract.TaskColumn.TASK_TITLE, task.getTitle());
        contentValues.put(TaskContract.TaskColumn.TASK_DESCRIPTION, task.getDescription());
        contentValues.put(TaskContract.TaskColumn.TASK_STATUS, task.getTaskStatus().name());
        contentValues.put(TaskContract.TaskColumn.START_DATE, task.getStartDate());
        contentValues.put(TaskContract.TaskColumn.END_DATE, task.getEndDate());
        return contentValues;
    }

    public void addTaskListToDatabase(List<Task> tasks) {
        mSQLiteDatabase.beginTransaction();
        for (Task task : tasks) {
            mSQLiteDatabase.insert(TaskContract.TaskColumn.TASKS_TABLE, "foo", toContentValues(task));
        }
        mSQLiteDatabase.endTransaction();
    }

    public void addTask(Task task) {
        mSQLiteDatabase.insert(TaskContract.TaskColumn.TASKS_TABLE, "foo", toContentValues(task));
    }

    public int getPendingTaskCount() {
        Cursor cursor = mSQLiteDatabase.query(TaskContract.TaskColumn.TASKS_TABLE, TaskContract.TASK_PROJECTION,
                TaskContract.TaskColumn.TASK_STATUS + "=?", new String[]{TaskStatus.PENDING.name()}, null, null, null);
        if (cursor != null) {
            try {

                return cursor.getCount();
            } finally {
                cursor.close();
            }
        }
        return 0;
    }
}
