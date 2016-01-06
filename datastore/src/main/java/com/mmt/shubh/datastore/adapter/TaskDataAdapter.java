package com.mmt.shubh.datastore.adapter;


import android.database.Cursor;

import com.mmt.shubh.datastore.database.QueryBuilder;
import com.mmt.shubh.datastore.database.Selection;
import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.model.Task;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by shubham on 12/23/15.
 */
public class TaskDataAdapter extends AbstractDataAdapter<Task> {


    public TaskDataAdapter(BriteDatabase database) {
        super(database, TaskContract.TASK_TABLE_NAME);
    }

    public Observable<List<Task>> getTaskByStatus(String taskStatus) {

        String selection = Selection.getSelection(TaskContract.TaskColumn.STATUS, Selection.EQUAL, taskStatus);

        String query = QueryBuilder.createSelectQuery(TABLE_NAME, TaskContract.TASK_PROJECTION, new String[]{selection});

        return mDatabase.createQuery(TABLE_NAME, query).mapToList(cursor -> new Task().parseCursor(cursor));

    }

    public Observable<Task> addTaskListToDatabase(List<Task> taskList) {
        return Observable.create((Observable.OnSubscribe<Task>) subscriber -> {
            BriteDatabase.Transaction transaction = mDatabase.newTransaction();
            try {
                for (Task task : taskList) {
                    long result = mDatabase.insert(TABLE_NAME, task.toContentValue());
                    if (result > 0) {
                        task.setId(result);
                        subscriber.onNext(task);
                    } else {
                        subscriber.onError(new Exception("Unable to insert data"));
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                }
            } finally {
                transaction.end();
            }
        });
    }

    public Observable<Task> addTask(Task task) {
        return Observable.create(new Observable.OnSubscribe<Task>() {

            @Override
            public void call(Subscriber<? super Task> subscriber) {
                if (subscriber.isUnsubscribed())
                    return;
                BriteDatabase.Transaction transaction = mDatabase.newTransaction();
                try {
                    long result = mDatabase.insert(TABLE_NAME, task.toContentValue());
                    if (result > 0) {
                        task.setId(result);
                        subscriber.onNext(task);
                    } else {
                        subscriber.onError(new Exception("Unable to insert data"));
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    @Override
    protected Task parseCursor(Cursor cursor) {
        return new Task().parseCursor(cursor);
    }
}
