package com.example.jho63.hongstudy01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowText extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent i = getIntent();
        String value = i.getStringExtra("value");
        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(value);
    }
}
