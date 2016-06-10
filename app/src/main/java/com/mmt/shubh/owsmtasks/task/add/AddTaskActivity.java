package com.mmt.shubh.owsmtasks.task.add;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.base.BaseActivity;
import com.mmt.shubh.owsmtasks.utility.Constants;
import com.mmt.shubh.owsmtasks.views.DateSelectorView;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends BaseActivity<AddTaskView, AddTaskPresenter> implements AddTaskView {

    @BindView(R.id.task_name)
    EditText mTaskTitleEditText;

    @BindView(R.id.task_description)
    EditText mTaskDescriptionEditText;

    @BindView(R.id.start_time)
    DateSelectorView mStartDateSelectorView;

    @BindView(R.id.end_time)
    DateSelectorView mEndDateSelectorView;

    @BindView(R.id.start_task_check_box)
    CheckBox mStartTaskCheckBox;

    private long mTaskBoardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpViews();
        mTaskBoardId = getIntent().getLongExtra(Constants.TASK_BOARD_ID, -1);
    }

    @Override
    protected void injectDependency(ApplicationComponent component) {
        AddTaskComponent addTaskComponent = DaggerAddTaskComponent.builder()
                .applicationComponent(component)
                .addTaskModule(new AddTaskModule()).build();
        addTaskComponent.inject(this);
    }

    private void setUpViews() {
        mEndDateSelectorView.setFragmentManager(getSupportFragmentManager());
        mStartDateSelectorView.setFragmentManager(getSupportFragmentManager());
    }


    @OnClick(R.id.save)
    public void addTask() {
        mPresenter.addTask(mTaskBoardId, mTaskTitleEditText.getText().toString(), mTaskDescriptionEditText.getText().toString()
                , mStartDateSelectorView.getDateInMillis(), mEndDateSelectorView.getDateInMillis(), mStartTaskCheckBox.isChecked());
    }


    @Override
    public void showTitleEmptyError() {
        mTaskTitleEditText.setError(getString(R.string.title_empty_error));
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showAddTaskFailureMessage() {

    }

    @Override
    public void onInvalidTaskBoardId() {

    }

}
