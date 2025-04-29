package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        List<Actor> actores = new ArrayList<>();
        String sql = "SELECT actor_id, first_name, last_name FROM actor";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getInstance();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor();
                actor.setActorID(rs.getInt("actor_id"));
                actor.setFirstName(rs.getString("first_name"));
                actor.setLastName(rs.getString("last_name"));
                actores.add(actor);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los actores: " + e.getMessage());

        } finally {

            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return actores;

    }

    @Override
    public Actor getByID(Integer id) {
        Actor actor = null;
        String sql = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getInstance(); 
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                actor = new Actor();
                actor.setActorID(rs.getInt("actor_id"));
                actor.setFirstName(rs.getString("first_name"));
                actor.setLastName(rs.getString("last_name"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el actor con ID " + id + ": " + e.getMessage());
         
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           
        }
        return actor;
    }

    @Override
    public void save(Actor t) {
        return;
    }

    @Override
    public void delete(Integer id) {
        return;
    }

}
