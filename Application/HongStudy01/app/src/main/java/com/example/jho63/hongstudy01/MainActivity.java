package com.example.jho63.hongstudy01;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactView;
    ImageButton addBtn, resetBtn;
    DBManager dbManager;
    ArrayList<Person> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        dataList = dbManager.selectAll();
        addBtn = findViewById(R.id.addBtn);
        resetBtn = findViewById(R.id.resetBtn);
        contactView = findViewById(R.id.ContactView);
        contactView.setLayoutManager(new LinearLayoutManager(this));
        contactView.setAdapter(new ContactAdapter(this, dataList));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                i.putExtra("menu","add");
                startActivityForResult(i,1);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                contactView.getAdapter().notifyDataSetChanged();
            }
        });



    }
    @Override
    protected void onResume(){
        super.onResume();
        dataList = dbManager.selectAll();
        contactView.setAdapter(new ContactAdapter(this, dataList));
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){
//            if(resultCode == Activity.RESULT_OK){
//                contactView.invalidate();
//                contactView.getAdapter().notifyDataSetChanged();
//            }
//        }
//    }
}
