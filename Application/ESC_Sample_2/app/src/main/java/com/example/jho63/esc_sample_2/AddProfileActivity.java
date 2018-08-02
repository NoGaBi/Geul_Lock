package com.example.jho63.esc_sample_2;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class AddProfileActivity extends AppCompatActivity {

    EditText add_Email, add_Phone;
    TextInputLayout add_Name;
    ImageView add_ProfileImg;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        add_Name = (TextInputLayout)findViewById(R.id.add_Name);
        add_Email = (EditText)findViewById(R.id.add_email);
        add_Phone = (EditText)findViewById(R.id.add_phoneNum);
        add_ProfileImg = (ImageView)findViewById(R.id.add_Img);

        Intent intentOfThis = new Intent(this.getIntent());
        String tel = intentOfThis.getStringExtra(DialActivity.KEY_OF_TEL);
        add_Phone.setText(tel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.saveData:
                DummyData.dummyList.add(new Person(
                        add_Name.getEditText().getText().toString()
                        ,add_Phone.getText().toString()
                        ,add_Email.getText().toString()
                ));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
