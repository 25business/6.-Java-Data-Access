package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        /*
        Author new_author = new Author();
        new_author.set_id(new ObjectId());
        new_author.setName("Edgar Allan Poe");
        new_author.setBorn(LocalDate.of(1809,1,19));
        new_author.setDied(LocalDate.of(1849,10,7));
        AuthorDAO.insert(new_author);
         */

        ArrayList<Author> authors = AuthorDAO.all();
        for(var author : authors) {
            System.out.println(author);
        }

        /*Author author = AuthorDAO.get("623cbc59295dd7139d9594a2");
        System.out.println(author);*/
        /*author.setBiography("Agatha's biography");
        AuthorDAO.update(author);*/

        /*
        MongoCollection<Document> authorsCollection = AuthorDAO.getAuthorCollection();
        Bson filter = Filters.eq("_id", new ObjectId("623cbc59295dd7139d9594a2"));
        var results = authorsCollection.find(filter);
        for(var row : results) {
           var books = row.getList("books", Document.class);
           for(var book : books) System.out.println(book.get("title"));
        }
         */
    }
}
