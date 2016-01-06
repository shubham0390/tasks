package com.mmt.shubh.taskmanager.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.taskmanager.R;
import com.mmt.shubh.taskmanager.ui.adapters.TaskListAdapter;
import com.mmt.shubh.taskmanager.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.taskmanager.ui.injection.component.DaggerHomeActivityComponent;
import com.mmt.shubh.taskmanager.ui.injection.component.HomeActivityComponent;
import com.mmt.shubh.taskmanager.ui.injection.module.HomeActivityModule;
import com.mmt.shubh.taskmanager.ui.presenter.TaskListPresenter;
import com.mmt.shubh.taskmanager.ui.view.TaskListView;
import com.mmt.shubh.taskmanager.ui.views.ListRecyclerView;

import java.util.List;

import butterknife.Bind;


/**
 * Created by Subham Tyagi,
 * on 04/Aug/2015,
 * 6:13 PM
 * TODO:Add class comment.
 */
public class TaskListFragment extends BaseFragment<TaskListView, TaskListPresenter> implements TaskListView {

    private TaskListAdapter mTaskListAdapter;

    @Bind(R.id.recycler_view)
    ListRecyclerView mRecyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.empty_text)
    TextView mEmptyTextView;

    @Bind(R.id.progress_container)
    FrameLayout mEmptyContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadData();
    }

    @Override
    protected void injectDependency(ApplicationComponent component) {
        HomeActivityComponent homeActivityComponent = DaggerHomeActivityComponent.builder()
                .applicationComponent(component)
                .homeActivityModule(new HomeActivityModule(getActivity()))
                .build();
        homeActivityComponent.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showData(List<Task> taskList) {
        mEmptyContainer.setVisibility(View.GONE);
        mTaskListAdapter = new TaskListAdapter(getActivity(), taskList);
        mRecyclerView.setAdapter(mTaskListAdapter);
        mRecyclerView.setOnItemClickListener(mOnItemClickListener);
    }

    private ListRecyclerView.OnItemClickListener mOnItemClickListener = new ListRecyclerView.OnItemClickListener() {
        @Override
        public boolean onItemClick(RecyclerView parent, View view, int position, long id) {
            onClick(mTaskListAdapter.getItemAtPosition(position));
            return true;
        }
    };

    private void onClick(Task task) {

    }

    @Override
    public void showEmptyView() {
        mProgressBar.setVisibility(View.GONE);
    }
}
