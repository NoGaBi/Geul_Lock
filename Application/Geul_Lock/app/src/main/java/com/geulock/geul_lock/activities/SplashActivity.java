package com.geulock.geul_lock.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.geulock.geul_lock.Fonts;
import com.geulock.geul_lock.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition( R.anim.startanim, R.anim.emptyanim );
        super.onCreate(savedInstanceState);



//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        TextView txt1 = (TextView) findViewById(R.id.textView);
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        TextView txt3 = (TextView) findViewById(R.id.textView3);

        txt1.setTypeface(Fonts.getMn(this));
        txt2.setTypeface(Fonts.getMn(this));
        txt3.setTypeface(Fonts.getMn(this));

        txt2.setPaintFlags(txt2.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        txt3.setPaintFlags(txt3.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.alpha);

        SystemClock.sleep(500);
        txt1.startAnimation(animAlpha);
        txt2.startAnimation(animAlpha);
        txt3.startAnimation(animAlpha);


        ConstraintLayout background = (ConstraintLayout) findViewById(R.id.StartBackground);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity( intent );
                overridePendingTransition( R.anim.slideinanim, R.anim.slideoutanim );
                finish();
            }
        });

    }
}
