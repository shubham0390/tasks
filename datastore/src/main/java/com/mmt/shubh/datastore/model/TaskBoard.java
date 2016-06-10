package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.mmt.shubh.datastore.database.TaskContract;

import org.parceler.Parcel;

import java.util.List;

@Parcel(Parcel.Serialization.BEAN)
public class TaskBoard implements IModel {

    private long mId;

    private String mTitle;

    private String mDescription;

    private String mStatus;

    private long mCreateDate;

    private List<Task> mTasks;

    public TaskBoard() {
    }

    public TaskBoard(String taskBardTitle, String taskBoardDescription) {
        mTitle = taskBardTitle;
        mDescription = taskBoardDescription;
        mCreateDate = System.currentTimeMillis();
        mStatus = Status.NEW.name();
    }

    @Override
    public ContentValues toContentValue() {
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskBoardColumn.TITLE, mTitle);
        values.put(TaskContract.TaskBoardColumn.DESCRIPTION, mDescription);
        values.put(TaskContract.TaskBoardColumn.CREATED_DATE, mCreateDate);
        values.put(TaskContract.TaskBoardColumn.STATUS, mStatus);
        return values;
    }

    @Override
    public void parseCursor(Cursor cursor) {
        mId = cursor.getLong(0);
        mTitle = cursor.getString(1);
        mDescription = cursor.getString(2);
        mStatus = cursor.getString(3);
        mCreateDate = cursor.getLong(4);
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

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }

    public enum Status {
        NEW, COMPLETED, INPROGRESS
    }
}
