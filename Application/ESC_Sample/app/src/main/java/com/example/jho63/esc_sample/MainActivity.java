package com.example.jho63.esc_sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView EnterdNum;
    Button btn[];
    Button btn_Del, btn_Call, btn_Add, btn_Contact, btn_Sharp, btn_Plus, btn_Star;

    /**
     * 배열로 버튼을 이어주기 위해 필요한, string 으로 입력된 리소스아이디명을 id값으로 바꿔주는 함수.
     * 궁금하시면 보시고, 안보셔도 무난합니다.
     */
    public int getResourceID( final String resName, final String resType, final Context ctx )
    {
        final int ResourceID =
                ctx.getResources().getIdentifier( resName, resType, ctx.getApplicationInfo().packageName );
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException( "No resource string found with name " + resName );
        }
        else
        {
            return ResourceID;
        }
    }
    /**
     * 01098908717 과 같이 입력되는 형태를 010-9890-8717 로 입력되게 해주는 함수.
     * +, #, * 이 들어가는 경우 - 없애주는 기능 또한 함.
     * 간단한 방법으로 구현했으니 한번 봐보시면 좋습니다.
     * 참고) setText(문자열 값), getText(문자열 값) 함수는 TextView 객체 내부에 정의되어있는 텍스트 설정, 텍스트 가져오기 함수입니다.
     */
    public String changeToDialFormat(String rawStr) {

        while(rawStr.contains("-")){
            rawStr = rawStr.substring(0,rawStr.indexOf("-")) + rawStr.substring(rawStr.indexOf("-")+1);
        }

        if( !(rawStr.contains("*") || rawStr.contains("#") || rawStr.startsWith("+") || rawStr.length() > 11) ){
            if(rawStr.length() >= 4 && rawStr.charAt(3) != '-') {
                rawStr = rawStr.substring(0, 3) + "-" + rawStr.substring(3);
            }
            if(rawStr.length() >= 9 && rawStr.charAt(8) != '-') {
                rawStr = rawStr.substring(0, 8) + "-" + rawStr.substring(8);
            }
        }

        return rawStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= new Button[10];
        for(int i = 0; i < 10; i++){
            btn[i] = (Button) findViewById(getResourceID("btn"+i,"id", this));
        }
        btn_Del = (Button) findViewById(R.id.btnDel);
        btn_Call = (Button) findViewById(R.id.btnCall);
        btn_Add = (Button) findViewById(R.id.btnAdd);
        btn_Contact = (Button) findViewById(R.id.btnContact);
        btn_Sharp = (Button) findViewById(R.id.btnSharp);
        btn_Plus = (Button) findViewById(R.id.btnPlus);
        btn_Star = (Button) findViewById(R.id.btnStar);

        EnterdNum = (TextView) findViewById(R.id.EnteredNum);
        EnterdNum.setText("");

        for(int i = 0; i < 10; i++){
            final int finalI = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EnterdNum.setText(changeToDialFormat(EnterdNum.getText().toString() + finalI));
                }
            });
        }

        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterdNum.setText(changeToDialFormat(EnterdNum.getText().toString() + "+"));
            }
        });

        btn_Sharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterdNum.setText(changeToDialFormat(EnterdNum.getText().toString() + "#"));
            }
        });

        btn_Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterdNum.setText(changeToDialFormat(EnterdNum.getText().toString() + "*"));
            }
        });

        btn_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = EnterdNum.getText().toString();
                if(prevText.length() > 0) {
                    prevText = prevText.substring(0, prevText.length() - 1);

                    if (prevText.length() >= 1) {
                        if (prevText.substring(prevText.length() - 1).equals("-")) {
                            prevText = prevText.substring(0, prevText.length() - 1);
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this, "There is no Element to Delete!", Toast.LENGTH_SHORT).show();
                }
                EnterdNum.setText(changeToDialFormat(prevText));
            }
        });

        btn_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
