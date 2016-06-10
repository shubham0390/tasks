package com.mmt.shubh.datastore.database.adapter;

import android.database.Cursor;

import com.mmt.shubh.datastore.model.ModelFactory;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.squareup.sqlbrite.BriteDatabase;


public class TaskBoardDataAdapter extends AbstractDataAdapter<TaskBoard> {

    public TaskBoardDataAdapter(BriteDatabase database, String tableName) {
        super(database, tableName);
    }

    @Override
    protected TaskBoard parseCursor(Cursor cursor) {
        TaskBoard taskBoard = ModelFactory.getNewTaskBoard();
        taskBoard.parseCursor(cursor);
        return taskBoard;
    }
}
