package com.example;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.dao.AuthorDAO;
import com.example.hibernate.dao.BookDAO;
import com.example.hibernate.models.Author;
import com.example.hibernate.models.Book;
import com.example.hibernate.models.Publisher;
import com.example.hibernate.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();

            /*
            Publisher new_publisher = new Publisher();
            new_publisher.setName("Little, Brown and Company");
            new_publisher.setWebsite("https://www.littlebrown.com/");
            session.persist(new_publisher);
             */
            List<Publisher> publishers = session.createQuery("FROM Publisher", Publisher.class).list();
            for(Publisher p : publishers) System.out.println(p.getName());

            /*
            Author new_author = new Author();
            new_author.setFull_name("Arthur Conan Doyle");
            new_author.setPhoto("arthur_conan_doyle.webp");
            new_author.setBorn(LocalDate.parse("1859-05-22"));
            new_author.setDied(LocalDate.parse("1930-07-07"));
            session.persist(new_author);
             */

            /*
            Author dan_simmons = session.createQuery("FROM Author a WHERE a.authors_id = 1",Author.class).getSingleResult();
            dan_simmons.setBiography("""
                    Dan Simmons grew up in various cities and small towns in the Midwest, including Brimfield, Illinois, which was the source of his fictional "Elm Haven" in 1991's SUMMER OF NIGHT and 2002's A WINTER HAUNTING. Dan received a B.A. in English from Wabash College in 1970, winning a national Phi Beta Kappa Award during his senior year for excellence in fiction, journalism and art.
                                        
                    Dan received his Masters in Education from Washington University in St. Louis in 1971. He then worked in elementary education for 18 years—2 years in Missouri, 2 years in Buffalo, New York—one year as a specially trained BOCES "resource teacher" and another as a sixth-grade teacher—and 14 years in Colorado.""");
            session.persist(dan_simmons);
             */
            /*
            int rows_affected = session.createQuery("DELETE FROM Author a WHERE a.authors_id = :authors_id")
                    .setParameter("authors_id", 6).executeUpdate();
            System.out.println(rows_affected);
             */

            //List<Author> authors = session.createQuery("FROM Author", Author.class).list();
            List<Author> authors = AuthorDAO.all();
            for(Author a : authors) {
                System.out.println(a.getFull_name());
            }

            //List<Book> books = session.createQuery("FROM Book", Book.class).list();
            List<Book> books = BookDAO.all();
            for(Book b : books) System.out.println(b);

            /*
            Publisher publisher = session.createQuery("FROM Publisher p WHERE p.publishers_id = 1", Publisher.class).getSingleResult();
            List<Book> books = publisher.getBooks();
            for(Book b : books) System.out.println(b);
             */

            User user = session.createQuery("FROM User u WHERE u.users_id = 1", User.class).getSingleResult();
            System.out.println(user);

            session.getTransaction().commit();
            session.close();
            sessionFactory.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
