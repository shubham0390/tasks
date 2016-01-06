package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shubham on 12/23/15.
 */
public class Task implements IModel<Task> {

    private long mId;

    private String mTitle;

    private String mDescription;

    private long mStartDate;

    private long mCompletionDate;

    private TaskStatus mTaskStatus;

    public enum TaskStatus {
        NEW,
        INPROGRESS,
        PENDING,
        COMPLETED
    }

    @Override
    public ContentValues toContentValue() {
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
        mCompletionDate = cursor.getLong(5);
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
}
