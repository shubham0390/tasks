package com.mmt.shubh.owsmtasks.ui.mvpviews;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
public interface AddTaskView extends IMVPView {
    void showTitleEmptyError();

    void finishActivity();

    void showAddTaskFailureMessage();
}
