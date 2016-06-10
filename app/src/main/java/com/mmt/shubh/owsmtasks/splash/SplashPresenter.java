package com.mmt.shubh.owsmtasks.splash;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.mmt.shubh.datastore.model.IModel;
import com.mmt.shubh.owsmtasks.mvp.BasePresenter;
import com.mmt.shubh.owsmtasks.utility.Constants;
import com.mmt.shubh.owsmtasks.utility.JsonParser;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SplashPresenter extends BasePresenter<SplashView> {

    final SharedPreferences mSharedPreferences;


    final JsonParser mJsonParser;

    @Inject
    public SplashPresenter(SharedPreferences sharedPreferences, JsonParser jsonParser) {
        mSharedPreferences = sharedPreferences;
        mJsonParser = jsonParser;
        Timber.tag(this.getClass().getName());
    }


    public void addSeedData() {
        boolean seedDataStatus = mSharedPreferences.getBoolean(Constants.KEY_SEED_DATA_ADDED, false);
        if (seedDataStatus){
            Timber.i("Seed Data Seed Data already added");
            getMvpView().startHomeActivity();
            return;
        }
        mJsonParser.seedData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<IModel>() {
                    @Override
                    public void onCompleted() {
                        mSharedPreferences.edit().putBoolean(Constants.KEY_SEED_DATA_ADDED, true).apply();
                        getMvpView().startHomeActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError("");
                    }

                    @Override
                    public void onNext(IModel task) {
                        Timber.d("Task added to database with id = %s", task.getId());
                    }
                });
    }


    @Override
    public void onActivityRestored(Bundle bundle) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }
}
