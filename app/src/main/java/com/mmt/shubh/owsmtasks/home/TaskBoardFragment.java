package com.mmt.shubh.owsmtasks.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.base.BaseFragment;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;
import com.mmt.shubh.owsmtasks.task.add.AddTaskActivity;
import com.mmt.shubh.owsmtasks.task.list.DaggerTaskListComponent;
import com.mmt.shubh.owsmtasks.task.list.TaskListComponent;
import com.mmt.shubh.owsmtasks.task.list.TaskListDataView;
import com.mmt.shubh.owsmtasks.task.list.TaskListModule;
import com.mmt.shubh.owsmtasks.task.list.TaskListPresenter;
import com.mmt.shubh.owsmtasks.task.list.TaskListView;
import com.mmt.shubh.owsmtasks.utility.Constants;
import com.mmt.shubh.owsmtasks.R;

import org.parceler.Parcels;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskBoardFragment extends BaseFragment<TaskListView, TaskListPresenter> implements TaskListView {

    @BindView(R.id.title)
    TextView mTitleTextView;

    @BindView(R.id.description)
    TextView mDescriptionTextView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.empty_text)
    TextView mMessageTextView;

    @BindView(R.id.progress_container)
    FrameLayout mContainer;

    @BindView(R.id.taskList)
    TaskListDataView mTaskListDataView;

    TaskBoard mTaskBoard;

    @Override
    protected void injectDependency(ApplicationComponent appComponent) {
        TaskListComponent component = DaggerTaskListComponent.builder()
                .applicationComponent(appComponent)
                .taskListModule(new TaskListModule())
                .build();
        component.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.task_board_card;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTaskBoard = Parcels.unwrap(bundle.getParcelable(Constants.TASK_BOARD));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitleTextView.setText(mTaskBoard.getTitle());
        mDescriptionTextView.setText(mTaskBoard.getDescription());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadTaskByTaskBoardId(mTaskBoard.getId());
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        Intent intent = new Intent(getActivity(), AddTaskActivity.class);
        intent.putExtra(Constants.TASK_BOARD_ID, mTaskBoard.getId());
        startActivity(intent);
    }

    @Override
    public void showError(int errorMessageType) {
        mMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<Task> data) {
        mTaskListDataView.showData(data);
    }
}
