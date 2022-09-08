package com.example.hibernate;

import com.example.hibernate.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Publisher.class);
        configuration.addAnnotatedClass(Genre.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(User.class);

        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
