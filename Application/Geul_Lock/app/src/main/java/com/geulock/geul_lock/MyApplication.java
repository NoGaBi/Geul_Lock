package com.geulock.geul_lock;

import android.app.Application;
import android.util.Log;

import com.geulock.geul_lock.data.SearchHistories;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sihwan on 2017. 12. 14..
 */

public class MyApplication extends Application {

    /*
     * 앱 시작할 때만 로드합니다.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // 렘 초기화.
        Realm.init(this);
        /*
            RealmConfiguration 은 특정 Realm instance 를 setup 해줄 때 사용합니다.
            execute 메소드 안에서 원하는 setup을 시행합니다.
            initialData 는 초기에 realm database를 file 초기화해서 활성화할 때만 호출됩니다.
            즉, deleteRealm 함수를 호출해서 config를 지우지 않는 이상 다시 불리는 일은 없습니다.
        */
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().initialData(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(SearchHistories.class);
            }
        }).build();
        // 활성화하면 Realm 을 앱 재시작시마다 realm 설정 삭제.
        // Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
