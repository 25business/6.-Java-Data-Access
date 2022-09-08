package com.example.hibernate.dao;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.models.Book;
import org.hibernate.Session;

import java.util.ArrayList;

public class BookDAO {

    public static ArrayList<Book> all() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            books = (ArrayList<Book>)session.createQuery("FROM Book", Book.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }

    public static Book one(int book_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Book book = session.createQuery("FROM Book b WHERE b.books_id = :books_id", Book.class)
                    .setParameter("books_id", book_id).getSingleResult();
            session.getTransaction().commit();
            return book;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void save(Book book) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(book);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Book book) {
        save(book);
    }

    public static void delete(int book_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("DELETE FROM Book b WHERE b.books_id = :books_id")
                            .setParameter("books_id", book_id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(Book book) {
        delete(book.getBooks_id());
    }

}
