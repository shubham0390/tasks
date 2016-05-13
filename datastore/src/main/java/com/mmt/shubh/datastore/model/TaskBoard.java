package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.mmt.shubh.datastore.database.TaskContract;

import java.util.List;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/13/16,
 */
public class TaskBoard implements IModel<TaskBoard> {

    private long mId;

    private String mTitle;

    private String mDescription;

    private long mCreateDate;
    private int mStatus;
    private List<Task> mTasks;

    @Override
    public ContentValues toContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskContract.TaskBoardColumn.CREATED_DATE, mCreateDate);
        contentValues.put(TaskContract.TaskBoardColumn.TITLE, mTitle);
        contentValues.put(TaskContract.TaskBoardColumn.DESCRIPTION, mDescription);
        contentValues.put(TaskContract.TaskBoardColumn.STATUS, mStatus);
        return contentValues;
    }

    @Override
    public TaskBoard parseCursor(Cursor cursor) {
        mId = cursor.getLong(cursor.getColumnIndex(TaskContract.TaskBoardColumn._ID));
        mCreateDate = cursor.getLong(cursor.getColumnIndex(TaskContract.TaskBoardColumn.CREATED_DATE));
        mTitle = cursor.getString(cursor.getColumnIndex(TaskContract.TaskBoardColumn.TITLE));
        mDescription = cursor.getString(cursor.getColumnIndex(TaskContract.TaskBoardColumn.DESCRIPTION));
        mStatus = cursor.getInt(cursor.getColumnIndex(TaskContract.TaskBoardColumn.STATUS));
        return null;
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

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(long createDate) {
        mCreateDate = createDate;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
