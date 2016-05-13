package com.mmt.shubh.owsmtasks;

import android.content.Context;
import android.content.res.AssetManager;

import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.datastore.model.TaskBoard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    TaskBoardDataAdapter mBoardDataAdapter;
    Context mContext;

    @Inject
    public JsonParser(TaskDataAdapter taskDataAdapter, Context context, TaskBoardDataAdapter boardDataAdapter) {
        mTaskDataAdapter = taskDataAdapter;
        mBoardDataAdapter = boardDataAdapter;
        mContext = context;
        Timber.tag(this.getClass().getName());
    }

    public Observable<Boolean> seedData() {

        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                List<Task> taskList = new ArrayList<>();
                List<TaskBoard> taskBoards = null;
                try {
                    taskBoards = JsonParser.this.createTaskBoard(getJsonStringFromUrl("https://www.mockaroo.com/5c82d660/download?count=5&key=327934b0"));
                    createTaskList(getJsonStringFromUrl("https://www.mockaroo.com/fe011ff0/download?count=500&key=327934b0"), taskList);
                } catch (JSONException e) {
                    Timber.e(e.getMessage());
                    subscriber.onError(e);
                } catch (ParseException e) {
                    Timber.e(e.getMessage());
                    subscriber.onError(e);
                }
                mBoardDataAdapter.create(taskBoards);
                mTaskDataAdapter.create(taskList);
                subscriber.onCompleted();
                subscriber.onNext(true);
            }

        });

    }

    private List<TaskBoard> createTaskBoard(String jsonStringFromUrl) throws JSONException, ParseException {
        List<TaskBoard> taskBoards = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonStringFromUrl);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Timber.i("Converting json to object start.Json array length:- %d", jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            TaskBoard taskBoard = new TaskBoard();
            taskBoard.setTitle(jsonObject.getString("title"));
            taskBoard.setDescription(jsonObject.getString("description"));
            taskBoard.setCreateDate(dateFormat.parse(jsonObject.getString("created_date")).getTime());
            taskBoard.setStatus(Task.TaskStatus.valueOf(jsonObject.getString("status")).ordinal());
        }
        return taskBoards;
    }

    private void createTaskList(String jsonString, List<Task> taskList) throws JSONException, ParseException {

        JSONArray jsonArray = new JSONArray(jsonString);
        Timber.i("Converting json to object start.Json array length:- %d", jsonArray.length());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < jsonArray.length(); i++) {
            Task task = new Task();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String title = jsonObject.getString("title");
            String description = jsonObject.getString("description");
            String startdate = jsonObject.getString("start_date");
            String enddate = jsonObject.getString("complication_date");
            int progress = jsonObject.getInt("progress");
            String status = jsonObject.getString("status");
            int boradKey = jsonObject.getInt("taskboard_key");
            String type = jsonObject.getString("task_type");

            task.setTitle(title);
            task.setDescription(description);
            task.setStartDate(dateFormat.parse(startdate).getTime());
            task.setCompletionDate(dateFormat.parse(enddate).getTime());
            task.setProgress(progress + "");
            task.setTaskStatus(Task.TaskStatus.valueOf(status));
            task.setTaskBoardKey(boradKey);

            task.setTaskType(TaskType.valueOf(type).ordinal());
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

    private String getJsonStringFromUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int code = con.getResponseCode();
                if (code == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                }
            } catch (IOException e) {
                Timber.e(e.getMessage());
            }

        } catch (MalformedURLException e) {
            Timber.e(e.getMessage());
        }
        return null;
    }
}
