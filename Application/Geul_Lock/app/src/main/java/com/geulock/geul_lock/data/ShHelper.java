package com.geulock.geul_lock.data;

import java.util.Collection;

import io.realm.Realm;

/**
 * Created by sihwan on 2017. 12. 14..
 */

public class ShHelper {
    public static void addItemAsync(Realm realm, final String message) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SearchHistory.create(realm, message);
            }
        });
    }

    public static void deleteItemAsync(Realm realm, final long id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SearchHistory.delete(realm, id);
            }
        });
    }

    public static void deleteItemAsync(Realm realm, Collection<Integer> ids) {
        final Integer[] idsToDelete = new Integer[ids.size()];
        ids.toArray(idsToDelete);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(Integer id : idsToDelete) {
                    SearchHistory.delete(realm, id);
                }
            }
        });
    }
}
