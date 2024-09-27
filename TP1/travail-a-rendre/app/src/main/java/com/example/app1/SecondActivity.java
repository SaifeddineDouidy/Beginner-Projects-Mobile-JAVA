package com.example.app1;

import static com.example.app1.R.*;

import android.annotation.SuppressLint;
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

    private TextView nomText, emailText, phoneText, adresseText, villeText;
    private Button retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        nomText = findViewById(R.id.nomText);
        emailText = findViewById(R.id.emailText);
        phoneText = findViewById(R.id.phoneText);
        adresseText = findViewById(R.id.adresseText);
        villeText = findViewById(R.id.villeText);

        retour = findViewById(R.id.retour);

        nomText.setText(this.getIntent().getStringExtra("nom"));
        emailText.setText(this.getIntent().getStringExtra("email"));
        phoneText.setText(this.getIntent().getStringExtra("phone"));
        adresseText.setText(this.getIntent().getStringExtra("adresse"));
        villeText.setText(this.getIntent().getStringExtra("ville"));


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}