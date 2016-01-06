package com.mmt.shubh.taskmanager.ui.presenter;


import com.mmt.shubh.datastore.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.taskmanager.ui.BasePresenter;
import com.mmt.shubh.taskmanager.ui.view.TaskListView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by shubham on 1/5/16.
 */
public class TaskListPresenter extends BasePresenter<TaskListView> {

    private TaskDataAdapter mTaskDataAdapter;

    @Inject
    public TaskListPresenter(TaskDataAdapter taskDataAdapter) {
        mTaskDataAdapter = taskDataAdapter;
    }

    private Subscription mSubscription;

    public void loadData() {
        mSubscription = mTaskDataAdapter.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showErrorView();
                        Timber.e(e, "There was an error loading the tasks.");
                    }

                    @Override
                    public void onNext(List<Task> data) {
                        if (data == null || data.isEmpty()) {
                            getMvpView().showEmptyView();
                        } else {
                            getMvpView().showData(data);
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        super.detachView();
        mSubscription.unsubscribe();
    }
}