package com.mmt.shubh.datastore.adapter;

import android.content.Context;
import android.database.Cursor;

import com.mmt.shubh.datastore.database.DatabaseOpenHelper;
import com.mmt.shubh.datastore.database.QueryBuilder;
import com.mmt.shubh.datastore.database.Selection;
import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.model.Task;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by shubham on 12/23/15.
 */
public class TaskDataAdapter extends AbstractDataAdapter<Task> {

    BriteDatabase mDatabase;

    public TaskDataAdapter(Context context) {
        super(context);
        mDatabase = SqlBrite.create().wrapDatabaseHelper(new DatabaseOpenHelper(context));
    }

    public Observable<List<Object>> getTaskByStatus(String taskStatus) {

        String selection = Selection.getSelection(TaskContract.TaskColumn.STATUS, Selection.EQUAL, taskStatus);

        String query = QueryBuilder.createSelectQuery(TaskContract.TASK_TABLE_NAME, TaskContract.TASK_PROJECTION, new String[]{selection});

        return mDatabase.createQuery(TaskContract.TASK_TABLE_NAME, query).mapToList((Func1<Cursor, Object>)
                cursor -> new Task().parseCursor(cursor));

    }
}
