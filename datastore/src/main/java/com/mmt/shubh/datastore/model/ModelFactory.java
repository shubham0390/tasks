package com.mmt.shubh.datastore.model;

/**
 * Created by subhamtyagi on 1/26/16.
 */
public class ModelFactory {

    public static Task getNewTask() {
        return new Task();
    }

    public static TaskBoard getNewTaskBoard() {
        return new TaskBoard();
    }
}
