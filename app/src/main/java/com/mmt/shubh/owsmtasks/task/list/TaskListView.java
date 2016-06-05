package com.mmt.shubh.owsmtasks.task.list;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.mvp.MvpView;

import java.util.List;


public interface TaskListView extends MvpView {

    void showErrorView();

    void showData(List<Task> taskList);

    void showEmptyView();
}
