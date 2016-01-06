package com.mmt.shubh.datastore.adapter;


import android.database.Cursor;

import com.mmt.shubh.datastore.database.QueryBuilder;
import com.mmt.shubh.datastore.database.TaskContract;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;

/**
 * Created by shubham on 12/30/15.
 */
public abstract class AbstractDataAdapter<M> implements IDataAdapter<M> {

    protected final BriteDatabase mDatabase;
    protected final String TABLE_NAME ;

    public AbstractDataAdapter(BriteDatabase database, String tableName) {
        mDatabase = database;
        TABLE_NAME = tableName;
    }

    public Observable<List<M>> getAll() {

        String query = QueryBuilder.createSelectQuery(TABLE_NAME, TaskContract.TASK_PROJECTION);

        return mDatabase.createQuery(TABLE_NAME, query).mapToList(this::parseCursor);
    }

    protected abstract M parseCursor(Cursor cursor);

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
