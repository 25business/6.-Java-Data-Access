package my.library.database.dao;

import my.library.database.JDBIManager;
import my.library.models.Book;
import my.library.models.Genre;
import my.library.models.Publisher;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BookDAO {

    public static ArrayList<Book> all() throws FileNotFoundException {
        String query_string = """
                SELECT books.books_id AS "bk_books_id", books.title AS "bk_title", 
                       books.cover AS "bk_cover", books.synopsis AS "bk_synopsis", 
                       books.release_date AS "bk_release_date", 
                       publishers.publishers_id AS "pb_publishers_id", 
                       publishers.name AS "pb_name", publishers.website AS "pb_website", 
                       publishers.address AS "pb_address", genres.genres_id AS "gn_genres_id", 
                       genres.name AS "gn_name" 
                FROM books, publishers, genres, books_genres 
                WHERE (books.publisher_id = publishers.publishers_id) 
                AND (books.books_id = books_genres.books_id) 
                AND (genres.genres_id = books_genres.genres_id);""";
        Jdbi jdbi = JDBIManager.get();
        List<Book> result = jdbi.withHandle(handle -> {
            return handle.createQuery(query_string)
                    .registerRowMapper(BeanMapper.factory(Book.class,"bk"))
                    .registerRowMapper(BeanMapper.factory(Genre.class,"gn"))
                    .registerRowMapper(BeanMapper.factory(Publisher.class,"pb"))
                    .reduceRows(new LinkedHashMap<Long, Book>(), (map, rowView) -> {
                        Book book = map.computeIfAbsent(rowView.getColumn("bk_books_id", Long.class), id -> rowView.getRow(Book.class));

                        if (rowView.getColumn("pb_publishers_id", Long.class) != null) {
                            book.setPublisher(rowView.getRow(Publisher.class));
                        }
                        if (rowView.getColumn("gn_genres_id", Long.class) != null) {
                            book.addGenre(rowView.getRow(Genre.class));
                        }
                        return map;
                    }).values().stream().collect(Collectors.toList());
        });
        return (ArrayList<Book>) result;
    }

    public static Book one(int books_id) throws FileNotFoundException {
        String query_string = """
                SELECT books.books_id AS "bk_books_id", books.title AS "bk_title", 
                       books.cover AS "bk_cover", books.synopsis AS "bk_synopsis", 
                       books.release_date AS "bk_release_date", 
                       publishers.publishers_id AS "pb_publishers_id", 
                       publishers.name AS "pb_name", publishers.website AS "pb_website", 
                       publishers.address AS "pb_address", genres.genres_id AS "gn_genres_id", 
                       genres.name AS "gn_name" 
                FROM books, publishers, genres, books_genres 
                WHERE (books.publisher_id = publishers.publishers_id) 
                AND (books.books_id = books_genres.books_id) 
                AND (genres.genres_id = books_genres.genres_id) 
                AND (books.books_id = :books_id);""";
        Jdbi jdbi = JDBIManager.get();
        List<Book> result = jdbi.withHandle(handle -> {
            return handle.createQuery(query_string)
                    .bind("books_id", books_id)
                    .registerRowMapper(BeanMapper.factory(Book.class,"bk"))
                    .registerRowMapper(BeanMapper.factory(Genre.class,"gn"))
                    .registerRowMapper(BeanMapper.factory(Publisher.class,"pb"))
                    .reduceRows(new LinkedHashMap<Long, Book>(), (map, rowView) -> {
                        Book book = map.computeIfAbsent(rowView.getColumn("bk_books_id", Long.class), id -> rowView.getRow(Book.class));

                        if (rowView.getColumn("pb_publishers_id", Long.class) != null) {
                            book.setPublisher(rowView.getRow(Publisher.class));
                        }
                        if (rowView.getColumn("gn_genres_id", Long.class) != null) {
                            book.addGenre(rowView.getRow(Genre.class));
                        }
                        return map;
                    }).values().stream().collect(Collectors.toList());
        });
        return result.size() == 0 ? null : result.get(0);
    }
}
