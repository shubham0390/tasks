package com.mmt.shubh.owsmtasks.mvp;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMVPView().
 */
public abstract class BaseLCEPresenter<D, V extends LCEView<D>> implements Presenter<V> {

    private WeakReference<V> mMvpView;

    public BaseLCEPresenter() {
        Timber.tag(this.getClass().getName());
    }

    @Override
    public void attachView(V mvpView) {
        mMvpView = new WeakReference<V>(mvpView);
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMVPView() {
        checkViewAttached();
        return mMvpView.get();
    }

    protected void showError(Throwable e) {
        Timber.e(e.getMessage());
        e.printStackTrace();
        V v = getMVPView();
        if (v != null) {
            v.showError(getErrorMessageCode(e));
        }
    }

    private int getErrorMessageCode(Throwable e) {
        return 0;
    }

    protected void showProgress() {
        V v = getMVPView();
        if (v != null) {
            v.showProgress();
        }
    }

    protected void hideProgress() {
        V v = getMVPView();
        if (v != null) {
            v.hideProgress();
        }
    }

    protected void setData(D data) {
        V v = getMVPView();
        if (v != null) {
            v.hideProgress();
            v.setData(data);
        }
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MVPView) before" +
                    " requesting data to the Presenter");
        }
    }
}
