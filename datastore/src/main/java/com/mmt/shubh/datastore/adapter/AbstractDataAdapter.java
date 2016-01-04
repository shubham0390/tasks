package com.mmt.shubh.datastore.adapter;

import android.content.Context;

import com.mmt.shubh.datastore.database.DatabaseOpenHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

/**
 * Created by shubham on 12/30/15.
 */
public abstract class AbstractDataAdapter<M> implements IDataAdapter<M> {

    protected BriteDatabase mDatabase;

    public AbstractDataAdapter(Context context) {
        mDatabase = SqlBrite.create().wrapDatabaseHelper(new DatabaseOpenHelper(context));
    }

    public List<M> getAll() {
        return null;
    }

    public M getById(long id) {
        return null;
    }

    public long create(M m) {
        return 0;
    }

    public long delete(M m) {
        return 0;
    }

    public long delete(long id) {
        return 0;
    }
}
