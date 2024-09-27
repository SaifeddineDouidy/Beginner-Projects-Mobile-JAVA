package com.example.tp1_exercice3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button suivant,reset, quitter;
    private CheckBox checkbox1, checkbox2, checkbox3, checkbox4;
    private RadioGroup radioGroup;
    private Spinner villes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        suivant = findViewById(R.id.suivant);
        reset = findViewById(R.id.reset);
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);
        radioGroup = findViewById(R.id.radioGroup);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                String selectedCheckboxes = "";
                if (checkbox1.isChecked()) {
                    selectedCheckboxes += checkbox1.getText() + ", ";
                }
                if (checkbox2.isChecked()) {
                    selectedCheckboxes += checkbox2.getText() + ", ";
                }
                if (checkbox3.isChecked()) {
                    selectedCheckboxes += checkbox3.getText() + ", ";
                }
                if (checkbox4.isChecked()) {
                    selectedCheckboxes += checkbox4.getText() + ", ";
                }

                intent.putExtra("quest1", selectedCheckboxes);


                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selected = findViewById(selectedRadioButtonId);
                    intent.putExtra("quest2", selected.getText().toString());
                }


                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                checkbox1.setChecked(false);
                checkbox2.setChecked(false);
                checkbox3.setChecked(false);
                checkbox4.setChecked(false);
            }
        });

    }
}