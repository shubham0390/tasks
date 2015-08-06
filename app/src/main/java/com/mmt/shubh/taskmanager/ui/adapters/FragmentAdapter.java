package com.mmt.shubh.taskmanager.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mmt.shubh.taskmanager.ui.fragments.TaskListFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TaskListFragment();
            case 1:
                return new TaskListFragment();
            case 2:
                return new TaskListFragment();
            case 3:
                return new TaskListFragment();
            default:
                throw new IllegalArgumentException("Not a valid number");
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "New";

            case 1:
                return "In Progress";
            case 2:
                return "Pending";
            case 3:
                return "Completed";
        }
        return "";
    }
}