package my.library.database.dao;

import my.library.database.JDBIManager;
import my.library.models.Author;
import org.jdbi.v3.core.Jdbi;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public static ArrayList<Author> allForBook(int books_id) throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        String query_string = """
                SELECT authors.authors_id, authors.full_name, 
                       authors.photo, authors.biography, authors.born, 
                       authors.died 
                FROM authors, authors_books 
                WHERE (authors.authors_id = authors_books.authors_id) 
                AND (authors_books.books_id = :books_id)""";
        List<Author> authors = jdbi.withHandle(handle -> {
            return handle.createQuery(query_string)
                    .bind("books_id", books_id)
                    .mapToBean(Author.class)
                    .list();
        });
        return (ArrayList<Author>) authors;
    }
}
