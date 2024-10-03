package com.example.app2.services;

import com.example.app2.beans.Fruit;
import com.example.app2.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public  class FruitService implements IDao<Fruit> {

    private List<Fruit> fruits;

    public FruitService() {
        fruits = new ArrayList<>();

    }

    @Override
    public boolean create(Fruit o) {
        return fruits.add(o);
    }

    @Override
    public boolean update(Fruit o) {
        return false;
    }

    @Override
    public boolean delete(Fruit o) {
        return fruits.remove(o);
    }

    @Override
    public Fruit findById(int id) {
        for(Fruit fruit : fruits){
            if(fruit.getId() == id){
                return fruit;
            }
        }
        return null;
    }

    @Override
    public List<Fruit> findAll() {
        return fruits;
    }
}
