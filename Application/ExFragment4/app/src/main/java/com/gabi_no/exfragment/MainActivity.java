package com.gabi_no.exfragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText param1_edit, param2_edit;
    Button button_ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        param1_edit = (EditText) findViewById(R.id.param_edit_1);
        param2_edit = (EditText) findViewById(R.id.param_edit_2);
        button_ap = (Button) findViewById(R.id.button);

        button_ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String param1 = param1_edit.getText().toString();
                String param2 = param2_edit.getText().toString();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.contents,
                        BlankFragment.newInstance(param1,param2));
                fragmentTransaction.commit();
            }
        });
    }
}
