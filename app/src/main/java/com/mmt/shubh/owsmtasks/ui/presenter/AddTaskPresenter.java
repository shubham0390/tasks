package com.mmt.shubh.owsmtasks.ui.presenter;

import android.text.TextUtils;

import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.TaskFactory;
import com.mmt.shubh.owsmtasks.ui.mvpviews.AddTaskView;

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
public class AddTaskPresenter extends BasePresenter<Task, AddTaskView> {


    private TaskDataAdapter mTaskDataAdapter;

    private Subscription mSubscription;

    private Observer<Task> mTaskObserver = new Subscriber<Task>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getMvpView().showAddTaskFailureMessage();
        }

        @Override
        public void onNext(Task task) {
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

    public void addTask(String title, String description, long startDate, long endDate, boolean isStarted) {

        boolean isEmpty = emptyCheck(title);
        if (isEmpty)
            return;

        Task.TaskStatus taskStatus;
        if (isStarted) {
            taskStatus = Task.TaskStatus.INPROGRESS;
            startDate = System.currentTimeMillis();
        } else {
            taskStatus = Task.TaskStatus.NEW;
        }
        Task task = TaskFactory.getInstance().createNewTask();
        task.setTitle(title);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setCompletionDate(endDate);
        task.setTaskStatus(taskStatus);
        task.setProgress("0");
        task.setCreationDate(System.currentTimeMillis());

        mSubscription = mTaskDataAdapter.addTask(task).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mTaskObserver);
    }

    private boolean emptyCheck(String title) {
        if (TextUtils.isEmpty(title)) {
            getMvpView().showTitleEmptyError();
            return true;
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
    protected void handleError(Throwable throwable) {

    }

    @Override
    protected void handleData(Task task) {

    }
}
