package com.mmt.shubh.owsmtasks.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.ui.activities.AddTaskActivity;
import com.mmt.shubh.owsmtasks.ui.activities.BaseActivity;
import com.mmt.shubh.owsmtasks.ui.adapters.TaskboardAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.recyclerviewlib.HorizontalRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity<HomeView, HomePresenter> implements HomeView {


    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.recycler_view)
    protected HorizontalRecyclerView mHorizontalRecyclerView;

    private TaskboardAdapter mTaskboardAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mTaskboardAdapter = new TaskboardAdapter();
        mHorizontalRecyclerView.setAdapter(mTaskboardAdapter);
        mPresenter.attachView(this);
        mPresenter.getTaskAllBoardWithTasks();
    }

    @OnClick(R.id.fab)
    public void onFacClick() {
        Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void injectDependency(ApplicationComponent component) {
        DaggerHomeComponent.builder()
                .applicationComponent(component)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void showEmptyMessage() {

    }

    @Override
    public void showData(List<TaskBoard> taskBoards) {
        mTaskboardAdapter.addData(taskBoards);
    }
}