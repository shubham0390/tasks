package com.mmt.shubh.owsmtasks.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmt.shubh.datastore.model.TaskBoard;
import com.mmt.shubh.owsmtasks.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/19/16,
 */
public class TaskboardAdapter extends RecyclerView.Adapter<TaskboardAdapter.ViewHolder> {

    private List<TaskBoard> mTaskBoards = new ArrayList<>();

    @Override
    public TaskboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.task_board_card, parent));
    }

    @Override
    public void onBindViewHolder(TaskboardAdapter.ViewHolder holder, int position) {
        holder.bindView(mTaskBoards.get(position));
    }

    @Override
    public int getItemCount() {
        return mTaskBoards.size();
    }

    public void addData(List<TaskBoard> taskBoards) {
        mTaskBoards.addAll(taskBoards);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView mTitleTextView;
        @Bind(R.id.description)
        TextView mDescriptionTextView;
      /*  @Bind(R.id.taskList)
        TaskListDataView mTaskListDataView;*/

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(TaskBoard taskBoard) {
            mTitleTextView.setText(taskBoard.getTitle());
            mDescriptionTextView.setText(taskBoard.getTaskDescription());
            // mTaskListDataView.showData(taskBoard.getTasks());
        }
    }
}
