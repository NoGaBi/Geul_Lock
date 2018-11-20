package com.ebookfrenzy.exbroadcast2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    MyBroadcastReceiver mReceiver = new MyBroadcastReceiver();
    String TAG = "MAIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /* register the receiver (declared below) to get the information charging and batter */
    @Override
    public void onResume() {
        super.onResume();
        //the one below registers a global receiver.
        IntentFilter myIF = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        myIF.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        myIF.addAction("android.intent.action.BATTERY_LOW");
        myIF.addAction("com.ebookfrenzy.exbroadcast2.gogo");
        registerReceiver(mReceiver, myIF);
        Log.v(TAG, "receiver should be registered");
    }
    @Override
    public void onPause() {  //or onDestory()
        // Unregister since the activity is not visible.

        unregisterReceiver(mReceiver);
        Log.v(TAG, "receiver should be unregistered");
        super.onPause();

    }
    public void clickMethod(View v){
        Intent i = new Intent("com.ebookfrenzy.exbroadcast2.gogo");
        i.setPackage("com.ebookfrenzy.exbroadcast2");
        //in API 26, it must be explicit now.
        //since it's registered as a global (in the manifest), use sendBroadCast
        //LocalBroadcastManager.getInstance(getContext()).sendBroadcast(i);
        sendBroadcast(i);
    }



    public class MyBroadcastReceiver extends BroadcastReceiver {
        public MyBroadcastReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("myReceiver", "received an intent");
            String info = "\nsomething wrong.";
            int mStatus = 0;
            if (intent.getAction().equals("com.ebookfrenzy.exbroadcast2.gogo")) {  //battery is low!
                info = "\nMain activity Broadcast received.";
                mStatus = 1;
                Log.v("myReceiver", "own broadcast");
            }else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {  //battery is charging.
                info = "\nPower connected, so phone is charging.";
                mStatus = 2;
                Log.v("myReceiver", "ac on");
            } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {  //power has been disconnected.
                info = "\nPower disconnected.";
                mStatus = 3;
                Log.v("myReceiver", "ac off");
            }
            Toast.makeText(context,"Status: " + mStatus + info , Toast.LENGTH_LONG).show();
        }
    }
}
