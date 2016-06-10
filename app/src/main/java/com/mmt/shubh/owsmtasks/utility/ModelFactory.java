package com.mmt.shubh.owsmtasks.utility;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.datastore.model.TaskBoard;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
public class ModelFactory {


    public static Task createNewTask() {
        return new Task();
    }

    public static TaskBoard createTaskBoard() {
        return new TaskBoard();
    }
}
