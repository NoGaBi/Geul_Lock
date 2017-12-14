package com.geulock.geul_lock.activities;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geulock.geul_lock.Fonts;
import com.geulock.geul_lock.R;
import com.geulock.geul_lock.adapters.SearchHistoriesAdapter;
import com.geulock.geul_lock.data.SearchHistories;
import com.geulock.geul_lock.data.ShHelper;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Views
    private Button btnSend;     // 태그를 검색.
    private EditText etTag;    // 태그 검색어를 입력.
    private RecyclerView rcvSearchHistories;    // 검색 기록.

    // Adapters
    private SearchHistoriesAdapter adtSearchHistories;

    // LayoutManagers
    private LinearLayoutManager lm;

    // Realm
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        //--------------------------- 초기화 ----------------------------//

        btnSend = (Button) findViewById( R.id.btn_send );
        etTag = (EditText) findViewById( R.id.et_tag);
        rcvSearchHistories = (RecyclerView) findViewById(R.id.rcv_search_histories);
        lm = new LinearLayoutManager(this);
        realm = Realm.getDefaultInstance();

        //--------------------------- 기능 부여 ----------------------------//

        // 폰트 설정
        btnSend.setTypeface(Fonts.getBradhitc(this));
        etTag.setTypeface(Fonts.getMn(this));
        // 텍스트 굵게 설정
        btnSend.setPaintFlags(btnSend.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        // 클릭 리스너
        btnSend.setOnClickListener(this);
        // RecyclerView 세팅
        setUpRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rcvSearchHistories.setAdapter(null);
        realm.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 태그 검색 버튼 클릭 시
            case R.id.btn_send:
                ShHelper.addItemAsync(realm, etTag.getText().toString());
                break;
        }
    }

    /**
     * ItemTouchHelper 콜백 기능을 담당하는 클래스입니다.
     * 이 기능은 RecyclerView 에 swipe 했을 때의 기능을 부여해주기 위해 필요합니다.
     */
    private class TouchHelperCallback extends ItemTouchHelper.SimpleCallback {
        TouchHelperCallback() {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }


        /*
        * RecyclerView 에서 아이템을 움직일 때 drag&drop을 허용해주기 위함 이라고 하는데 아직 파악이 덜 됐습니다.
        * TODO: 이 메소드가 무엇인지 파악해야합니다.
        */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return true;
        }

        /*
        * RecyclerView 에서 swipe 했을시의 기능을 부여해주는 메소드입니다.
        */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            ShHelper.deleteItemAsync(realm, viewHolder.getItemId());
        }

        /*
        * 길게 클릭하여 드래그하는 것을 허용해주는 메소드입니다.
        */
        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }


    /*
     * RecyclerView 를 setup합니다.
     * realm 어댑터를 초기화하고 연결해주는 작업이 주입니다.
     */
    private void setUpRecyclerView() {
        // 어댑터 초기화
        adtSearchHistories = new SearchHistoriesAdapter(realm.where(SearchHistories.class).findFirst().getShList());
        Log.d("tag", realm.where(SearchHistories.class).count() + "");
        // RecyclerView 어댑터 설정
        rcvSearchHistories.setAdapter(adtSearchHistories);
        // RecyclerView 레이아웃매니저 설정 (마지막 아이템을 먼저 보이도록)
        lm.setStackFromEnd(true);
        rcvSearchHistories.setLayoutManager(lm);
        // RecyclerView 화면 크기 고정 설정
        rcvSearchHistories.setHasFixedSize(true);

        // TouchHelper 를 설정
        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(rcvSearchHistories);
    }

}
