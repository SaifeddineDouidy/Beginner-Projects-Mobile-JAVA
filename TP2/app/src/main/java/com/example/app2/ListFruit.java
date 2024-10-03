package com.example.app2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.app2.Adapter.FruitAdapter;
import com.example.app2.beans.Fruit;
import com.example.app2.services.FruitService;

public class ListFruit extends AppCompatActivity {
    private FruitService fruitService;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_fruit);


        fruitService = new FruitService();
        fruitService.create(new Fruit("Pomme", 1.5, R.mipmap.apple));
        fruitService.create(new Fruit("Pastèque", 35, R.mipmap.watermelon));
        fruitService.create(new Fruit("Orange", 10, R.mipmap.orange));
        fruitService.create(new Fruit("Pomme", 1.5, R.mipmap.apple));
        fruitService.create(new Fruit("Pastèque", 35, R.mipmap.watermelon));
        fruitService.create(new Fruit("Orange", 10, R.mipmap.orange));
        fruitService.create(new Fruit("Pomme", 1.5, R.mipmap.apple));
        fruitService.create(new Fruit("Pastèque", 35, R.mipmap.watermelon));
        fruitService.create(new Fruit("Orange", 10, R.mipmap.orange));
        fruitService.create(new Fruit("Pomme", 1.5, R.mipmap.apple));
        fruitService.create(new Fruit("Pastèque", 35, R.mipmap.watermelon));
        fruitService.create(new Fruit("Orange", 10, R.mipmap.orange));
        fruitService.create(new Fruit("Pomme", 1.5, R.mipmap.apple));
        fruitService.create(new Fruit("Pastèque", 35, R.mipmap.watermelon));
        fruitService.create(new Fruit("Orange", 10, R.mipmap.orange));


        list = findViewById(R.id.listView);
        list.setAdapter(new FruitAdapter(fruitService.findAll(), this));
         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 AlertDialog.Builder alert = new AlertDialog.Builder(ListFruit.this);
                 alert.setMessage("Voulez-vous supprimer ce fruit ?");
                 alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Fruit fruit = (Fruit) parent.getItemAtPosition(position);
                         fruitService.delete(fruit);

                         FruitAdapter fruitAdapter = (FruitAdapter) list.getAdapter();
                         fruitAdapter.removeFruit(position);

                     }
                 });
                 alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.cancel();
                     }
                 });
                 alert.show();
             }
         });
    }

}