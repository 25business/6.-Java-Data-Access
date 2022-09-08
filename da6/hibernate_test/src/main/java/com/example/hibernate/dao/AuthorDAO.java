package com.example.hibernate.dao;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class AuthorDAO {

    public static ArrayList<Author> all() {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            authors = (ArrayList<Author>)session.createQuery("FROM Author", Author.class).list();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return authors;
    }
}
