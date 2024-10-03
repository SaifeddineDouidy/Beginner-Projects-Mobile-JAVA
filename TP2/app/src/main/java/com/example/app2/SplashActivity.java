package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    private ImageView splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        splashImage = findViewById(R.id.splashImage);
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360, // Start and end angles
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot Y
        );
        rotateAnimation.setDuration(2000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        splashImage.startAnimation(rotateAnimation);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(SplashActivity.this, ListPizzaActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }}