package my.library.hibernate.dao;

import my.library.hibernate.HibernateUtil;
import my.library.hibernate.models.Author;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public static ArrayList<Author> all() {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            authors = (ArrayList<Author>)session.createQuery("FROM Author", Author.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return authors;
    }

    public static ArrayList<Author> for_ids(List<Integer> ids) {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            authors = (ArrayList<Author>)session.createQuery("FROM Author a WHERE a.authors_id IN (:ids)", Author.class)
                    .setParameter("ids", ids).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return authors;
    }

    public static Author one(int author_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Author author = session.createQuery("FROM Author a WHERE a.authors_id = :authors_id", Author.class)
                    .setParameter("authors_id", author_id).getSingleResult();
            session.getTransaction().commit();
            return author;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void save(Author author) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(author);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Author author) {
        save(author);
    }

    public static void delete(int author_id) {
        try {
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("DELETE FROM Author a WHERE a.authors_id = :authors_id")
                    .setParameter("authors_id", author_id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(Author author) {
        delete(author.getAuthors_id());
    }
}
