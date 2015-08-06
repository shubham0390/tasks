package com.mmt.shubh.taskmanager.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.mmt.shubh.taskmanager.R;
import com.mmt.shubh.taskmanager.TaskStatus;
import com.mmt.shubh.taskmanager.ui.adapters.TaskListAdapter;
import com.mmt.shubh.taskmanager.tasks.Task;
import com.mmt.shubh.taskmanager.db.TaskDataAdapter;

import java.util.List;

/**
 * Created by Subham Tyagi,
 * on 04/Aug/2015,
 * 6:13 PM
 * TODO:Add class comment.
 */
public class TaskListFragment extends RecyclerViewFragment {

    private TaskListAdapter mTaskListAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_list;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(12, null, mLoaderCallbacks).forceLoad();
    }

    public LoaderManager.LoaderCallbacks<List<Task>> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<List<Task>>() {
        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            return new TaskListLoader(getActivity().getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader<List<Task>> loader, List<Task> data) {
            if (data == null || data.isEmpty()) {
                showRecylerView(false);
                setEmptyText(R.string.empty_text_no_conntent);
            } else {
                showRecylerView(true);
                TaskListAdapter taskListAdapter = new TaskListAdapter(getActivity().getApplicationContext(), data);
                setAdapter(taskListAdapter);
                taskListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(Loader loader) {

        }
    };

    public static class TaskListLoader extends AsyncTaskLoader<List<Task>> {
        private Context mContext;

        public TaskListLoader(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public List<Task> loadInBackground() {
            TaskDataAdapter adapter = TaskDataAdapter.getInstance(mContext);
            return adapter.getTaskByStatus(TaskStatus.COMPLETED.name());
        }
    }
}
