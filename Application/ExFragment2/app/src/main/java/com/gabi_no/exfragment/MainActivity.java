package com.gabi_no.exfragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText param1Edit, param2Edit;
    Button ApButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        param1Edit = (EditText) findViewById(R.id.param1_edit);
        param2Edit = (EditText) findViewById(R.id.param2_edit);

        ApButton = (Button) findViewById(R.id.Adopt_btn);

        ApButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String param1 = param1Edit.getText().toString();
                String param2 = param2Edit.getText().toString();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contents, BlankFragment.newInstance(
                        "param1: "+param1,
                        "param2: "+param2));
                fragmentTransaction.commit();
            }
        });

    }
}
