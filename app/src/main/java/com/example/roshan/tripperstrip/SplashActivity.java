package com.example.roshan.tripperstrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image=findViewById(R.id.imageview);
        float bottomOfScreen = getResources().getDisplayMetrics()
                .heightPixels/4 - (image.getHeight() * 8);
        //bottomOfScreen is where you want to animate to

        image.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                .setInterpolator(new BounceInterpolator())
                .setDuration(3000);

        Thread t = new Thread(r);
        t.start();

    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent in = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(in);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        super.finish();
    }
}
