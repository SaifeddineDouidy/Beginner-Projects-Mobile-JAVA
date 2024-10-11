package com.example.tp3;

import android.os.Bundle;
import android.util.Log;

import com.example.tp3.beans.Etudiant;
import com.example.tp3.service.EtudiantService;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button add;

    private EditText id;
    private Button rechercher;
    private Button delete;
    private TextView res;

    void clear() {
        nom.setText("");
        prenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService es = new EtudiantService(this);

        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        add = (Button) findViewById(R.id.bn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                es.create(new Etudiant(nom.getText().toString(), prenom.getText().toString()));
                clear();

                for (Etudiant e : es.findAll()) {
                    Log.d(String.valueOf(e.getId()), e.getNom() + " " + e.getPrenom());
                }
            }
        });

        id = (EditText) findViewById(R.id.id);
        rechercher = (Button) findViewById(R.id.load);
        res = (TextView) findViewById(R.id.res);

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Etudiant e = es.findById(Integer.parseInt(id.getText().toString()));
                    if (e != null) {
                        res.setText(e.getNom() + " " + e.getPrenom());
                    } else {
                        Toast.makeText(MainActivity.this, "Student not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Invalid ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int studentId = Integer.parseInt(id.getText().toString());
                    Etudiant e = es.findById(studentId);
                    if (e != null) {
                        es.delete(e);
                        Toast.makeText(MainActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                        res.setText(""); // Clear the result display after deletion
                    } else {
                        Toast.makeText(MainActivity.this, "Student not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Invalid ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
