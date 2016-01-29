package com.mmt.shubh.datastore.database.adapter;


import android.database.Cursor;

import com.mmt.shubh.database.QueryBuilder;
import com.mmt.shubh.database.Selection;
import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.firebase.FirebaseDataAdapter;
import com.mmt.shubh.datastore.model.ModelFactory;
import com.mmt.shubh.datastore.model.Task;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 12/23/15,
 */
public class TaskDataAdapter extends AbstractDataAdapter<Task> {

    FirebaseDataAdapter mFirebaseDataAdapter;

    public TaskDataAdapter(BriteDatabase database, FirebaseDataAdapter firebaseDataAdapter) {
        super(database, TaskContract.TASK_TABLE_NAME);
        this.mFirebaseDataAdapter = firebaseDataAdapter;
    }

    public Observable<List<Task>> getTaskByStatus(String taskStatus) {

        String selection = Selection.getSelection(TaskContract.TaskColumn.STATUS, Selection.EQUAL, taskStatus);

        String query = QueryBuilder.createSelectQuery(TABLE_NAME, TaskContract.TASK_PROJECTION, new String[]{selection});

        return mDatabase.createQuery(TABLE_NAME, query).mapToList(this::parseCursor);

    }

    @Override
    protected Task parseCursor(Cursor cursor) {
        Task task = ModelFactory.getNewTask();
        task.parseCursor(cursor);
        return task;
    }
}
