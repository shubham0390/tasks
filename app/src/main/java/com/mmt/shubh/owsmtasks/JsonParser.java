package com.mmt.shubh.owsmtasks;

import android.content.Context;
import android.content.res.AssetManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskboardDataAdapter;
import com.mmt.shubh.datastore.model.IModel;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.datastore.model.TaskBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Subham Tyagi,
 * on 05/Aug/2015,
 * 3:33 PM
 * TODO:Add class comment.
 */
public class JsonParser {

    TaskDataAdapter mTaskDataAdapter;
    TaskboardDataAdapter mTaskboardDataAdapter;
    Context mContext;

    @Inject
    public JsonParser(TaskDataAdapter taskDataAdapter, Context context, TaskboardDataAdapter dataAdapter) {
        mTaskDataAdapter = taskDataAdapter;
        mTaskboardDataAdapter = dataAdapter;
        mContext = context;
        Timber.tag(this.getClass().getName());
    }

    public Observable<IModel> seedData() {

        return Observable.create((Subscriber<? super IModel> subscriber) -> {
            createTaskboardList(readFromFile(mContext, "taskboard.json"), subscriber);
            createTaskList(readFromFile(mContext, "task.json"), subscriber);
        });
    }

    @DebugLog
    private void createTaskboardList(String jsonString, Subscriber<? super IModel> subscriber) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, TaskBoard.class);
            List<TaskBoard> taskList = objectMapper.readValue(jsonString, collectionType);
            mTaskboardDataAdapter.addDataList(taskList)
                    .observeOn(Schedulers.immediate())
                    .subscribeOn(Schedulers.immediate())
                    .subscribe(new Subscriber<TaskBoard>() {
                        @Override
                        public void onCompleted() {
                            subscriber.onCompleted();
                        }

                        @Override
                        public void onError(Throwable e) {
                            subscriber.onError(e);
                            Timber.e(e.getMessage());
                        }

                        @Override
                        public void onNext(TaskBoard task) {
                            subscriber.onNext(task);
                        }
                    });
        } catch (IOException e) {
            Timber.e(e.getMessage());
        }

        Timber.i("Converting json to object End");
    }

    @DebugLog
    private void createTaskList(String jsonString, Subscriber<? super IModel> subscriber) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class);
            List<Task> taskList = objectMapper.readValue(jsonString, collectionType);
            mTaskDataAdapter.addDataList(taskList)
                    .observeOn(Schedulers.immediate())
                    .subscribeOn(Schedulers.immediate())
                    .subscribe(new Subscriber<IModel>() {
                        @Override
                        public void onCompleted() {
                            subscriber.onCompleted();
                        }

                        @Override
                        public void onError(Throwable e) {
                            subscriber.onError(e);
                            Timber.e(e.getMessage());
                        }

                        @Override
                        public void onNext(IModel task) {
                            subscriber.onNext(task);
                        }
                    });
        } catch (IOException e) {
            Timber.e(e.getMessage());
        }

        Timber.i("Converting json to object End");
    }


    @DebugLog
    public String readFromFile(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();

        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = assetManager.open(fileName);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            Timber.e(e.getMessage());
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        Timber.i("Read file %s",fileName);
        return returnString.toString();
    }
}
