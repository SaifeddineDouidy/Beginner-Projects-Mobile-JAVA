package com.example.app2.classes;

public class Produit {
    private int id;
    private String nom;
    private int nbrIngredients;
    private int image;
    private String duree;
    private String detailsIngredients;
    private String description;
    private String preparation;
    private static int compteur = 0;

    public Produit() {
        this.id = ++compteur;
    }

    public Produit(String nom, int nbrIngredients, int image,String duree, String detailsIngredients, String description, String preparation) {
        this.id = ++compteur;
        this.nom = nom;
        this.nbrIngredients = nbrIngredients;
        this.image = image;
        this.duree = duree;
        this.detailsIngredients = detailsIngredients;
        this.description = description;
        this.preparation = preparation;
    }

    public static int getCompteur() {
        return compteur;
    }

    public static void setCompteur(int compteur) {
        Produit.compteur = compteur;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailsIngredients() {
        return detailsIngredients;
    }

    public void setDetailsIngredients(String detailsIngredients) {
        this.detailsIngredients = detailsIngredients;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNbrIngredients() {
        return nbrIngredients;
    }

    public void setNbrIngredients(int nbrIngredients) {
        this.nbrIngredients = nbrIngredients;
    }

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

    @Override
    public String toString() {
        return "Produit : " + "id=" + id + ", nom='" + nom + '\'' +
                ", nbrIngredients=" + nbrIngredients + ", image=" + image + ", duree=" + duree + ", detailsIngredients='" + detailsIngredients + '\'' +
                ", description='" + description + '\'' + ", preparation='" + preparation ;
    }

}
