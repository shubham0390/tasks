package com.mmt.shubh.datastore.adapter;

import java.util.List;

/**
 * Created by shubham on 12/23/15.
 */
public interface IDataAdapter<M> {

    List<M> getAll();

    M getById(long id);

    long create(M m);

    long delete(M m);

    long delete(long id);

}
