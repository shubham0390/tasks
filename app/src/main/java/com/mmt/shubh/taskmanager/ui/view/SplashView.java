package com.mmt.shubh.taskmanager.ui.view;

import com.mmt.shubh.taskmanager.ui.activities.MvpView;

/**
 * Created by shubham on 1/6/16.
 */
public interface SplashView extends MvpView {
    void startHomeActivity();

    void showError(String s);
}
