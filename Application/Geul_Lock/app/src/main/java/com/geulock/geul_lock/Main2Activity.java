package com.geulock.geul_lock;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition( R.anim.slideinanim, R.anim.endanim );
        setContentView(R.layout.activity_main2);
        mRecyclerView = (RecyclerView) findViewById(R.id.msg_recyclerview);
        Button send_btn = (Button) findViewById(R.id.send_btn);

        Typeface font = Typeface.createFromAsset(getAssets(), "BRADHITC.TTF");
        send_btn.setTypeface(font);
        send_btn.setPaintFlags(send_btn.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
