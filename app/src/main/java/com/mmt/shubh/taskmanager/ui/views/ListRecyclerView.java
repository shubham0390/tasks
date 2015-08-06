package com.mmt.shubh.taskmanager.ui.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Subham Tyagi,
 * on 10/Jul/2015,
 * 11:53 AM
 * TODO:Add class comment.
 */
public class ListRecyclerView extends RecyclerView {

    private RecyclerTouchListener mRecyclerTouchListener;

    public ListRecyclerView(Context context) {
        super(context);
        init();
    }

    public ListRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mRecyclerTouchListener = new RecyclerTouchListener(this);
        this.addOnItemTouchListener(mRecyclerTouchListener);
    }

    public interface OnItemClickListener {
        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         * <p/>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param view     The view within the AdapterView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         * @param id       The row id of the item that was clicked.
         * @return true if the callback consumed the long click, false otherwise
         */
        boolean onItemClick(RecyclerView parent, View view, int position, long id);
    }

    public interface OnItemLongClickListener {

        /**
         * Callback method to be invoked when an item in this view has been
         * clicked and held.
         * <p/>
         * Implementers can call getItemAtPosition(position) if they need to access
         * the data associated with the selected item.
         *
         * @param view     The view within the AbsListView that was clicked
         * @param position The position of the view in the list
         * @param id       The row id of the item that was clicked
         * @return true if the callback consumed the long click, false otherwise
         */
        boolean onItemLongClick(RecyclerView parent, View view, int position, long id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mRecyclerTouchListener.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener){
        mRecyclerTouchListener.setOnItemLongClickListener(longClickListener);
    }
}