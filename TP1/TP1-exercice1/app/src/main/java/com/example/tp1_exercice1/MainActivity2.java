package com.example.tp1_exercice1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity2 extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textView;
    private Button toastButton;
    private Button incrementButton;
    private Button decrementButton;
    private Button resetButton;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        textView = findViewById(R.id.textView);
        toastButton = findViewById(R.id.toastButton);
        incrementButton = findViewById(R.id.incrementButton);
        decrementButton = findViewById(R.id.decrementButton);
        resetButton = findViewById(R.id.resetButton);

        toastButton.setOnClickListener(v -> {
            Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT).show();
        });

        textView.setText(String.valueOf(count));

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText(String.valueOf(count));
            }
        });
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                textView.setText(String.valueOf(count));
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                textView.setText(String.valueOf(count));
            }
        });

        setSupportActionBar(toolbar);
    }
}
