package com.mmt.shubh.owsmtasks.task.add;

import com.mmt.shubh.owsmtasks.mvp.MVPView;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
public interface AddTaskView extends MVPView {
    void showTitleEmptyError();

    void finishActivity();

    void showAddTaskFailureMessage();

    void onInvalidTaskBoardId();

}
