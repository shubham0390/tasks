package com.mmt.shubh.owsmtasks.mvp;

/**
 * Created by Subham on 08/06/16.
 */
public interface LCEView<D> extends MVPView {
    void showError(int errorMessageType);

    void showProgress();

    void hideProgress();

    void setData(D data);
}
