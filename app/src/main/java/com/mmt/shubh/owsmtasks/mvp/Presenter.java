package com.mmt.shubh.owsmtasks.mvp;

import android.os.Bundle;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MVPView type that wants to be attached with.
 */
public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);

    void detachView();

    void onActivityRestored(Bundle bundle);

    void onStop();

    void onStart();
}