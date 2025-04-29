package com.example;

import java.util.List;

import com.example.model.Actor;
import com.example.repository.ActorRepository;

public class Main {
    public static void main(String[] args) {
      ActorRepository actorRepository = new ActorRepository();

        
        System.out.println("--- Listado de todos los actores ---");
        List<Actor> allActors = actorRepository.findAll();
        if (allActors != null) {
            for (Actor actor : allActors) {
                System.out.println(actor);
            }
        } else {
            System.out.println("No se encontraron actores o hubo un error.");
        }

        System.out.println("\n--- Obtener actor por ID (ejemplo ID 1) ---");
        Actor actor1 = actorRepository.getByID(1);
        if (actor1 != null) {
            System.out.println("Actor con ID 1: " + actor1);
        } else {
            System.out.println("No se encontró el actor con ID 1.");
        }

        System.out.println("\n--- Obtener actor por ID (ejemplo ID inexistente) ---");
        Actor actor999 = actorRepository.getByID(999);
        if (actor999 != null) {
            System.out.println("Actor con ID 999: " + actor999);
        } else {
            System.out.println("No se encontró el actor con ID 999.");
        }

     
      try {
         
          System.out.println("Conexion exitosa");
        } catch (Exception e) {
           System.out.println("Error Conexion");
        }


    }
}