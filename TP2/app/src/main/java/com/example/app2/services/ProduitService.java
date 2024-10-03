package com.example.app2.services;

import com.example.app2.classes.Produit;
import com.example.app2.dao.IDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitService implements IDao<Produit> {
    private List<Produit> produits;

    public ProduitService() {
        this.produits = new ArrayList<Produit>();
    }

    @Override
    public boolean create(Produit o) {
        return produits.add(o);
    }

    @Override
    public boolean update(Produit o) {
        return false;
    }

    @Override
    public boolean delete(Produit o) {
        return produits.remove(o);
    }

    @Override
    public Produit findById(int id) {
        for(Produit produit: produits)
            if(produit.getId() == id)
                return produit;
        return null;
    }

    @Override
    public List<Produit> findAll() {
        return produits;
    }
}
