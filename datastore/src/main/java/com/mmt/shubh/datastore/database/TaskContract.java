package com.mmt.shubh.datastore.database;

import android.provider.BaseColumns;

/**
 * Created by shubham on 12/19/15.
 */
public interface TaskContract {
    String TASK_TABLE_NAME = "tasks";
    String NOTES_TABLE_NAME = "notes";

    String[] TASK_PROJECTION = {
            TaskColumn._ID,
            TaskColumn.TITLE,
            TaskColumn.DESCRIPTION,
            TaskColumn.START_DATE,
            TaskColumn.CREATED_DATE,
            TaskColumn.PROGRESS,
            TaskColumn.COMPLETION_DATE,
            TaskColumn.STATUS
    };

    interface TaskColumn extends BaseColumns {
        String TITLE = "title";
        String DESCRIPTION = "description";
        String CREATED_DATE = "created_date";
        String START_DATE = "start_date";
        String COMPLETION_DATE = "complication_date";
        String STATUS = "status";
        String PROGRESS = "progress";
    }

    String CREATE_TASKS =
            "CREATE TABLE " + TaskContract.TASK_TABLE_NAME + " (" +
                    TaskContract.TaskColumn._ID + " INTEGER PRIMARY KEY, " +
                    TaskContract.TaskColumn.TITLE + " TEXT NOT NULL, " +
                    TaskContract.TaskColumn.DESCRIPTION + " TEXT, " +
                    TaskContract.TaskColumn.START_DATE + " TEXT NOT NULL, " +
                    TaskContract.TaskColumn.CREATED_DATE + " INTEGER NOT NULL, " +
                    TaskContract.TaskColumn.PROGRESS + " TEXT , " +
                    TaskContract.TaskColumn.COMPLETION_DATE + " TEXT, " +
                    TaskContract.TaskColumn.STATUS + " TEXT" +
                    " ); ";

    interface NotesColumn extends BaseColumns {
        String DESCRIPTION = "description";
        String KEY_TASK = "task_id";
    }

    String CREATE_NOTES =
            "CREATE TABLE " + TaskContract.NOTES_TABLE_NAME + " (" +
                    TaskContract.TaskColumn._ID + " INTEGER PRIMARY KEY, " +
                    NotesColumn.DESCRIPTION + " TEXT NOT NULL, " +
                    NotesColumn.KEY_TASK + " INTEGER " +
                    " ); ";
}
