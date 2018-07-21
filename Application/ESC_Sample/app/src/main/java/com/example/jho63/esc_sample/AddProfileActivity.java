package com.example.jho63.esc_sample;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.jho63.esc_sample.SampleDataClass.DummyData;
import com.example.jho63.esc_sample.SampleDataClass.Profile;

public class AddProfileActivity extends AppCompatActivity {

    EditText phoneNum, email;
    TextInputLayout nameInputLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        //actionBar 객체를 가져올 수 있다.
        ActionBar actionBar = getSupportActionBar();

        //메뉴바에 '<' 버튼이 생긴다.(두개는 항상 같이다닌다)
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //메뉴바에 타이틀 지정
        actionBar.setTitle("Add Profile");

        //intent안에 담아서 전달한 전화번호를 가져온다.
        Intent intentOfThis = new Intent(this.getIntent());
        String tel = intentOfThis.getStringExtra("tel");

        phoneNum = (EditText) findViewById(R.id.add_phoneNum);
        phoneNum.setText( tel );

        email = (EditText) findViewById(R.id.add_email);
        nameInputLayout = (TextInputLayout) findViewById(R.id.NameInputLayout);
    }

    //액션바 아이템 선택시 각각 수행할 작업에 대한 함수.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            //뒤로가기 버튼 선택시 현재 액티비티를 닫아줌으로써 스택에서 바로 앞 액티비티를 불러옴.
            case android.R.id.home:
                finish();
                return true;
            case R.id.saveData:
                String entered_email = email.getText().toString();
                String entered_tel = phoneNum.getText().toString();
                String entered_name = nameInputLayout.getEditText().getText().toString();
                //TODO: 데이터베이스 구축하던지 아니면 샘플 객체 만들어서 데이터 저장.

                Profile profile = new Profile
                        .Builder(entered_name, entered_tel, entered_email)
                        .build();

                DummyData.dummyList.add(profile);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
