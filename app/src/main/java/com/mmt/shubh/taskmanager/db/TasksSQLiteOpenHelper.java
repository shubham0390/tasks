package com.mmt.shubh.taskmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mmt.shubh.taskmanager.db.TaskContract;

public class TasksSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "tasks_db.sqllite";
    public static final int VERSION = 1;


    public TasksSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    private void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TaskContract.TaskColumn.TASKS_TABLE + " ( " +
                        TaskContract.TaskColumn._ID + " integer primary key autoincrement not null, " +
                        TaskContract.TaskColumn.TASK_TITLE + " text, " +
                        TaskContract.TaskColumn.TASK_STATUS + " text, " +
                        TaskContract.TaskColumn.TASK_DESCRIPTION + " text, " +
                        TaskContract.TaskColumn.START_DATE + " integer, " +
                        TaskContract.TaskColumn.END_DATE + " integer " +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}