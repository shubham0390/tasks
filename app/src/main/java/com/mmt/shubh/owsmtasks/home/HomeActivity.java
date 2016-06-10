package com.mmt.shubh.owsmtasks.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.base.BaseActivity;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.findById;

public class HomeActivity extends BaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.recycler_view)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.message_text_view)
    TextView mMessageTextView;

    EditText mTaskBoardTitleTextView;
    EditText mTaskBoardDescriptionTextView;

    TaskBoardAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mPresenter.attachView(this);
        mPresenter.loadTaskBoards();
        mAdapter = new TaskBoardAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    protected void injectDependency(ApplicationComponent component) {
        HomeActivityComponent activityComponent = DaggerHomeActivityComponent
                .builder().applicationComponent(component)
                .homeActivityModule(new HomeActivityModule(this)).build();
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_task_board) {
            showAddTaskBoardTitle();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddTaskBoardTitle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_task_board);
        builder.setView(R.layout.dailog_add_task_board);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            dialog.dismiss();
        });
        builder.setPositiveButton(R.string.add, (dialog, which) -> {
            mPresenter.createTaskBoard(mTaskBoardTitleTextView.getText().toString(),
                    mTaskBoardDescriptionTextView.getText().toString());
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        mTaskBoardTitleTextView = findById(alertDialog, R.id.task_board_title);
        mTaskBoardDescriptionTextView = findById(alertDialog, R.id.task_board_description);
    }

    @Override
    public void showError(int errorMessageType) {
        // TODO: 07/06/16 replace it with proper error message base on error type
        mMessageTextView.setText("Unable to load data");
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
    public void setData(List<TaskBoard> data) {
        mAdapter.addData(data);
    }

    @Override
    public void onEmptyTitle() {
        mTaskBoardTitleTextView.setError(getString(R.string.error_title_empty));
    }

    @Override
    public void addNewTaskBoard(TaskBoard taskBoard) {
        mMessageTextView.setVisibility(View.GONE);
        mAdapter.addTaskBoard(taskBoard);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}