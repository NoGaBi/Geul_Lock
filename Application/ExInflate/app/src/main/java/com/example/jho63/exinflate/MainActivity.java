package com.example.jho63.exinflate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_adShow;
    Button btn_adopt;
    LinearLayout contentView;
    LayoutInflater inflater;
    ImageView adImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_adShow = (CheckBox) findViewById(R.id.cb_adShow);
        btn_adopt = (Button) findViewById(R.id.btn_adopt);
        contentView = (LinearLayout) findViewById(R.id.contentView);
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        btn_adopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_adShow.isChecked()){
                    contentView.removeAllViews();
                    inflater.inflate(R.layout.layout_ad, contentView, true);
                    adImageView = (ImageView)findViewById(R.id.adImage);

                    adImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "광고입니다!", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    contentView.removeAllViews();
                    inflater.inflate(R.layout.layout_content, contentView, true);

                }
            }
        });

    }
}
