package my.library.database.dao;

import my.library.database.JDBIManager;
import my.library.models.Author;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

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

    public static ArrayList<Author> all() throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        return (ArrayList<Author>) jdbi.withHandle(handle -> {
           return handle.createQuery("SELECT * FROM authors;")
                   .mapToBean(Author.class)
                   .list();
        });
    }

    public static int save(Author author) throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO authors VALUES (NULL, :full_name, :photo, :biography, :born, :died)")
                    .bindBean(author)
                    .execute();
        });
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM authors ORDER BY authors_id DESC LIMIT 1")
                    .mapToBean(Author.class)
                    .one().getAuthors_id();
        });
    }

    public static void delete(int author_id) throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM authors WHERE authors_id = :author_id")
                    .bind("author_id", author_id)
                    .execute();
        });
    }

    public static Author get(int author_id) throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM authors WHERE authors_id = :authors_id")
                    .bind("authors_id", author_id)
                    .mapToBean(Author.class)
                    .one();
        });
    }

    public static void update(Author author) throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        jdbi.useHandle(handle -> {
            handle.createUpdate("""
        UPDATE authors SET full_name = :full_name,
        biography = :biography,
        born = :born,
        died = :died,
        photo = :photo
        WHERE authors_id = :authors_id
        """).bindBean(author).execute();
        });
    }
}
