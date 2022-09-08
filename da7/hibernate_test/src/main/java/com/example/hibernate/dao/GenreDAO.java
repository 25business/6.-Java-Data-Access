package com.example.hibernate.dao;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.models.Genre;
import org.hibernate.Session;

import java.util.ArrayList;

public class GenreDAO {

    public static ArrayList<Genre> all() {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            genres = (ArrayList<Genre>)session.createQuery("FROM Genre", Genre.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return genres;
    }

    public static Genre one(int genre_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Genre book = session.createQuery("FROM Genre g WHERE g.genres_id = :genres_id", Genre.class)
                    .setParameter("genres_id", genre_id).getSingleResult();
            session.getTransaction().commit();
            return book;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void save(Genre genre) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(genre);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Genre genre) {
        save(genre);
    }

    public static void delete(int genre_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("DELETE FROM Genre g WHERE g.genres_id = :genres_id")
                    .setParameter("genres_id", genre_id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(Genre genre) {
        delete(genre.getGenres_id());
    }
}
