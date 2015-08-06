package com.mmt.shubh.taskmanager.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mmt.shubh.taskmanager.R;
import com.mmt.shubh.taskmanager.ui.fragments.TaskListFragment;
import com.mmt.shubh.taskmanager.ui.adapters.FragmentAdapter;
import com.mmt.shubh.taskmanager.ui.views.SlidingTabLayout;

public class HomeActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new TaskListFragment(), "New");
        fragmentAdapter.addFragment(new TaskListFragment(), "Pending");
        fragmentAdapter.addFragment(new TaskListFragment(), "In Progress");
        fragmentAdapter.addFragment(new TaskListFragment(), "Completed");
        viewPager.setAdapter(fragmentAdapter);
    }

}