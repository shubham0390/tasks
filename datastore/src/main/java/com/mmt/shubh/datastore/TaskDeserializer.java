package com.mmt.shubh.datastore;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mmt.shubh.datastore.model.ModelFactory;
import com.mmt.shubh.datastore.model.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import timber.log.Timber;

public class TaskDeserializer extends JsonDeserializer<Task> {

    private static final long serialVersionUID = -2275951539867772400L;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TaskDeserializer() {
        Timber.tag(getClass().getName());
    }

    @Override
    public Task deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        Task task = ModelFactory.getNewTask();
        task.setId(node.get(0).intValue());
        task.setTitle(node.get(1).asText());//titile
        task.setDescription(node.get(2).asText());// description
        try {
            task.setCreationDate(sdf.parse(node.get(3).asText()).getTime());//creae date
            task.setStartDate(sdf.parse(node.get(4).asText()).getTime());//start date
            task.setCompletion(sdf.parse(node.get(8).asText()).getTime());//complition date
        } catch (ParseException e) {
            Timber.e(e.getMessage());
        }

        task.setTaskStatus(Task.TaskStatus.valueOf(node.get(5).asText()));//status
        task.setProgress(String.valueOf(node.get(6).asInt()));//progress
        task.setTaskBorardKey(node.get(7).asInt());//taskboard key

        return task;
    }
}