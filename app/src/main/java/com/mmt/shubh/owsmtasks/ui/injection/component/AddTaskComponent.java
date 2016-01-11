package com.mmt.shubh.owsmtasks.ui.injection.component;

import com.mmt.shubh.owsmtasks.ui.activities.AddTaskActivity;
import com.mmt.shubh.owsmtasks.ui.injection.PerActivity;
import com.mmt.shubh.owsmtasks.ui.injection.module.AddTaskModule;

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
