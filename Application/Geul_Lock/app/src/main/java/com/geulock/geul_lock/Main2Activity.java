package com.geulock.geul_lock;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition( R.anim.slideinanim, R.anim.endanim );
        setContentView(R.layout.activity_main2);

        Button send_btn = (Button) findViewById(R.id.send_btn);
        Typeface font = Typeface.createFromAsset(getAssets(), "BRADHITC.TTF");
        send_btn.setTypeface(font);
        send_btn.setPaintFlags(send_btn.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
    }
}
