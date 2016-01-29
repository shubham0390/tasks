package com.mmt.shubh.datastore.database.adapter;

import android.database.Cursor;

import com.mmt.shubh.datastore.model.ModelFactory;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.squareup.sqlbrite.BriteDatabase;

/**
 * Created by subhamtyagi on 1/26/16.
 */
public class TaskboardDataAdapter extends AbstractDataAdapter<TaskBoard> {

    public TaskboardDataAdapter(BriteDatabase database, String tableName) {
        super(database, tableName);
    }

    @Override
    protected TaskBoard parseCursor(Cursor cursor) {
        TaskBoard taskBoard = ModelFactory.getNewTaskBoard();
        taskBoard.parseCursor(cursor);
        return taskBoard;
    }
}
