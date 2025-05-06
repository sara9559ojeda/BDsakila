package com.example;

import com.example.model.Actor;
import com.example.repository.ActorRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ActorRepository repo = new ActorRepository();

        // ----- PROBAR SAVE -----
        Actor nuevoActor = new Actor();
        nuevoActor.setFirstName("Sara");
        nuevoActor.setLastName("Ramírez");
        repo.save(nuevoActor);  // Debería insertar un nuevo actor

        // ----- PROBAR FIND ALL -----
        List<Actor> actores = repo.findAll();
        System.out.println("Lista de actores:");
        for (Actor a : actores) {
            System.out.println(a.getActorID() + ": " + a.getFirstName() + " " + a.getLastName());
        }

        // ----- PROBAR GET BY ID -----
        Actor actor1 = repo.getByID(1);  // Cambia 1 por un ID real
        if (actor1 != null) {
            System.out.println("Actor con ID 1: " + actor1.getFirstName() + " " + actor1.getLastName());
        } else {
            System.out.println("No se encontró actor con ID 1.");
        }

        // ----- PROBAR UPDATE -----
        Actor actualizarActor = new Actor();
        actualizarActor.setActorID(1);  // Cambia 1 por un ID real existente
        actualizarActor.setFirstName("Sara Actualizada");
        actualizarActor.setLastName("Ramírez Mod");
        repo.update(actualizarActor);  // Debería actualizar

        // ----- PROBAR DELETE -----
        repo.delete(1);  // Cambia 1 por un ID real existente que quieras borrar

    }
}
