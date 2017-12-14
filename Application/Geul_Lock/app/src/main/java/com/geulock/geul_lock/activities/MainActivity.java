package com.geulock.geul_lock.activities;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

    private class TouchHelperCallback extends ItemTouchHelper.SimpleCallback {
        TouchHelperCallback() {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            ShHelper.deleteItemAsync(realm, viewHolder.getItemId());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }

    private void setUpRecyclerView() {
        adtSearchHistories = new SearchHistoriesAdapter(realm.where(SearchHistories.class).findFirst().getShList());
        // RecyclerView 어댑터 설정
        rcvSearchHistories.setAdapter(adtSearchHistories);
        // RecyclerView 레이아웃매니저 설정 (마지막 아이템을 먼저 보이도록)
        lm.setStackFromEnd(true);
        rcvSearchHistories.setLayoutManager(lm);
        // RecyclerView 화면 크기 고정 설정
        rcvSearchHistories.setHasFixedSize(true);

        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(rcvSearchHistories);
    }

}
