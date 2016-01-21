package com.mmt.shubh.owsmtasks.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.R;
import com.mmt.shubh.owsmtasks.ui.adapters.TaskboardAdapter;
import com.mmt.shubh.recyclerviewlib.HorizontalRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HorizontalRecyclerView recyclerView = (HorizontalRecyclerView) findViewById(R.id.recycler_view);
        TaskboardAdapter adapter = new TaskboardAdapter();
        recyclerView.setAdapter(adapter);
        List<TaskBoard> taskBoards = new ArrayList<>();
        TaskBoard taskBoard = new TaskBoard();
        taskBoard.setTaskTitle("Title1");
        taskBoard.setTaskDescription("asudfhlaisjbdvkjsdfkgjsluhgituh");
        taskBoards.add(taskBoard);

        TaskBoard taskBoard1 = new TaskBoard();
        taskBoard1.setTaskTitle("Title1");
        taskBoard1.setTaskDescription("jsadflkjsndlkjnelijrhfeljshdfljnjhlkjhsdk");
        taskBoards.add(taskBoard1);
        adapter.addData(taskBoards);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
    }


}