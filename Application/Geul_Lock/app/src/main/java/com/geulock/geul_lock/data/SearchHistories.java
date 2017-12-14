package com.geulock.geul_lock.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sihwan on 2017. 12. 14..
 */

public class SearchHistories extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<SearchHistory> shList;

    public RealmList<SearchHistory> getShList() {
        return shList;
    }
}
