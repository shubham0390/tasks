package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shubham on 12/23/15.
 */
public class Task implements IModel<Task> {

    private String mTitle;

    private String mDescription;

    private long mId;

    private long mStartDate;

    private long mEndDate;

    private TaskStatus mTaskStatus;

    public enum TaskStatus {
        NEW,
        INPROGRESS,
        PENDING,
        COMPLETED
    }

    @Override
    public ContentValues toContentValue(Task task) {
        return null;
    }

    @Override
    public Task parseCursor(Cursor cursor) {
        Task task = new Task();
        mId = cursor.getLong(0);
        mTitle = cursor.getString(1);
        mTaskStatus = TaskStatus.valueOf(cursor.getString(2));
        mDescription = cursor.getString(3);
        mStartDate = cursor.getLong(4);
        mEndDate = cursor.getLong(5);
        return task;
    }



}
