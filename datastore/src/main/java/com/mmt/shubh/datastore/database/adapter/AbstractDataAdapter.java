package com.mmt.shubh.datastore.database.adapter;


import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;

import com.mmt.shubh.database.QueryBuilder;
import com.mmt.shubh.database.Selection;
import com.mmt.shubh.datastore.model.IModel;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by shubham on 12/30/15.
 */
public abstract class AbstractDataAdapter<M extends IModel> implements IDataAdapter<M> {

    protected final BriteDatabase mDatabase;
    protected final String TABLE_NAME;

    public AbstractDataAdapter(BriteDatabase database, String tableName) {
        mDatabase = database;
        TABLE_NAME = tableName;
        Timber.tag(getClass().getName());
    }

    public Observable<List<M>> getAll() {

        String query = new QueryBuilder().addFrom(TABLE_NAME).build();

        return mDatabase.createQuery(TABLE_NAME, query).mapToList(this::parseCursor);
    }

    protected abstract M parseCursor(Cursor cursor);

    public M getById(long id) {
        return null;
    }

    public Observable<M> create(M data) {
        return Observable.create((subscriber -> {
            BriteDatabase.Transaction transaction = mDatabase.newTransaction();
            try {
                insert(data, subscriber);
                transaction.markSuccessful();
                subscriber.onCompleted();
            } catch (SQLiteConstraintException e) {
                subscriber.onError(e);
            } finally {
                transaction.end();
            }
        }
        ));
    }

    private void insert(M data, Subscriber<? super M> subscriber) {
        long result = mDatabase.insert(TABLE_NAME, data.toContentValue());
        if (result > 0) {
            data.setId(result);
            subscriber.onNext(data);
        } else {
            subscriber.onError(new Exception("Unable to insert data"));
        }
    }

    public Observable<Long> delete(M m) {
        return Observable.create(subscriber -> {

        });
    }

    public Observable<Long> delete(long id) {
        return Observable.create((subscriber -> {

        }));
    }

    public Observable<M> addDataList(List<M> taskList) {
        Timber.i("Adding task to database with size %d", taskList.size());
        return Observable.create((subscriber -> {
            BriteDatabase.Transaction transaction = mDatabase.newTransaction();
            try {
                for (M data : taskList) {
                    AbstractDataAdapter.this.insert(data, subscriber);
                }
                transaction.markSuccessful();
                subscriber.onCompleted();
            } catch (SQLiteConstraintException e) {
                subscriber.onError(e);
            } finally {
                transaction.end();
            }
        }
        ));
    }

    public Observable<List<M>> getData(String columnName, String columnValue) {
        QueryBuilder queryBuilder = new QueryBuilder();
        Selection selection = new Selection(columnName, Selection.EQUAL, columnValue);
        queryBuilder.addFrom(TABLE_NAME)
                .addSelection(selection);
        return mDatabase.createQuery(TABLE_NAME, queryBuilder.build()).mapToList(this::parseCursor);
    }

}
