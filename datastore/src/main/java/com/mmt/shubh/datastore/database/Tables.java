package com.mmt.shubh.datastore.database;

import com.mmt.shubh.database.TableBuilder;

import timber.log.Timber;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/13/16,
 */
public class Tables {

    public static String createTaskBoardTable() {

        String s = new TableBuilder()
                .addTableName(TaskContract.TASK_BOARD_TABLE_NAME)
                .addColumn(TaskContract.TaskBoardColumn._ID, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_PRIMARY_KEY)
                .addColumn(TaskContract.TaskBoardColumn.TITLE, TableBuilder.FIELD_TYPE_TEXT, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskBoardColumn.DESCRIPTION, TableBuilder.FIELD_TYPE_TEXT)
                .addColumn(TaskContract.TaskBoardColumn.CREATED_DATE, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskBoardColumn.STATUS, TableBuilder.FIELD_TYPE_TEXT, TableBuilder.CONSTRAINT_NOT_NULL)
                .build();
        Timber.tag("createTaskBoardTable");
        return s;
    }

    public static String createTaskTable() {
        String s = new TableBuilder()
                .addTableName(TaskContract.TASK_TABLE_NAME)
                .addColumn(TaskContract.TaskColumn._ID, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_PRIMARY_KEY)
                .addColumn(TaskContract.TaskColumn.TITLE, TableBuilder.FIELD_TYPE_TEXT, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.DESCRIPTION, TableBuilder.FIELD_TYPE_TEXT)
                .addColumn(TaskContract.TaskColumn.START_DATE, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.CREATED_DATE, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.PROGRESS, TableBuilder.FIELD_TYPE_TEXT, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.COMPLETION_DATE, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.STATUS, TableBuilder.FIELD_TYPE_TEXT, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.TASK_BOARD_KEY, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.TaskColumn.TASK_TYPE, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addForgienKey(TaskContract.TaskColumn.TASK_BOARD_KEY, TaskContract.TASK_BOARD_TABLE_NAME, TaskContract.TaskBoardColumn._ID)
                .build();
        Timber.tag("createTaskBoardTable");
        return s;
    }

    public static String createNoteTable() {

        String s = new TableBuilder()
                .addTableName(TaskContract.NOTES_TABLE_NAME)
                .addColumn(TaskContract.NotesColumn._ID, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_PRIMARY_KEY)
                .addColumn(TaskContract.NotesColumn.TITLE, TableBuilder.FIELD_TYPE_TEXT, TableBuilder.CONSTRAINT_NOT_NULL)
                .addColumn(TaskContract.NotesColumn.KEY_TASK, TableBuilder.FIELD_TYPE_INTEGER, TableBuilder.CONSTRAINT_NOT_NULL)
                .addForgienKey(TaskContract.NotesColumn.KEY_TASK, TaskContract.TASK_TABLE_NAME, TaskContract.TaskColumn._ID)
                .build();
        Timber.tag("createTaskBoardTable");
        Timber.e(s);
        return s;
    }

}
