package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nomEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText adresseEdit;
    private Button send, reset;
    private Spinner villes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nomEdit = findViewById(R.id.nomEdit);
        emailEdit = findViewById(R.id.emailEdit);
        phoneEdit = findViewById(R.id.phoneEdit);
        adresseEdit = findViewById(R.id.adresseEdit);
        villes  =  findViewById(R.id.villes);
        send = findViewById(R.id.send);
        reset = findViewById(R.id.reset);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomEdit.getText().clear();
                emailEdit.getText().clear();
                phoneEdit.getText().clear();
                adresseEdit.getText().clear();
                villes.setSelection(0);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("nom", nomEdit.getText().toString());
                intent.putExtra("email", emailEdit.getText().toString());
                intent.putExtra("phone", phoneEdit.getText().toString());
                intent.putExtra("adresse", adresseEdit.getText().toString());
                intent.putExtra("ville", villes.getSelectedItem().toString());

                startActivity(intent);
            }

        }

        );
    }
}