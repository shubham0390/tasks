package com.mmt.shubh.owsmtasks.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.ui.DateSelectorView;
import com.mmt.shubh.owsmtasks.ui.injection.component.AddTaskComponent;
import com.mmt.shubh.owsmtasks.ui.injection.component.ApplicationComponent;
import com.mmt.shubh.owsmtasks.ui.injection.component.DaggerAddTaskComponent;
import com.mmt.shubh.owsmtasks.ui.injection.module.AddTaskModule;
import com.mmt.shubh.owsmtasks.ui.mvpviews.AddTaskView;
import com.mmt.shubh.owsmtasks.ui.presenter.AddTaskPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends BaseActivity<AddTaskView, AddTaskPresenter> implements AddTaskView {

    @Bind(R.id.task_name)
    EditText mTaskTitleEditText;

    @Bind(R.id.task_description)
    EditText mTaskDescriptionEditText;

    @Bind(R.id.start_time)
    DateSelectorView mStartDateSelectorView;

    @Bind(R.id.end_time)
    DateSelectorView mEndDateSelectorView;

    @Bind(R.id.start_task_check_box)
    CheckBox mStartTaskCheckBox;


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
        mPresenter.addTask(mTaskTitleEditText.getText().toString(), mTaskDescriptionEditText.getText().toString()
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


}
