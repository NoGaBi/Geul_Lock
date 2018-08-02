package com.example.jho63.esc_sample_2;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class EditActivity extends AppCompatActivity {

    TextInputLayout Edit_Name;
    EditText PhoneNum, E_mail;
    ImageView ProfileImg;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Edit Profile");
        }



        Edit_Name = (TextInputLayout)findViewById(R.id.edit_Name);
        PhoneNum = (EditText)findViewById(R.id.edit_phoneNum);
        E_mail = (EditText)findViewById(R.id.edit_email);
        ProfileImg = (ImageView)findViewById(R.id.edit_Img);

        Edit_Name.getEditText().setText(DummyData.dummyList
                .get(DummyData.getPage()).getName());
        PhoneNum.setText(DummyData.dummyList
                .get(DummyData.getPage()).getPhoneNum());
        E_mail.setText(DummyData.dummyList
                .get(DummyData.getPage()).getE_mail());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.saveData:
                DummyData.dummyList.get(DummyData.getPage())
                        .setName(Edit_Name.getEditText().getText().toString());
                DummyData.dummyList.get(DummyData.getPage())
                        .setPhoneNum(PhoneNum.getText().toString());
                DummyData.dummyList.get(DummyData.getPage())
                        .setE_mail(E_mail.getText().toString());

                //TODO: 이미지 편집 기능 추가
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
