package com.mmt.shubh.datastore.firebase;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.mmt.shubh.datastore.model.Task;

import timber.log.Timber;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
public class FirebaseDataAdapter {
    private static final String BASE_URL = "https://owsmtask.firebaseio.com";
    private static final String USER_URL = "/users";
    private static final String TASK_URL = "/data/tasks";
    private static final String NOTE_URL = "/data/tasks/note";

    private Firebase mFirebase;
    private String userEmailId;

    public FirebaseDataAdapter(String userEmailId) {
        this.userEmailId = userEmailId;
        mFirebase = new Firebase(BASE_URL);
        Timber.tag(getClass().getName());
    }

    public void addTask(Task task) {
        mFirebase.child(USER_URL).push().setValue("shubham");
        mFirebase.child(USER_URL).child(TASK_URL);
        mFirebase.push().setValue(task, (firebaseError, firebase) -> Timber.i("Add value with code %d", firebaseError.getCode()));
        Timber.i("Data added to firebase successfully");
    }
}
