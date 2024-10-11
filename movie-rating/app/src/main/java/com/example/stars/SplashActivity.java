package com.example.stars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

        logo.animate().rotation(360f).setDuration(1000)
                .withEndAction(() -> logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(500)
                        .withEndAction(() -> logo.animate()
                                .translationYBy(1000f)
                                .alpha(0f)
                                .setDuration(1000)
                                .withEndAction(() -> {
                                    Log.d("SplashActivity", "Transitioning to ListActivity");
                                    Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                                    startActivity(intent);
                                    finish();
                                })));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
