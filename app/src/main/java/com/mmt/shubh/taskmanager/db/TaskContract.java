package com.mmt.shubh.taskmanager.db;

import android.provider.BaseColumns;

/**
 * Created by Subham Tyagi,
 * on 04/Aug/2015,
 * 5:31 PM
 * TODO:Add class comment.
 */
public class TaskContract {


    public static String[] TASK_PROJECTION = {
            TaskColumn._ID,
            TaskColumn.TASK_TITLE,
            TaskColumn.TASK_STATUS,
            TaskColumn.TASK_DESCRIPTION,
            TaskColumn.START_DATE,
            TaskColumn.END_DATE

    };

    public interface TaskColumn extends BaseColumns {
        String TASKS_TABLE = "tasks";

        String TASK_TITLE = "task_title";
        String TASK_DESCRIPTION = "task_description";
        String TASK_STATUS = "task_status";
        String START_DATE = "task_start_time";
        String END_DATE = "task_end_time";
    }
}
