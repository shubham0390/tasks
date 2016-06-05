package com.mmt.shubh.owsmtasks.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.utility.Constants;
import com.mmt.shubh.owsmtasks.R;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by subhamtyagi on 1/24/16.
 */
public class TaskBoardFragment extends Fragment {

    @Bind(R.id.title)
    TextView mTitleTextView;
    @Bind(R.id.description)
    TextView mDescriptionTextView;

    TaskBoard mTaskBoard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTaskBoard = Parcels.unwrap(bundle.getParcelable(Constants.TASK_BOARD));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_board_card, container, false);
        ButterKnife.bind(this, view);
        mTitleTextView.setText(mTaskBoard.getTitle());
        mDescriptionTextView.setText(mTaskBoard.getDescription());
        return view;
    }
}
