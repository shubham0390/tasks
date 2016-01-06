package com.mmt.shubh.taskmanager.ui.view;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.taskmanager.ui.activities.MvpView;

import java.util.List;

/**
 * Created by shubham on 1/5/16.
 */
public interface TaskListView extends MvpView {

    void showErrorView();

    void showData(List<Task> taskList);

    void showEmptyView();
}
