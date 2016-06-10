package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.mmt.shubh.datastore.database.TaskContract;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Task implements IModel {

    private long mId;

    private String mTitle;

    private String mDescription;

    private long mStartDate;

    private long mCreationDate;

    private long mCompletionDate;

    private String mProgress;

    private TaskStatus mTaskStatus;

    private long mTaskBorardKey;

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
        contentValues.put(TaskContract.TaskColumn.TASK_BOARD_KEY, mTaskBorardKey);
        return contentValues;
    }

    @Override
    public void parseCursor(Cursor cursor) {
        Task task = new Task();
        mId = cursor.getLong(0);
        mTitle = cursor.getString(1);
        mStartDate = cursor.getLong(2);
        mCreationDate = cursor.getLong(3);
        mProgress = cursor.getString(4);
        mDescription = cursor.getString(5);
        mCompletionDate = cursor.getLong(6);
        mTaskStatus = TaskStatus.valueOf(cursor.getString(7));
        mTaskBorardKey = cursor.getLong(8);
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

    public long getCompletion() {
        return mCompletionDate;
    }

    public void setCompletion(long completionDate) {
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


    public String getProgress() {
        return mProgress;
    }

    public void setProgress(String progress) {
        mProgress = progress;
    }

    public void setCreationDate(long creationDate) {
        mCreationDate = creationDate;
    }

    public long getTaskBorardKey() {
        return mTaskBorardKey;
    }

    public void setTaskBorardKey(long taskBorardKey) {
        mTaskBorardKey = taskBorardKey;
    }

    public enum TaskStatus {
        NEW,
        INPROGRESS,
        PENDING,
        COMPLETE,
        OBSULOTE
    }
}
