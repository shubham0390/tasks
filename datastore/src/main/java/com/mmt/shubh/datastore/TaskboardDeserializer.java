package com.mmt.shubh.datastore;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mmt.shubh.datastore.model.ModelFactory;
import com.mmt.shubh.datastore.model.TaskBoard;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import timber.log.Timber;

public class TaskboardDeserializer extends JsonDeserializer<TaskBoard> {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public TaskboardDeserializer() {
        Timber.tag(getClass().getName());
    }

    @Override
    public TaskBoard deserialize(com.fasterxml.jackson.core.JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TaskBoard taskBoard = ModelFactory.getNewTaskBoard();
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        taskBoard.setId(node.get(0).intValue());
        taskBoard.setTitle(node.get(1).asText());//titile
        taskBoard.setDescription(node.get(2).asText());// description
        try {
            taskBoard.setCreateDate(sdf.parse(node.get(3).asText()).getTime());//creae date
            taskBoard.setStartDate(sdf.parse(node.get(4).asText()).getTime());//start date
        } catch (ParseException e) {
           Timber.e(e.getMessage());
        }
        taskBoard.setStatus(node.get(5).asText());
        taskBoard.setProgress(String.valueOf(node.get(6).asInt()));//progress
        return taskBoard;
    }
}