package com.example.jho63.esc_phonebook;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(R.anim.spalsh_start,0);

        ConstraintLayout background = (ConstraintLayout) findViewById(R.id.SplashBackground);
        final Animation splashEnd = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash_finish);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                ImageView splashImg = (ImageView) findViewById(R.id.SplashImage);
                splashImg.startAnimation(splashEnd);
                startActivity(intent);
                finish();
            }
        });
    }
}
