package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/13/16,
 */
public class TaskBoard implements IModel<TaskBoard> {

    private long mId;

    private String mTaskTitle;

    private String mTaskDescription;

    private List<Task> mTasks;

    @Override
    public ContentValues toContentValue() {
        return null;
    }

    @Override
    public TaskBoard parseCursor(Cursor cursor) {
        return null;
    }

    public String getTitle() {
        return mTaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        mTaskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return mTaskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        mTaskDescription = taskDescription;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }
}
