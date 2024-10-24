package com.example.stars.beans;

public class Movie {
    private int id;
    private String name;
    private String img;
    private float rating;
    private static int compteur;

    public Movie(String name, String img, float rating) {
        this.id = ++compteur;
        this.name = name;
        this.img = img;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
