package com.mmt.shubh.owsmtasks.ui.presenter;

import com.mmt.shubh.owsmtasks.ui.mvpviews.IMVPView;

import timber.log.Timber;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public abstract class BasePresenter<M, V extends IMVPView> implements Presenter<V> {

    private V mMvpView;

    public BasePresenter() {
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    protected abstract void handleError(Throwable throwable);

    protected abstract void handleData(M m);

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(IMVPView) before" +
                    " requesting data to the Presenter");
        }
    }
}
