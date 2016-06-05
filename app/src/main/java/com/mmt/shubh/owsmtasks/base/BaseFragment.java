package com.mmt.shubh.owsmtasks.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmt.shubh.owsmtasks.TaskApplication;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;
import com.mmt.shubh.owsmtasks.mvp.MvpView;
import com.mmt.shubh.owsmtasks.mvp.Presenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by shubham on 1/4/16.
 */
public abstract class BaseFragment<V extends MvpView, P extends Presenter<V>> extends Fragment implements MvpView {

    @Inject
    protected P mPresenter;

    protected abstract void injectDependency(ApplicationComponent component);

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(getClass().getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectDependency(TaskApplication.get(getActivity().getApplicationContext()).getComponent());
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
