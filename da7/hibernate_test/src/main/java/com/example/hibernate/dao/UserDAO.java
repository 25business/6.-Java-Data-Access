package com.example.hibernate.dao;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.models.User;
import org.hibernate.Session;

import java.util.ArrayList;

public class UserDAO {

    public static ArrayList<User> all() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            users = (ArrayList<User>)session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public static User one(int user_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            User user = session.createQuery("FROM User u WHERE u.users_id = :users_id", User.class)
                    .setParameter("users_id", user_id).getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void save(User user) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(User user) {
        save(user);
    }

    public static void delete(int user_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("DELETE FROM User u WHERE u.users_id = :users_id")
                    .setParameter("users_id", user_id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(User user) {
        delete(user.getUsers_id());
    }
}
