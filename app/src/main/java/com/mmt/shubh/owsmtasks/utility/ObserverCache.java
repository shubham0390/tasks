package com.mmt.shubh.owsmtasks.utility;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;

public class ObserverCache {

    private static final ObserverCache OBSERVER_CACHE = new ObserverCache();

    public static ObserverCache getInstance() {
        return OBSERVER_CACHE;
    }

    Map<String, Observable> map = new ConcurrentHashMap<>();

    public void addObserver(String tag, Observable observable) {
        map.put(tag, observable);
    }

    public Observable getObservable(String tag) {
        return map.get(tag);
    }

    public void remove(String tag) {
        map.remove(tag);
    }
}
