package com.mmt.shubh.owsmtasks.ui.presenter;

import com.mmt.shubh.owsmtasks.ui.mvpviews.IMVPView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the IMVPView type that wants to be attached with.
 */
public interface Presenter<V extends IMVPView>{

    void attachView(V mvpView);

    void detachView();
}