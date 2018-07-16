package com.example.jho63.esc_sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

        

    }
}
