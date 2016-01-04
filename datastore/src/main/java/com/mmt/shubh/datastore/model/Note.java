package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shubham on 12/23/15.
 */
public class Note implements IModel<Note> {

    @Override
    public ContentValues toContentValue(Note note) {
        return null;
    }

    @Override
    public Note parseCursor(Cursor cursor) {
        return null;
    }
}
