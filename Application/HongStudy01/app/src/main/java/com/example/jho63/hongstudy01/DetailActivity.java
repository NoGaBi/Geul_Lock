package com.example.jho63.hongstudy01;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView name, phoneNum, eMail;
    ImageView detail_profile;
    ImageButton callBtn, msgBtn, emailBtn, editBtn, deleteBtn;
    String data_name, data_phoneNum, data_eMail;
    int index;
    DBManager dbManager;
    Person p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        index = getIntent().getIntExtra("cid", -1);
        name = findViewById(R.id.detail_Name);
        phoneNum = findViewById(R.id.detail_PhoneNum);
        eMail = findViewById(R.id.detail_Email);
        dbManager = new DBManager(this);
        p = dbManager.getData(index);
        data_name = p.getName();
        data_phoneNum = p.getPhoneNum();



        detail_profile = findViewById(R.id.detail_profile);

        data_name = dbManager.getData(index).getName();
        data_phoneNum = dbManager.getData(index).getPhoneNum();
        data_eMail = dbManager.getData(index).geteMail();

        name.setText(data_name);
        phoneNum.setText(data_phoneNum);
        eMail.setText(data_eMail);
        if(p.getProfileImg() != null) {
            Glide.with(this).load(p.getProfileImg()).into(detail_profile);
        }

        callBtn = findViewById(R.id.detail_callBtn);
        msgBtn = findViewById(R.id.detail_msgBtn);
        emailBtn = findViewById(R.id.detail_emailBtn);
        editBtn = findViewById(R.id.detail_editBtn);
        deleteBtn = findViewById(R.id.detail_deleteBtn);

        callBtn.setOnClickListener(this);
        msgBtn.setOnClickListener(this);
        emailBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_callBtn:
                Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + data_phoneNum));
                startActivity(callIntent);
                break;
            case R.id.detail_msgBtn:
                Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + data_phoneNum));
                startActivity(msgIntent);
                break;
            case R.id.detail_emailBtn:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + data_phoneNum));
                startActivity(intent);
                break;
            case R.id.detail_deleteBtn:
                dbManager.deleteData(p);
                Intent y = new Intent(this, MainActivity.class);
                setResult(Activity.RESULT_OK);
                startActivity(y);
                //finish();
                break;
            case R.id.detail_editBtn:
                Intent i = new Intent(DetailActivity.this, EditActivity.class);
                i.putExtra("cid", index);
                i.putExtra("menu", "edit");
                startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*data_name = Person.dummyList.get(index).getName();
        data_phoneNum = Person.dummyList.get(index).getPhoneNum();
        data_eMail = Person.dummyList.get(index).geteMail();*/
        Person p = dbManager.getData(index+1);
        data_name = p.getName();
        data_phoneNum = p.getPhoneNum();
        data_eMail = p.geteMail();


        name.setText(data_name);
        phoneNum.setText(data_phoneNum);
        eMail.setText(data_eMail);
        if (p.getProfileImg() != null)
            Glide.with(this).load(p.getProfileImg()).into(detail_profile);
    }
}
