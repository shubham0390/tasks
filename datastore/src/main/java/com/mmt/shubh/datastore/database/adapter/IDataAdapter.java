package com.mmt.shubh.datastore.database.adapter;

import com.mmt.shubh.datastore.model.IModel;

import java.util.List;

import rx.Observable;

/**
 * Created by shubham on 12/23/15.
 */
public interface IDataAdapter<M extends IModel> {

    Observable<List<M>> getAll();

    M getById(long id);

    Observable<M> create(M m);

    Observable<Long> delete(M m);

    Observable<Long> delete(long id);

}
