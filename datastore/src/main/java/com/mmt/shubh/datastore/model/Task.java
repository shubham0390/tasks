package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.mmt.shubh.datastore.database.TaskContract;

/**
 * Created by shubham on 12/23/15.
 */
public class Task implements IModel<Task> {

    private long mId;

    private String mTitle;

    private String mDescription;

    private long mStartDate;
    private long mCreationDate;

    private long mCompletionDate;

    private String mProgress;

    private TaskStatus mTaskStatus;

    public enum TaskStatus {
        NEW,
        INPROGRESS,
        PENDING,
        COMPLETED
    }

    @Override
    public ContentValues toContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskContract.TaskColumn.TITLE, mTitle);
        contentValues.put(TaskContract.TaskColumn.DESCRIPTION, mDescription);
        contentValues.put(TaskContract.TaskColumn.START_DATE, mStartDate);
        contentValues.put(TaskContract.TaskColumn.COMPLETION_DATE, mCompletionDate);
        contentValues.put(TaskContract.TaskColumn.STATUS, mTaskStatus.name());
        contentValues.put(TaskContract.TaskColumn.CREATED_DATE, mCreationDate);
        contentValues.put(TaskContract.TaskColumn.PROGRESS, mProgress);
        return contentValues;
    }

    @Override
    public Task parseCursor(Cursor cursor) {
        Task task = new Task();
        mId = cursor.getLong(0);
        mTitle = cursor.getString(1);
        mStartDate = cursor.getLong(2);
        mCreationDate = cursor.getLong(3);
        mProgress = cursor.getString(4);
        mDescription = cursor.getString(5);
        mCompletionDate = cursor.getLong(6);
        mTaskStatus = TaskStatus.valueOf(cursor.getString(7));
        return task;
    }


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getStartDate() {
        return mStartDate;
    }

    public void setStartDate(long startDate) {
        mStartDate = startDate;
    }

    public long getCompletionDate() {
        return mCompletionDate;
    }

    public void setCompletionDate(long completionDate) {
        mCompletionDate = completionDate;
    }

    public TaskStatus getTaskStatus() {
        return mTaskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        mTaskStatus = taskStatus;
    }

    public long getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(long creationDate) {
        mCreationDate = creationDate;
    }

    public String getProgress() {
        return mProgress;
    }

    public void setProgress(String progress) {
        mProgress = progress;
    }
}
