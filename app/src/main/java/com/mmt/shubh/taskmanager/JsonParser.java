package com.mmt.shubh.taskmanager;

import android.content.Context;
import android.content.res.AssetManager;


import com.mmt.shubh.datastore.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.model.Task;

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

import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by Subham Tyagi,
 * on 05/Aug/2015,
 * 3:33 PM
 * TODO:Add class comment.
 */
public class JsonParser {

    TaskDataAdapter mTaskDataAdapter;
    Context mContext;

    @Inject
    public JsonParser(TaskDataAdapter taskDataAdapter, Context context) {
        mTaskDataAdapter = taskDataAdapter;
        mContext = context;
        Timber.tag(this.getClass().getName());
    }

    public Observable<Boolean> seedData() {

        return Observable.create((Observable.OnSubscribe<Boolean>) subscriber -> {
            String jsonString = readFromFile(mContext);
            List<Task> taskList = new ArrayList<>();
            try {
                createTaskList(jsonString, taskList);
                subscriber.onNext(true);
                subscriber.onCompleted();
            } catch (JSONException e) {
                Timber.e(e.getMessage());
                subscriber.onError(e);
            } catch (ParseException e) {
                Timber.e(e.getMessage());
                subscriber.onError(e);
            }
            mTaskDataAdapter.addTaskListToDatabase(taskList);
        });

    }

    private void createTaskList(String jsonString, List<Task> taskList) throws JSONException, ParseException {

        JSONArray jsonArray = new JSONArray(jsonString);
        Timber.i("Converting json to object start");
        for (int i = 0; i < jsonArray.length(); i++) {
            Task task = new Task();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String title = jsonObject.getString("task_title");
            String enddate = jsonObject.getString("task_end_time");
            String startdate = jsonObject.getString("task_start_time");
            String description = jsonObject.getString("task_description");
            String status = jsonObject.getString("task_status");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            task.setCompletionDate(dateFormat.parse(enddate).getTime());
            task.setStartDate(dateFormat.parse(startdate).getTime());
            task.setTitle(title);
            task.setTaskStatus(Task.TaskStatus.valueOf(status));
            task.setDescription(description);
            taskList.add(task);
        }
        Timber.i("Converting json to object End");
    }


    public String readFromFile(Context context) {
        AssetManager assetManager = context.getAssets();

        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = assetManager.open("task_database.json");
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
        Timber.i("Read file");
        return returnString.toString();
    }
}
