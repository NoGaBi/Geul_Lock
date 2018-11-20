package com.ebookfrenzy.exbroadcast5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter myIF = new IntentFilter("com.ebookfrenzy.exbroadcast5.MyBroadcast");
        myIF.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(myReceiver, myIF);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMethod(View v) {
        Intent i = new Intent("com.ebookfrenzy.exbroadcast5.MyBroadcast");
        i.setPackage(this.getPackageName());
        sendBroadcast(i);
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String info="Something is wrong";
            int mStatus = 0;

            if(intent.getAction().equals("com.ebookfrenzy.exbroadcast5.MyBroadcast")){
                info = "Main Broadcast Received!";
                mStatus = 1;
            }else if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
                info = "Airplane Mode Changed!";
                mStatus = 2;
            }

            Toast.makeText(context, mStatus + "\n" + info,Toast.LENGTH_SHORT).show();
        }
    }
}
