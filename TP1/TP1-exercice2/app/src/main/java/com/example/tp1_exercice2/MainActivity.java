package com.example.tp1_exercice2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView impotdeBase;
    private TextView impotTotal;
    private TextView impotSupp;
    private EditText nomEdit;
    private EditText adresseEdit;
    private EditText nbrPieceEdit;
    private EditText surfaceEdit;
    private Button button;
    private Button reset;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        nomEdit = findViewById(R.id.nomEdit);
        adresseEdit = findViewById(R.id.adresseEdit);
        nbrPieceEdit = findViewById(R.id.nbrPieceEdit);
        surfaceEdit = findViewById(R.id.surfaceEdit);
        checkBox = findViewById(R.id.checkBox);
        impotdeBase = findViewById(R.id.impotdeBase);
        impotSupp = findViewById(R.id.impotSupp);
        impotTotal = findViewById(R.id.impotTotal);
        reset = findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomEdit.getText().clear();
                adresseEdit.getText().clear();
                nbrPieceEdit.getText().clear();
                surfaceEdit.getText().clear();
                checkBox.setChecked(false);
                impotdeBase.setText("Impot de base :");
                impotSupp.setText("Impot Supplémentaire :");
                impotTotal.setText("Impot total :");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerRes();
            }
        });
    }

    private void calculerRes() {

            int surface = Integer.parseInt(surfaceEdit.getText().toString());
            int nbrPiece = Integer.parseInt(nbrPieceEdit.getText().toString());

            int baseImpot = calculBaseImpot(surface);
            int supplementImpot = calculSupplementImpot(nbrPiece, checkBox.isChecked());

            impotdeBase.setText(String.format("Impot de base: %d", baseImpot));
            impotSupp.setText(String.format("Impot Supplémentaire: %d", supplementImpot));

            int totalImpot = baseImpot + supplementImpot;
            impotTotal.setText(String.format("Impot total: %d", totalImpot));

    }

    private int calculBaseImpot(int surface) {
        return surface * 2;
    }

    private int calculSupplementImpot(int nbrPiece, boolean hasPiscine) {
        int supplement = nbrPiece * 50;
        if (hasPiscine) {
            supplement += 100;
        }
        return supplement;
    }

}
