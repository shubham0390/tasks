package com.mmt.shubh.owsmtasks.task.list;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.TaskApplication;
import com.mmt.shubh.owsmtasks.views.injection.component.DaggerHomeActivityComponent;
import com.mmt.shubh.owsmtasks.home.HomeActivityComponent;
import com.mmt.shubh.recyclerviewlib.ListRecyclerView;

import java.util.List;

import javax.inject.Inject;

import static butterknife.ButterKnife.findById;


/**
 * Created by Subham Tyagi,
 * on 04/Aug/2015,
 * 6:13 PM
 * TODO:Add class comment.
 */
public class TaskListDataView extends FrameLayout implements TaskListView {

    ListRecyclerView mRecyclerView;

    ProgressBar mProgressBar;

    TextView mEmptyTextView;

    FrameLayout mEmptyContainer;

    TaskListAdapter mTaskListAdapter;

    @Inject
    TaskListPresenter mTaskListPresenter;

    private ListRecyclerView.OnItemClickListener mOnItemClickListener = new ListRecyclerView.OnItemClickListener() {
        @Override
        public boolean onItemClick(RecyclerView parent, View view, int position, long id) {
            onClick(mTaskListAdapter.getItemAtPosition(position));
            return true;
        }
    };

    public TaskListDataView(Context context) {
        super(context);
        init(context);
    }

    public TaskListDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TaskListDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TaskListDataView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_task_list, this, true);
        mRecyclerView = (ListRecyclerView) findViewById(R.id.recycler_view);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        mEmptyTextView = (TextView) view.findViewById(R.id.empty_text);

        mEmptyContainer = (FrameLayout) view.findViewById(R.id.progress_container);
        if (isInEditMode()) {
            return;
        }
        mTaskListAdapter =  new TaskListAdapter(context,null);
        HomeActivityComponent component = DaggerHomeActivityComponent.builder()
                .applicationComponent(TaskApplication.get(getContext()).getComponent())
                .taskListModule(new TaskListModule())
                .build();
        component.inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // mTaskListPresenter.attachView(this);
    }

    @Override
    public void showErrorView() {
        mEmptyContainer.setVisibility(VISIBLE);
        mEmptyTextView.setText("Unable to load task list");
    }

    @Override
    public void showData(List<Task> taskList) {
        mEmptyContainer.setVisibility(View.GONE);
        mTaskListAdapter = new TaskListAdapter(getContext(), taskList);
        mRecyclerView.setAdapter(mTaskListAdapter);
        mRecyclerView.setOnItemClickListener(mOnItemClickListener);
    }

    private void onClick(Task task) {
        // TODO: 1/20/16 opentaskdetailActivity
    }

    @Override
    public void showEmptyView() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTaskListPresenter.detachView();
    }

    public void setTaskBoardId(long taskboardId) {
        mTaskListPresenter.loadTaskByTaskboardId(taskboardId);
    }
}
