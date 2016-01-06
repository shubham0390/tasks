package com.mmt.shubh.taskmanager.ui.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mmt.shubh.datastore.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.taskmanager.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends AppCompatActivity {

    @Bind(R.id.task_name)
    EditText mTaskTitleEditText;

    @Bind(R.id.task_description)
    EditText mTaskDescriptionEditText;

    @Bind(R.id.start_time)
    TextView mStartTimeTextView;

    @Bind(R.id.end_time)
    TextView mEndTimeTextView;

    @Bind(R.id.start_task_check_box)
    CheckBox mStartTaskCheckBox;

    private long startDate;
    private long endDate;

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

    private void setUpViews() {

    }

    @OnClick(R.id.start_time_container)
    public void onStartDateClick() {
        showDatePickerDialog(startDateSetListener);
    }

    private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mStartTimeTextView.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.YEAR, year);
            startDate = calendar.getTimeInMillis();
        }
    };

    private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mEndTimeTextView.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.YEAR, year);
            endDate = calendar.getTimeInMillis();
        }
    };

    @OnClick(R.id.end_time_container)
    public void onEndDateClick() {
        showDatePickerDialog(endDateSetListener);
    }


    @OnClick(R.id.save)
    public void addTask() {
        if (!emptyCheck()) {
            String taskName = mTaskTitleEditText.getText().toString();
            Task task = new Task();
            task.setDescription(mTaskDescriptionEditText.getText().toString());
            task.setStartDate(endDate);
            task.setCompletionDate(startDate);
            if (mStartTaskCheckBox.isChecked()) {
                task.setTaskStatus(Task.TaskStatus.INPROGRESS);
                task.setStartDate(System.currentTimeMillis());
            } else {
                task.setTaskStatus(Task.TaskStatus.NEW);
            }
            task.setTitle(taskName);
            TaskDataAdapter adapter = null;
            adapter.addTask(task);
            finish();
        }
    }

    private boolean emptyCheck() {
        boolean isEmpty = false;

        if (TextUtils.isEmpty(mTaskTitleEditText.getText().toString())) {
            mTaskTitleEditText.setError("Cannot be empty");
            isEmpty = true;
        }
        if (endDate <= 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public void showDatePickerDialog(DatePickerDialog.OnDateSetListener startDateSetListener) {
        DialogFragment newFragment = new DatePickerFragment(startDateSetListener);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener mOnDateSetListener;

        public DatePickerFragment() {
        }

        public DatePickerFragment(DatePickerDialog.OnDateSetListener onDateSetListener) {
            this();
            mOnDateSetListener = onDateSetListener;
        }

        @Override
        @Nullable
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), mOnDateSetListener, year, month, day);
        }


    }
}
