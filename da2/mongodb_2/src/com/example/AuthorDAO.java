package com.example;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class AuthorDAO {
    private static Gson gson = new Gson();

    public static MongoCollection<Document> getAuthorCollection() {
        MongoClient client = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase db = client.getDatabase("bookstore");
        return db.getCollection("authors");
    }

    public static ArrayList<Author> all() {
        var authorCollection = getAuthorCollection();
        var authors = authorCollection.find();
        ArrayList<Author> authors_list = new ArrayList<>();
        for(Document author :  authors) {
            authors_list.add(Author.fromBsonDocument(author));
        }
        return authors_list;
    }

    public static void insert(Author author) {
        var authorCollection = getAuthorCollection();
        authorCollection.insertOne(author.toBsonDocument());
    }

    // String oid
    public static void delete(String oid) {
        delete(new ObjectId(oid));
    }
    public static void delete(ObjectId oid) {
        var authorCollection = getAuthorCollection();
        Bson filter = Filters.eq("_id", oid);
        authorCollection.deleteOne(filter);
    }

    public static Author get(String oid) {
        return get(new ObjectId(oid));
    }

    public static Author get(ObjectId oid) {
        var authorCollection = getAuthorCollection();
        Bson filter = Filters.eq("_id", oid);
        var result = authorCollection.find(filter);
        for(var row : result) {
            return Author.fromBsonDocument(row);
        }
        return null;
    }

    public static void update(Author author) {
        Bson filter = Filters.eq("_id", author.get_id());
        Bson updates = Updates.combine(
          Updates.set("name", author.getName()),
          Updates.set("born", author.getBorn()),
          Updates.set("died", author.getDied()),
          Updates.set("biography", author.getBiography())
        );
        var authorCollection = getAuthorCollection();
        authorCollection.updateOne(filter, updates);
    }
}
