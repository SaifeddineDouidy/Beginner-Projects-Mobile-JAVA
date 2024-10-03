package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PizzaDescriptionActivity extends AppCompatActivity {
    private TextView pizzaName, pizzaDescription, pizzaIngredient, pizzaPreparation;
    private ImageView pizzaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza_description);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Enable the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            // Set the title directly on the ActionBar instead of using a separate TextView
            getSupportActionBar().setTitle(getIntent().getStringExtra("pizzaName"));
        }

        // Handle the back button click
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Initialize views
        pizzaName = findViewById(R.id.pizzaName);
        pizzaDescription = findViewById(R.id.pizzaDescription);
        pizzaIngredient = findViewById(R.id.pizzaIngredient);
        pizzaPreparation = findViewById(R.id.pizzaPreparation);
        pizzaImage = findViewById(R.id.pizzaImage);

        // Retrieve data from intent
        String name = getIntent().getStringExtra("pizzaName");
        String description = getIntent().getStringExtra("pizzaDescription");
        String ingredients = getIntent().getStringExtra("pizzaIngredients");
        String preparation = getIntent().getStringExtra("pizzaPreparation");
        int imageResource = getIntent().getIntExtra("pizzaImage", 0);

        // Set data to views
        pizzaName.setText(name);
        pizzaDescription.setText(description);
        pizzaIngredient.setText(ingredients);
        pizzaPreparation.setText(preparation);
        pizzaImage.setImageResource(imageResource);
    }
}