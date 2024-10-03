package com.example.app2.beans;

import java.util.ArrayList;
import java.util.List;

public class Fruit {
    private int id;
    private static int compteur;

    private String nom;
    private double prix;
    private int image;

    List<String> l = new ArrayList<>();

    //Constructeurs
    public Fruit() {

    }
    public Fruit(String nom, double prix, int image) {
        this.id = ++compteur;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
    }

    //Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
