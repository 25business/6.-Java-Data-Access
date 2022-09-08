package my.library.hibernate.dao;


import my.library.hibernate.HibernateUtil;
import my.library.hibernate.models.Publisher;
import org.hibernate.Session;

import java.util.ArrayList;

public class PublisherDAO {

    public static ArrayList<Publisher> all() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            publishers = (ArrayList<Publisher>)session.createQuery("FROM Publisher", Publisher.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return publishers;
    }

    public static Publisher one(int publisher_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Publisher book = session.createQuery("FROM Publisher p WHERE p.publishers_id = :publishers_id", Publisher.class)
                    .setParameter("publishers_id", publisher_id).getSingleResult();
            session.getTransaction().commit();
            return book;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void save(Publisher publisher) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(publisher);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Publisher publisher) {
        save(publisher);
    }

    public static void delete(int publisher_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("DELETE FROM Publisher p WHERE p.publishers_id = :publishers_id")
                    .setParameter("publishers_id", publisher_id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(Publisher publisher) {
        delete(publisher.getPublishers_id());
    }
}
