package com.geulock.geul_lock.activities;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geulock.geul_lock.Fonts;
import com.geulock.geul_lock.R;
import com.geulock.geul_lock.adapters.SearchHistoriesAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Views
    private Button btnSend;     // 태그를 검색.
    private EditText etTag;    // 태그 검색어를 입력.
    private RecyclerView rcvSearchHistories;    // 검색 기록.

    // Adapters
    private SearchHistoriesAdapter adtSearchHistories;

    // LayoutManagers
    private LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        //--------------------------- 초기화 ----------------------------//

        btnSend = (Button) findViewById( R.id.btn_send );
        etTag = (EditText) findViewById( R.id.et_tag);
        rcvSearchHistories = (RecyclerView) findViewById(R.id.rcv_search_histories);
        adtSearchHistories = new SearchHistoriesAdapter();
        lm = new LinearLayoutManager(this);

        //--------------------------- 기능 부여 ----------------------------//

        // 폰트 설정
        btnSend.setTypeface(Fonts.getBradhitc(this));
        etTag.setTypeface(Fonts.getMn(this));
        // 텍스트 굵게 설정
        btnSend.setPaintFlags(btnSend.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        // 클릭 리스너
        btnSend.setOnClickListener(this);
        // RecyclerView 어댑터 설정
        rcvSearchHistories.setAdapter(adtSearchHistories);
        // RecyclerView 레이아웃매니저 설정
        lm.setStackFromEnd(true);
        rcvSearchHistories.setLayoutManager(lm);
        // RecyclerView 화면 크기 고정 설정
        rcvSearchHistories.setHasFixedSize(true);
        // 마지막 아이템을 먼저 보이도록 설정
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 태그 검색 버튼 클릭 시
            case R.id.btn_send:

                break;
        }
    }
}
