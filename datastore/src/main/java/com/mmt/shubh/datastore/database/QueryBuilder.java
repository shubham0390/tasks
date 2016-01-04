package com.mmt.shubh.datastore.database;

import android.text.TextUtils;

import com.mmt.shubh.datastore.DSUtil;

/**
 * Created by shubham on 12/30/15.
 */
public class QueryBuilder {

    static final String SELECT = "SELECT";
    static final String FROM = "FROM";
    static final String ALL = "*";
    static final String WHERE = "WHERE";
    static final String SPACE = "";
    static final String SORT_BY = "ORDER BY";
    static final String ASC = "ASC";
    static final String DESC = "DESC";


    static final char COMMA = ',';

    static final int SORT_TYPE_ASC = 0;
    static final int SORT_TYPE_DESC = 1;

    private static StringBuilder createQuery(String tableName, String[] projection) {
        StringBuilder sb = new StringBuilder();
        sb.append(SELECT);
        sb.append(SPACE);
        if (DSUtil.isArrayEmpty(projection)) {
            sb.append(ALL);
            sb.append(SPACE);
        } else {
            arrayToString(sb, projection);
            sb.append(SPACE);
        }
        sb.append(FROM);
        sb.append(SPACE);
        sb.append(tableName);
        return sb;
    }

    public static String createSelectQuery(String tableName, String[] projection) {
        StringBuilder sb = createQuery(tableName, projection);
        sb.append(SPACE);
        return sb.toString();
    }

    public static String createSelectQuery(String tableName, String[] projection, String[] selections) {
        StringBuilder sb = createQuery(tableName, projection);
        if (!DSUtil.isArrayEmpty(selections)) {
            sb.append(SPACE);
            sb.append(WHERE);
            sb.append(SPACE);
            arrayToString(sb, selections);
            sb.append(SPACE);
        }
        return sb.toString();
    }

    public static String createSelectQuery(String tableName, String[] projection, String[] selections, String sortColumn, int sortType) {
        StringBuilder sb = new StringBuilder(createSelectQuery(tableName, projection, selections));
        sb.append(SPACE);
        sb.append(SORT_BY);
        sb.append(SPACE);
        sb.append(sortColumn);
        sb.append(SPACE);
        sb.append(sortType == SORT_TYPE_ASC ? ASC : DESC);
        sb.append(SPACE);
        return sb.toString();
    }


    public static void arrayToString(StringBuilder sb, Object[] projection) {
        for (int i = 0; i < projection.length; i++) {
            sb.append(projection[i]);
            if (i < projection.length - 1)
                sb.append(COMMA);
        }
    }
}
