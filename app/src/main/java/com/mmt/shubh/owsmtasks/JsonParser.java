package com.mmt.shubh.owsmtasks;

import android.content.Context;
import android.content.res.AssetManager;

import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskboardDataAdapter;
import com.mmt.shubh.datastore.model.IModel;
import com.mmt.shubh.datastore.model.ModelFactory;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.datastore.model.TaskBoard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
            createTask(readFromFile(mContext, "task.json"), subscriber);
        });
    }

    @DebugLog
    private void createTaskboardList(String jsonString, Subscriber<? super IModel> subscriber) {
        List<TaskBoard> taskList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                taskList.add(createTaskBoard(jsonObject));
            }
        } catch (JSONException e) {
            Timber.e(e.getMessage());
        }
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

        Timber.i("Converting json to object End");
    }

    @DebugLog
    private TaskBoard createTaskBoard(JSONObject jsonObject) {
        TaskBoard taskBoard = ModelFactory.getNewTaskBoard();
        try {
            taskBoard.setId(jsonObject.getLong(TaskContract.TaskBoardColumn._ID));

            taskBoard.setTitle(jsonObject.getString(TaskContract.TaskBoardColumn.TITLE));//titile
            taskBoard.setDescription(jsonObject.getString(TaskContract.TaskBoardColumn.DESCRIPTION));// description
            try {
                taskBoard.setCreateDate(sdf.parse(jsonObject.getString(TaskContract.TaskBoardColumn.CREATED_DATE)).getTime());//creae date
                taskBoard.setStartDate(sdf.parse(jsonObject.getString(TaskContract.TaskBoardColumn.START_DATE)).getTime());//start date
            } catch (ParseException e) {
                Timber.e(e.getMessage());
            }
            taskBoard.setStatus(jsonObject.getString(TaskContract.TaskBoardColumn.STATUS));
            taskBoard.setProgress(String.valueOf(jsonObject.get(TaskContract.TaskBoardColumn.PROGRESS)));//progress
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return taskBoard;

    }

    @DebugLog
    private void createTask(String jsonString, Subscriber<? super IModel> subscriber) {
        List<Task> tasks = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                try {
                    tasks.add(createTask(jsonObject));
                } catch (ParseException e) {
                    Timber.e(e.getMessage());
                }
            }
        } catch (JSONException e) {
            Timber.e(e.getMessage());
        }
        mTaskDataAdapter.addDataList(tasks)
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

        Timber.i("Converting json to object End");
    }

    @DebugLog
    private Task createTask(JSONObject jsonObject) throws JSONException, ParseException {
        Task task = ModelFactory.getNewTask();
        task.setId(jsonObject.getLong(TaskContract.TaskColumn._ID));
        task.setTitle(jsonObject.getString(TaskContract.TaskColumn.TITLE));
        task.setDescription(jsonObject.getString(TaskContract.TaskColumn.DESCRIPTION));
        task.setCreationDate(sdf.parse(jsonObject.getString(TaskContract.TaskColumn.CREATED_DATE)).getTime());
        task.setStartDate(sdf.parse(jsonObject.getString(TaskContract.TaskColumn.COMPLETION_DATE)).getTime());
        task.setCreationDate(sdf.parse(jsonObject.getString(TaskContract.TaskColumn.START_DATE)).getTime());
        task.setTaskStatus(Task.TaskStatus.valueOf(jsonObject.getString(TaskContract.TaskColumn.STATUS)));
        task.setProgress(jsonObject.getString(TaskContract.TaskColumn.PROGRESS));
        task.setTaskBorardKey(jsonObject.getLong(TaskContract.TaskColumn.TASK_BOARD_KEY));
        return task;
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
        Timber.i("Read file %s", fileName);
        return returnString.toString();
    }
}
