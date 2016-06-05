package com.mmt.shubh.owsmtasks.utility;

import com.mmt.shubh.datastore.model.Task;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
public class TaskFactory {
    private static TaskFactory ourInstance = new TaskFactory();

    private TaskFactory() {
    }

    public static TaskFactory getInstance() {
        return ourInstance;
    }

    public Task createNewTask() {
        return new Task();
    }
}
