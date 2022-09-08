package my.library.models;

import my.library.database.dao.AuthorDAO;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Book implements Serializable {
    private int books_id;
    private String title;
    private String synopsis;
    private String cover;
    private LocalDate release_date;
    private Publisher publisher;
    private ArrayList<Genre> genres = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();

    public Book() {}
    public Book(HashMap<String, Object> book_data) {
        this.title = (String) book_data.get("title");
        this.cover = "nocover.webp";
    }

    public int getBooks_id() {
        return books_id;
    }

    public void setBooks_id(int books_id) {
        this.books_id = books_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public ArrayList<Author> getAuthors() {
        try {
            this.authors = AuthorDAO.allForBook(books_id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.authors;
    }
}
