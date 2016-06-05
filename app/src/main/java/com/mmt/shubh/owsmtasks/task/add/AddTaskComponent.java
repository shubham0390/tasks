package com.mmt.shubh.owsmtasks.task.add;

import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;
import com.mmt.shubh.owsmtasks.dagger.PerActivity;

import dagger.Component;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 1/7/16,
 */
@Component(dependencies = ApplicationComponent.class,
        modules = AddTaskModule.class)
@PerActivity
public interface AddTaskComponent {

    void inject(AddTaskActivity addTaskActivity);
}
