package com.mmt.shubh.owsmtasks.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.utility.Constants;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/19/16,
 */
public class TaskboardAdapter extends FragmentStatePagerAdapter {

    private List<TaskBoard> mTaskBoards = new ArrayList<>();

    public TaskboardAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addData(List<TaskBoard> taskBoards) {
        mTaskBoards.addAll(taskBoards);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        TaskBoardFragment boardFragment = new TaskBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.TASK_BOARD, Parcels.wrap(mTaskBoards.get(position)));
        boardFragment.setArguments(bundle);
        return boardFragment;
    }

    @Override
    public int getCount() {
        return mTaskBoards.size();
    }


}
