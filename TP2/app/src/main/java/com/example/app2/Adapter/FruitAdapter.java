package com.example.app2.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app2.R;
import com.example.app2.beans.Fruit;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private List<Fruit> fruits;
    private LayoutInflater inflater;

    public FruitAdapter(List<Fruit> fruits, Activity activity) {
        this.fruits = fruits;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeFruit(int position) {
        fruits.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.item, null);

        TextView id = convertView.findViewById(R.id.id);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView prix = convertView.findViewById(R.id.prix);
        ImageView image = convertView.findViewById(R.id.image);

        id.setText(fruits.get(position).getId()+"");
        nom.setText(fruits.get(position).getNom());
        prix.setText(fruits.get(position).getPrix()+"");
        image.setImageResource(fruits.get(position).getImage());

        return convertView;
    }
}
