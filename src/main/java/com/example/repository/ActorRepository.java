package com.example.repository;

import java.util.List;

import com.example.model.Actor;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        return null;
    }

    @Override
    public Actor getByID(Integer id) {
       return null;
    }

    @Override
    public void save(Actor t) {
       return ;
    }

    @Override
    public void delete(Integer id) {
        return;
    }
    
    
}
