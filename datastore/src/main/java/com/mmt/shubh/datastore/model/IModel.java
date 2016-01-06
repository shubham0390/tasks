package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shubham on 12/23/15.
 */
public interface IModel<M> {
    ContentValues toContentValue();

    M parseCursor(Cursor cursor);
}
