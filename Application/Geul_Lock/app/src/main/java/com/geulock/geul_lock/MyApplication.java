package com.geulock.geul_lock;

import android.app.Application;

import com.geulock.geul_lock.data.SearchHistories;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sihwan on 2017. 12. 14..
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 렘 초기화. 앱 시작할 때만 로드합니다.
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().initialData(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(SearchHistories.class);
            }
        }).build();
        // Realm 을 앱 재시작시마다 삭제.
        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
