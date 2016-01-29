package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mmt.shubh.datastore.TaskboardDeserializer;
import com.mmt.shubh.datastore.database.TaskContract;

import java.util.List;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/13/16,
 */
@JsonDeserialize(using = TaskboardDeserializer.class)
public class TaskBoard implements IModel {

    private long mId;

    private String mTitle;

    private String mDescription;

    private String mProgress;

    private String mStatus;

    private long mCreateDate;
    private long mStartDate;

    private List<Task> mTasks;

    @Override
    public ContentValues toContentValue() {
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskBoardColumn.TITLE, mTitle);
        values.put(TaskContract.TaskBoardColumn.DESCRIPTION, mDescription);
        values.put(TaskContract.TaskBoardColumn.PROGRESS, mProgress);
        values.put(TaskContract.TaskBoardColumn.CREATED_DATE, mCreateDate);
        values.put(TaskContract.TaskBoardColumn.START_DATE, mStartDate);
        values.put(TaskContract.TaskBoardColumn.STATUS, mStatus);
        return values;
    }

    @Override
    public void parseCursor(Cursor cursor) {

        mId = cursor.getLong(0);
        mTitle = cursor.getString(1);
        mDescription = cursor.getString(2);
        mProgress = cursor.getString(3);
        mStatus = cursor.getString(4);
        mCreateDate = cursor.getLong(5);
        mStartDate = cursor.getLong(6);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String taskTitle) {
        mTitle = taskTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String taskDescription) {
        mDescription = taskDescription;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getProgress() {
        return mProgress;
    }

    public void setProgress(String progress) {
        mProgress = progress;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public long getCreateDate() {
        return mCreateDate;
    }
    public void setCreateDate(long createDate) {
        mCreateDate = createDate;
    }

    public long getStartDate() {
        return mStartDate;
    }

    public void setStartDate(long startDate) {
        mStartDate = startDate;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }
}
