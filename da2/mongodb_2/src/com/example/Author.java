package com.example;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Author {
    private ObjectId _id;
    private String name;
    private LocalDate born;
    private LocalDate died;
    private String biography;
    private ArrayList<Book> books = new ArrayList<>();

    public Author() {}

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String output = "Author{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", born=" + born +
                ", died=" + died +
                ", biography='" + biography + '\'' +
                '}';
        for(Book book: books) output += book;
        return output;
    }

    public static Author fromBsonDocument(Document data) {
        Author tmp = new Author();
        tmp._id = data.getObjectId("_id");
        if(data.containsKey("name")) tmp.name = data.getString("name");
        if(data.containsKey("born")) {
            Date tmp_born = data.getDate("born");
            tmp.born = LocalDate.ofInstant(tmp_born.toInstant(), ZoneId.of("Europe/Belgrade"));
        }
        if(data.containsKey("died")) {
            Date tmp_died = data.getDate("died");
            tmp.died =  LocalDate.ofInstant(tmp_died.toInstant(), ZoneId.of("Europe/Belgrade"));
        }
        if(data.containsKey("biography")) tmp.biography = data.getString("biography");

        if(data.containsKey("books")) {
            var books = data.getList("books", Document.class);
            for(Document book : books) {
                Book b = new Book();
                b.setTitle(book.getString("title"));
                b.setPublished(book.getInteger("published"));
                tmp.books.add(b);
            }
        }

        return tmp;
    }

    public Document toBsonDocument() {
        Document tmp = new Document();
        tmp.put("_id", this._id);
        tmp.put("name", this.name);
        tmp.put("born", this.born);
        tmp.put("died", this.died);
        tmp.put("biography", this.biography);
        return tmp;
    }
}
