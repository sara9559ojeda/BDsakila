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
    public void save(Actor actor) {
        String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, actor.getFirstName());
            pstmt.setString(2, actor.getLastName());
            pstmt.executeUpdate();
            System.out.println("Actor guardado exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al guardar el actor: " + e.getMessage());

        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sqlDeleteFilmActor = "DELETE FROM film_actor WHERE actor_id = ?";
        String sqlDeleteActor = "DELETE FROM actor WHERE actor_id = ?";

        Connection conn = null;
        PreparedStatement pstmtFilmActor = null;
        PreparedStatement pstmtActor = null;

        try {
            conn = DatabaseConnection.getInstance();

            // eliminar relaciones con actor
            pstmtFilmActor = conn.prepareStatement(sqlDeleteFilmActor);
            pstmtFilmActor.setInt(1, id);
            pstmtFilmActor.executeUpdate();

            pstmtActor = conn.prepareStatement(sqlDeleteActor);
            pstmtActor.setInt(1, id);
            int filasAfectadas = pstmtActor.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Actor eliminado exitosamente (ID: " + id + ").");
            } else {
                System.out.println("No se encontró actor con ID " + id + ".");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el actor con ID " + id + ": " + e.getMessage());
        } finally {
            try {
                if (pstmtFilmActor != null)
                    pstmtFilmActor.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmtActor != null)
                    pstmtActor.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void update(Actor actor) {
        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, actor.getFirstName());
            pstmt.setString(2, actor.getLastName());
            pstmt.setInt(3, actor.getActorID());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Actor actualizado exitosamente.");
            } else {
                System.out.println("No se encontró un actor con el ID: " + actor.getActorID());
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el actor: " + e.getMessage());

        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
