package com.geulock.geul_lock;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main2 );

        Button send_btn = (Button) findViewById( R.id.send_btn );
        EditText Main2_intput_text = (EditText) findViewById( R.id.main2_editText);

        Typeface font = Typeface.createFromAsset(getAssets(), "BRADHITC.TTF");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "MN.TTF");
        send_btn.setTypeface(font);
        Main2_intput_text.setTypeface(font2);
        send_btn.setPaintFlags(send_btn.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

    }
}
