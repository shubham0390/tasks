package com.mmt.shubh.owsmtasks.task.list;

import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;
import com.mmt.shubh.owsmtasks.dagger.PerActivity;
import com.mmt.shubh.owsmtasks.home.TaskBoardFragment;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                TaskListModule.class
        }
)
public interface TaskListComponent {
        void inject(TaskBoardFragment taskBoardFragment);
}
