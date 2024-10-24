package com.example.stars.service;



import com.example.stars.beans.Movie;
import com.example.stars.dao.IDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieService implements IDao<Movie> {
    private List<Movie> movies;
    private static MovieService instance;

    private MovieService(){
        this.movies = new ArrayList<>();
    }

    public static MovieService getInstance(){
        if(instance==null){
            instance = new MovieService();
        }
        return instance;
    }

    @Override
    public boolean create(Movie o) {
        return movies.add(o);
    }

    @Override
    public boolean update(Movie o) {
        for(Movie m: movies){
            if(m.getId() == o.getId()){
                m.setImg(o.getImg());
                m.setName(o.getName());
                m.setRating(o.getRating());
            }
        }
        return true;
    }

    @Override
    public boolean delete(Movie o) {
        return movies.remove(o);
    }

    @Override
    public Movie findById(int id) {
        for(Movie m: movies){
            if(m.getId()==id){
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Movie> findAll() {
        return movies;
    }
}
