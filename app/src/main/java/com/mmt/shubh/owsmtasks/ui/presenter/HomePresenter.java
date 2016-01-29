package com.mmt.shubh.owsmtasks.ui.presenter;

import com.mmt.shubh.datastore.database.adapter.TaskboardDataAdapter;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.ui.mvpviews.HomeView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by subhamtyagi on 1/25/16.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    TaskboardDataAdapter mDataAdapter;

    @Inject
    public HomePresenter(TaskboardDataAdapter dataAdapter) {
        mDataAdapter = dataAdapter;
        Timber.tag(this.getClass().getName());
    }

    public void loadTaskBoards() {
        getMvpView().showProgress();
        mDataAdapter.getAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<List<TaskBoard>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.d("Unable to load tasnboard list %s", e.getMessage());
                getMvpView().showError();
            }

            @Override
            public void onNext(List<TaskBoard> taskBoards) {
                getMvpView().setTaskBoardList(taskBoards);
            }
        });

    }
}
