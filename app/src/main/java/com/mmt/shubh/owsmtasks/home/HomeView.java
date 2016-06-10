package com.mmt.shubh.owsmtasks.home;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.mvp.LCEView;
import com.mmt.shubh.owsmtasks.mvp.MVPView;

import java.util.List;

public interface HomeView extends LCEView<List<TaskBoard>> {

    void onEmptyTitle();

    void addNewTaskBoard(TaskBoard taskBoard);
}
