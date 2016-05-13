package com.mmt.shubh.datastore.database.adapter;

import android.database.Cursor;

import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.squareup.sqlbrite.BriteDatabase;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 4/22/16,
 */
public class TaskBoardDataAdapter extends AbstractDataAdapter<TaskBoard> {

    public TaskBoardDataAdapter(BriteDatabase database) {
        super(database, TaskContract.TASK_BOARD_TABLE_NAME);
    }

    @Override
    protected TaskBoard parseCursor(Cursor cursor) {
        return new TaskBoard().parseCursor(cursor);
    }

}
