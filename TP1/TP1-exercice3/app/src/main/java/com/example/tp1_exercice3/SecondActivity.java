package com.example.tp1_exercice3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    private TextView quest1, quest2;
    private Button quitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        quest1 = findViewById(R.id.quest1);
        quest2 = findViewById(R.id.quest2);
        quitter = findViewById(R.id.quitter);

        quest1.setText(this.getIntent().getStringExtra("quest1"));
        quest2.setText(this.getIntent().getStringExtra("quest2"));

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}