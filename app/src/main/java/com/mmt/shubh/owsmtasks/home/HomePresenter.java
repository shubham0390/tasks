package com.mmt.shubh.owsmtasks.home;

import android.os.Bundle;
import android.text.TextUtils;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.mvp.BaseLCEPresenter;
import com.mmt.shubh.owsmtasks.utility.ObserverCache;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class HomePresenter extends BaseLCEPresenter<List<TaskBoard>, HomeView> {

    private static final String TAG_OBSERVABLE = "observable";
    private TaskBoardDataAdapter mTaskBoardDataAdapter;
    private Subscription mSubscription;

    @Inject
    public HomePresenter(TaskBoardDataAdapter dataAdapter) {
        super();
        mTaskBoardDataAdapter = dataAdapter;
    }

    public void loadTaskBoards() {
        showProgress();
        Observable<List<TaskBoard>> observable = mTaskBoardDataAdapter.getAll().cache();
        ObserverCache.getInstance().addObserver(TAG_OBSERVABLE, observable);
        mSubscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::showError);
    }

    @Override
    protected void showError(Throwable e) {
        ObserverCache.getInstance().remove(TAG_OBSERVABLE);
        super.showError(e);
    }

    @Override
    public void onActivityRestored(Bundle bundle) {
        Observable<List<TaskBoard>> observable = ObserverCache.getInstance().getObservable(TAG_OBSERVABLE);
        if (observable == null) {
            observable = mTaskBoardDataAdapter.getAll().cache();
            ObserverCache.getInstance().addObserver(TAG_OBSERVABLE, observable);
        }
        mSubscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::showError);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    public void createTaskBoard(String taskBardTitle, String taskBoardDescription) {

        if (TextUtils.isEmpty(taskBardTitle)) {
            HomeView homeView = getMVPView();
            if (homeView != null) {
                homeView.onEmptyTitle();
            } else {
                Timber.d("View is not attached with presenter");
            }
        }

        mTaskBoardDataAdapter.create(new TaskBoard(taskBardTitle, taskBoardDescription))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addTaskBoard, this::showError);

    }

    private void addTaskBoard(TaskBoard taskBoard) {
        getMVPView().addNewTaskBoard(taskBoard);
    }
}
