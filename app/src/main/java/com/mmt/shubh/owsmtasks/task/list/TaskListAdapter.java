package com.mmt.shubh.owsmtasks.task.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {


    private List<Task> mData;

    public TaskListAdapter() {
        mData = new ArrayList<>();
    }

    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, final int position) {
        holder.bindView(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void add(Task s, int position) {
        position = position == -1 ? getItemCount() : position;
        mData.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Task getItemAtPosition(int position) {
        return mData.get(position);
    }

    public void addData(List<Task> taskList) {
        mData.addAll(taskList);
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_title)
        TextView mTitleTextView;

        @BindView(R.id.task_description)
        TextView mDescriptionTextView;

        @BindView(R.id.end_date)
        TextView mEndDateTextView;

        @BindView(R.id.status)
        TextView mStatus;


        public TaskViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(Task task) {
            mTitleTextView.setText(task.getTitle());
            mDescriptionTextView.setText(task.getDescription());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            mEndDateTextView.setText(dateFormat.format(task.getCompletion()));
            mStatus.setText(task.getTaskStatus().name());
        }
    }
}