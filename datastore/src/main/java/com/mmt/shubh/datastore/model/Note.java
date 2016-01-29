package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shubham on 12/23/15.
 */
public class Note implements IModel {

    @Override
    public ContentValues toContentValue() {
        return null;
    }

    @Override
    public void parseCursor(Cursor cursor) {
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }
}
