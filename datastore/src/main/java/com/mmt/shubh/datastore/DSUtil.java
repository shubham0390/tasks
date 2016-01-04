package com.mmt.shubh.datastore;

/**
 * Created by shubham on 12/31/15.
 */
public class DSUtil {


    public static boolean isArrayEmpty(Object[] array) {
        if (array == null || array.length <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isArrayEmpty(int[] array) {
        if (array == null || array.length <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isArrayEmpty(long[] array) {
        if (array == null || array.length <= 0) {
            return true;
        }
        return false;
    }
}
