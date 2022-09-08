package com.example.hibernate.dao;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.models.Author;
import com.example.hibernate.models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class BookDAO {

    public static ArrayList<Book> all() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            books = (ArrayList<Book>)session.createQuery("FROM Book", Book.class).list();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }
}
