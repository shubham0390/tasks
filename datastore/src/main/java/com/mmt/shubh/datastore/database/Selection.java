package com.mmt.shubh.datastore.database;

/**
 * Created by shubham on 12/31/15.
 */
public class Selection {

    public static final char EQUAL = '=';
    public static final String AND = "AND";
    public static final String OR = "OR";


    public static final String getSelection(String columnName, String operation, Object value) {
        return columnName + " " + operation + " " + value.toString();
    }

    public static final String getSelection(String columnName, char operation, String value) {
        return columnName + " " + operation + " " + value.toString();
    }

    public static final String getSelection(String columnName, String operation, Object[] value) {
        StringBuilder sb = new StringBuilder();
        sb.append(columnName);
        sb.append(QueryBuilder.SPACE);
        sb.append(operation);
        sb.append(" ( ");
        QueryBuilder.arrayToString(sb, value);
        sb.append(" ) ");
        return columnName + " " + operation + " " + value.toString();
    }
}
