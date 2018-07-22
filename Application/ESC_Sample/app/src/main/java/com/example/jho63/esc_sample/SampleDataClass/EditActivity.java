package com.example.jho63.esc_sample.SampleDataClass;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jho63.esc_sample.R;


public class EditActivity extends AppCompatActivity {

    TextInputLayout Edit_NameInputLayout;
    EditText PhoneNum, E_Mail;
    ImageView ProfileImg;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //actionBar 객체를 가져올 수 있다.
        ActionBar actionBar = getSupportActionBar();

        //메뉴바에 '<' 버튼이 생긴다.(두개는 항상 같이다닌다)
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //메뉴바에 타이틀 지정
        actionBar.setTitle("Edit Profile");

        Edit_NameInputLayout = (TextInputLayout)findViewById(R.id.Edit_NameInputLayout);
        PhoneNum = (EditText)findViewById(R.id.Edit_phoneNum);
        E_Mail = (EditText)findViewById(R.id.Edit_email);
        ProfileImg = (ImageView)findViewById(R.id.Edit_Img);

        Edit_NameInputLayout.getEditText().setText(DummyData.dummyList.get(DummyData.getPage()).getName());
        PhoneNum.setText(DummyData.dummyList.get(DummyData.getPage()).getPhoneNum());
        E_Mail.setText(DummyData.dummyList.get(DummyData.getPage()).getE_Mail());
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
                String entered_email = E_Mail.getText().toString();
                String entered_tel = PhoneNum.getText().toString();
                String entered_name = Edit_NameInputLayout.getEditText().getText().toString();

                DummyData.dummyList.get(DummyData.getPage()).setName(entered_name);
                DummyData.dummyList.get(DummyData.getPage()).setPhoneNum(entered_tel);
                DummyData.dummyList.get(DummyData.getPage()).setE_Mail(entered_email);

                //TODO: 이미지 설정 기능 추가

                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
