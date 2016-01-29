package com.mmt.shubh.owsmtasks.ui.mvpviews;

import com.mmt.shubh.datastore.model.TaskBoard;

import java.util.List;

/**
 * Created by subhamtyagi on 1/25/16.
 */
public interface HomeView extends MvpView {
    void setTaskBoardList(List<TaskBoard> taskBoards);

    void showError();

    void showProgress();

}
