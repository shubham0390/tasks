package com.mmt.shubh.datastore.database;

import android.provider.BaseColumns;

/**
 * Created by shubham on 12/19/15.
 */
public interface TaskContract {
    String TASK_BOARD_TABLE_NAME = "tasks_board";
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
            TaskColumn.STATUS,
            TaskColumn.TASK_BOARD_KEY
    };

    interface TaskBoardColumn extends BaseColumns {
        String TITLE = "title";
        String DESCRIPTION = "description";
        String CREATED_DATE = "created_date";
        String STATUS = "status";
    }

    interface TaskColumn extends BaseColumns {
        String TITLE = "title";
        String DESCRIPTION = "description";
        String CREATED_DATE = "created_date";
        String START_DATE = "start_date";
        String COMPLETION_DATE = "complication_date";
        String STATUS = "status";
        String PROGRESS = "progress";
        String TASK_BOARD_KEY = "taskboard_key";
        String TASK_TYPE = "task_type";
    }

    interface NotesColumn extends BaseColumns {
        String TITLE = "description";
        String KEY_TASK = "task_id";
    }
}
