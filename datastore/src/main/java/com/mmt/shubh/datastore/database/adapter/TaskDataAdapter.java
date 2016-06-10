package com.mmt.shubh.datastore.database.adapter;


import android.database.Cursor;

import com.mmt.shubh.database.QueryBuilder;
import com.mmt.shubh.database.Selection;
import com.mmt.shubh.datastore.database.TaskContract;
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

    public TaskDataAdapter(BriteDatabase database) {
        super(database, TaskContract.TASK_TABLE_NAME);
    }

    public Observable<List<Task>> getTaskByStatus(String taskStatus) {
        QueryBuilder queryBuilder = new QueryBuilder();

        queryBuilder.addFrom(TABLE_NAME)
                .addProjection(TaskContract.TASK_PROJECTION)
                .addSelection(
                        new Selection(TaskContract.TaskColumn.STATUS, Selection.EQUAL, taskStatus)
                );

        return mDatabase.createQuery(TABLE_NAME, queryBuilder.build()).mapToList(this::parseCursor);

    }

    @Override
    protected Task parseCursor(Cursor cursor) {
        Task task = ModelFactory.getNewTask();
        task.parseCursor(cursor);
        return task;
    }

    public Observable<List<Task>> getTaskByTaskboardId(long taskboardId) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addFrom(TABLE_NAME)
                .addProjection(TaskContract.TASK_PROJECTION)
                .addSelection(
                        new Selection(TaskContract.TaskColumn.TASK_BOARD_KEY, Selection.EQUAL, String.valueOf(taskboardId))
                );

        return mDatabase.createQuery(TABLE_NAME, queryBuilder.build()).mapToList(this::parseCursor);
    }
}
