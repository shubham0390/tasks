package com.mmt.shubh.owsmtasks.task.add;

import android.os.Bundle;
import android.text.TextUtils;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.model.IModel;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.mvp.BasePresenter;
import com.mmt.shubh.owsmtasks.utility.ModelFactory;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
public class AddTaskPresenter extends BasePresenter<AddTaskView> {


    private TaskDataAdapter mTaskDataAdapter;

    private Subscription mSubscription;

    private Observer<IModel> mTaskObserver = new Subscriber<IModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getMvpView().showAddTaskFailureMessage();
        }

        @Override
        public void onNext(IModel task) {
            if (task.getId() > 0) {
                getMvpView().finishActivity();
            } else {
                getMvpView().showAddTaskFailureMessage();
            }
        }
    };

    @Inject
    public AddTaskPresenter(TaskDataAdapter taskDataAdapter) {
        mTaskDataAdapter = taskDataAdapter;
    }

    public void addTask(long taskBoardId, String title, String description, long startDate, long endDate, boolean isStarted) {

        boolean isEmpty = emptyCheck(title, taskBoardId);
        if (isEmpty)
            return;

        Task.TaskStatus taskStatus;
        if (isStarted) {
            taskStatus = Task.TaskStatus.INPROGRESS;
            startDate = System.currentTimeMillis();
        } else {
            taskStatus = Task.TaskStatus.NEW;
        }
        Task task = ModelFactory.createNewTask();
        task.setTitle(title);
        task.setDescription(description);
        task.setTaskBorardKey(taskBoardId);
        task.setStartDate(startDate);
        task.setCompletion(endDate);
        task.setTaskStatus(taskStatus);
        task.setProgress("0");
        task.setCreationDate(System.currentTimeMillis());

        mSubscription = mTaskDataAdapter.create(task).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mTaskObserver);
    }

    private boolean emptyCheck(String title, long taskBoardId) {
        if (TextUtils.isEmpty(title)) {
            getMvpView().showTitleEmptyError();
            return true;
        }

        if (taskBoardId <= 0) {
            getMvpView().onInvalidTaskBoardId();
        }
        return false;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
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
}
