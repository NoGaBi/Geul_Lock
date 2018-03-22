package com.example.jho63.hongstudy01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button btn;



    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view){
            textView = (TextView) findViewById(R.id.textView);
            editText = (EditText) findViewById(R.id.editText);

            switch (view.getId()){
                case R.id.button :
                    textView.setText(editText.getText().toString().trim());
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnOnClickListener onClickListener = new BtnOnClickListener();
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(onClickListener);
    }
}
