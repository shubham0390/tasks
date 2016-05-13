package com.mmt.shubh.owsmtasks.ui.Home;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.ui.presenter.BasePresenter;
import com.mmt.shubh.util.DSUtil;

import java.util.List;

import javax.inject.Inject;

import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 4/19/16,
 */
public class HomePresenter extends BasePresenter<List<TaskBoard>, HomeView> {

    private TaskBoardDataAdapter mBoardDataAdapter;

    @Inject
    public HomePresenter(TaskBoardDataAdapter adapter) {
        super();
        mBoardDataAdapter = adapter;
    }

    public void getTaskAllBoardWithTasks() {
        mBoardDataAdapter.getAllObserver()
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .subscribe(this::handleData, this::handleError);
    }

    @Override
    protected void handleError(Throwable throwable) {
        Timber.d("Unable to load task boards %s", throwable.getMessage());
    }

    @Override
    protected void handleData(List<TaskBoard> taskBoards) {
        if (DSUtil.isEmpty(taskBoards)) {
            getMvpView().showEmptyMessage();
        } else
            getMvpView().showData(taskBoards);
    }
}
