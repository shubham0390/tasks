package com.mmt.shubh.owsmtasks.views;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmt.shubh.owsmtasks.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/11/16,
 */
public class DateSelectorView extends RelativeLayout {


    @BindView(R.id.time_text_view)
    TextView mStartTimeTextView;

    @BindView(R.id.time_label_text_view)
    TextView mDateLabel;

    FragmentManager mFragmentManager;

    private long mTime;

    private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            setStartTimeTextView(dayOfMonth + "/" + monthOfYear + "/" + year);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.YEAR, year);
            mTime = calendar.getTimeInMillis();
        }
    };

    public DateSelectorView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public DateSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public DateSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DateSelectorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        View view = LayoutInflater.from(context).inflate(R.layout.date_selector_view, this, true);
        ButterKnife.bind(view, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateSelectorView, defStyleAttr, defStyleRes);
        CharSequence title = a.getText(R.styleable.DateSelectorView_text);
        mDateLabel.setText(title);
        a.recycle();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void setStartTimeTextView(String dateText) {
        mStartTimeTextView.setText(dateText);
    }

    public void setDateLabel(String dateLabel) {
        mDateLabel.setText(dateLabel);
    }

    public long getDateInMillis() {
        return mTime;
    }

    @OnClick(R.id.time_container)
    public void onStartDateClick() {
        showDatePickerDialog(startDateSetListener);
    }

    public void showDatePickerDialog(DatePickerDialog.OnDateSetListener startDateSetListener) {
        DialogFragment newFragment = new DatePickerFragment(startDateSetListener);
        newFragment.show(mFragmentManager, "datePicker");
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
