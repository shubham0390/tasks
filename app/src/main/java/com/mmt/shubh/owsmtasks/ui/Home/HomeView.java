package com.mmt.shubh.owsmtasks.ui.Home;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.ui.mvpviews.IMVPView;

import java.util.List;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 4/19/16,
 */
public interface HomeView extends IMVPView {
    void showEmptyMessage();

    void showData(List<TaskBoard> taskBoards);
}
