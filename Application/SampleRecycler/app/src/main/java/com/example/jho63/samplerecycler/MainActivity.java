package com.example.jho63.samplerecycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button linear_btn, grid_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear_btn = findViewById(R.id.main_Linear_btn);
        grid_btn = findViewById(R.id.main_Grid_btn);

        linear_btn.setOnClickListener(this);
        grid_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_Linear_btn:
                Intent i = new Intent(this, LinearTypeActivity.class);
                startActivity(i);
            case R.id.main_Grid_btn:
                Intent i2 = new Intent(this, GridTypeActivity.class);
                startActivity(i2);
        }
    }
}
