package com.mmt.shubh.taskmanager;

import android.content.Context;
import android.content.res.AssetManager;

import com.mmt.shubh.taskmanager.tasks.Task;
import com.mmt.shubh.taskmanager.db.TaskContract;
import com.mmt.shubh.taskmanager.db.TaskDataAdapter;

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

/**
 * Created by Subham Tyagi,
 * on 05/Aug/2015,
 * 3:33 PM
 * TODO:Add class comment.
 */
public class JsonParser {


    public void seedData(Context context) {
        String jsonString = readFromFile(context);
        List<Task> taskList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                Task task = new Task();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title = jsonObject.getString(TaskContract.TaskColumn.TASK_TITLE);
                String enddate = jsonObject.getString(TaskContract.TaskColumn.END_DATE);
                String startdate = jsonObject.getString(TaskContract.TaskColumn.START_DATE);
                String description = jsonObject.getString(TaskContract.TaskColumn.TASK_DESCRIPTION);
                String status = jsonObject.getString(TaskContract.TaskColumn.TASK_STATUS);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                task.setEndDate(dateFormat.parse(enddate).getTime());
                task.setStartDate(dateFormat.parse(startdate).getTime());
                task.setTitle(title);
                task.setTaskStatus(TaskStatus.valueOf(status));
                task.setDescription(description);
                taskList.add(task);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TaskDataAdapter adapter = TaskDataAdapter.getInstance(context);
        adapter.addTaskListToDatabase(taskList);
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
            e.getMessage();
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
        return returnString.toString();
    }
}
