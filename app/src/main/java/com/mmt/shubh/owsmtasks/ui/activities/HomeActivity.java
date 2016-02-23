package com.mmt.shubh.owsmtasks.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.WindowManager;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.ui.adapters.TaskboardAdapter;
import com.mmt.shubh.owsmtasks.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.owsmtasks.ui.injection.component.DaggerHomeActivityComponent;
import com.mmt.shubh.owsmtasks.ui.injection.component.HomeActivityComponent;
import com.mmt.shubh.owsmtasks.ui.injection.module.HomeActivityModule;
import com.mmt.shubh.owsmtasks.ui.mvpviews.HomeView;
import com.mmt.shubh.owsmtasks.ui.presenter.HomePresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity<HomeView, HomePresenter> implements HomeView {

    @Bind(R.id.recycler_view)
    ViewPager mViewPager;

    @Bind(R.id.fab)
    FloatingActionButton mActionButton;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    TaskboardAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mPresenter.attachView(this);
        mPresenter.loadTaskBoards();
        mAdapter = new TaskboardAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void injectDependency(ApplicationComponent component) {
        HomeActivityComponent activityComponent = DaggerHomeActivityComponent
                .builder().applicationComponent(component)
                .homeActivityModule(new HomeActivityModule(this)).build();
        activityComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void setTaskBoardList(List<TaskBoard> taskBoards) {
        mAdapter.addData(taskBoards);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {

    }
}