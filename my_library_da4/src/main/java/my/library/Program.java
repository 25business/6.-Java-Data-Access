package my.library;

import my.library.database.dao.BookDAO;
import my.library.models.Book;
import my.library.models.Genre;
import my.library.models.Publisher;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            Book b = BookDAO.one(1);
            System.out.printf("""
                    Books ID: %d
                    Title: %s
                    Cover: %s
                    Synopsis: %s
                    Release date: %s
                    """,
                    b.getBooks_id(), b.getTitle(),
                    b.getCover(), b.getSynopsis(),
                    b.getRelease_date());
            Publisher p = b.getPublisher();
            System.out.printf("""
                    Publisher ID: %d
                    Name: %s
                    Website: %s 
                    Address: %s
                    """, p.getPublishers_id(),
                    p.getName(), p.getWebsite(), p.getAddress());
            ArrayList<Genre> genres = b.getGenres();
            for(var g : genres)
                System.out.printf("""
                        Genre ID: %d
                        Name: %s 
                        """, g.getGenres_id(), g.getName());

            for(var author : b.getAuthors())
                System.out.printf("""
                        Author ID: %d
                        Full name: %s
                        """, author.getAuthors_id(), author.getFull_name());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
