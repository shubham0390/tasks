package com.mmt.shubh.owsmtasks.ui.mvpviews;

import com.mmt.shubh.datastore.model.Task;

import java.util.List;

/**
 * Created by shubham on 1/5/16.
 */
public interface TaskListView extends IMVPView {

    void showErrorView();

    void showData(List<Task> taskList);

    void showEmptyView();
}
