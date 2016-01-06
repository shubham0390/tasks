package com.mmt.shubh.taskmanager.ui;

import com.mmt.shubh.taskmanager.ui.activities.MvpView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends MvpView>{

    void attachView(V mvpView);

    void detachView();
}