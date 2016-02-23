package com.mmt.shubh.owsmtasks.ui.adapters;

import android.content.Context;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private final Context mContext;

    private List<Task> mData;


    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_task, parent, false);
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

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.task_title)
        TextView mTitleTextView;

        @Bind(R.id.task_description)
        TextView mDescriptionTextView;

        @Bind(R.id.end_date)
        TextView mEndDateTextView;

        @Bind(R.id.status)
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

    public TaskListAdapter(Context context, List<Task> data) {
        mContext = context;
        if (data != null)
            mData = data;
        else
            mData = new ArrayList<>();
    }


}