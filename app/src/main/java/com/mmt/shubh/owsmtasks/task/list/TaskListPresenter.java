package com.mmt.shubh.owsmtasks.task.list;


import android.os.Bundle;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.mvp.BaseLCEPresenter;
import com.mmt.shubh.owsmtasks.mvp.BasePresenter;
import com.mmt.shubh.owsmtasks.utility.ObserverCache;
import com.mmt.shubh.util.DSUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class TaskListPresenter extends BaseLCEPresenter<List<Task>, TaskListView> {

    private static final String TAG_TASK_LIST = "taskList";

    private TaskDataAdapter mTaskDataAdapter;

    @Inject
    public TaskListPresenter(TaskDataAdapter taskDataAdapter) {
        mTaskDataAdapter = taskDataAdapter;
    }

    private Subscription mSubscription;

    @Override
    public void detachView() {
        super.detachView();
        mSubscription.unsubscribe();
    }

    @Override
    public void onActivityRestored(Bundle bundle) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    public void loadTaskByTaskBoardId(long taskBoardId) {
        Observable<List<Task>> listObservable = mTaskDataAdapter.getTaskByTaskboardId(taskBoardId);
        ObserverCache.getInstance().addObserver(TAG_TASK_LIST, listObservable);
        mSubscription = listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::setData, this::showError);
    }

}
