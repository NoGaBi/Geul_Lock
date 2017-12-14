package com.geulock.geul_lock.data;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sihwan on 2017. 12. 14..
 */

public class SearchHistory extends RealmObject {

    public static final String FIELD_ID = "id";

    @PrimaryKey
    private int id;
    private String message;

    // Thread-safe 기능을 부여해주는 Atomic 클래스를 이용.
    private static AtomicInteger INTEGER_COUNTER = new AtomicInteger(0);

    private static int increment() {
        return INTEGER_COUNTER.getAndIncrement();
    }

    public static void setIntegerCounter(int value) {
        INTEGER_COUNTER.set(value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountString() {
        return Integer.toString(id);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void create(Realm realm, String message) {
        SearchHistories shs = realm.where(SearchHistories.class).findFirst();
        RealmList<SearchHistory> shList = shs.getShList();
        SearchHistory item = realm.createObject(SearchHistory.class, increment());
        item.setMessage(message);
        shList.add(item);
    }

    public static void delete(Realm realm, long id) {
        SearchHistory searchHistory = realm.where(SearchHistory.class).equalTo(FIELD_ID, id).findFirst();
        if (searchHistory != null)
            searchHistory.deleteFromRealm();
    }
}
