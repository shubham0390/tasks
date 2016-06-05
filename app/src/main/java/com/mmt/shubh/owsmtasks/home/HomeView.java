package com.mmt.shubh.owsmtasks.home;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.mvp.MvpView;

import java.util.List;

/**
 * Created by subhamtyagi on 1/25/16.
 */
public interface HomeView extends MvpView {
    void setTaskBoardList(List<TaskBoard> taskBoards);

    void showError();

    void showProgress();

}
