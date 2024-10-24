package com.example.app2.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app2.R;
import com.example.app2.classes.Produit;

import java.util.List;

public class PizzaAdapter extends BaseAdapter {
    private List<Produit> produits;
    private LayoutInflater inflater;

    public PizzaAdapter(List<Produit> produits, Activity activity) {
        this.produits = produits;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return produits.size();
    }

    @Override
    public Object getItem(int position) {
        return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeProduit(int position) {
        produits.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.pizza_item, null);

        ImageView pizzaImage = convertView.findViewById(R.id.pizzaImage);
        TextView pizzaName = convertView.findViewById(R.id.pizzaName);
        TextView pizzaNbrIngredients = convertView.findViewById(R.id.pizzaNbrIngredients);
        TextView pizzaDuree = convertView.findViewById(R.id.pizzaDuree);

        pizzaImage.setImageResource(produits.get(position).getImage());
        pizzaName.setText(produits.get(position).getNom());
        pizzaNbrIngredients.setText(String.valueOf(produits.get(position).getNbrIngredients()) + " ingredients");
        pizzaDuree.setText(produits.get(position).getDuree());

        return convertView;
    }
}

