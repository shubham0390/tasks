package com.mmt.shubh.owsmtasks.splash;

import com.mmt.shubh.owsmtasks.mvp.MVPView;

/**
 * Created by shubham on 1/6/16.
 */
public interface SplashView extends MVPView {
    void startHomeActivity();

    void showError(String s);
}
