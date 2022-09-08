package com.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017/");
            /*
            MongoIterable<String> dblist = mongoClient.listDatabaseNames();
            for(var dbname : dblist) {
                System.out.println(dbname);
            }*/
            MongoDatabase db = mongoClient.getDatabase("bookstore");
            MongoCollection<Document> authorsCollection = db.getCollection("authors");

            /*
            Document new_author = new Document();
            new_author.put("name", "Edgar Allan Poe");
            new_author.put("born", LocalDate.parse("1809-01-18"));
            new_author.put("died", LocalDate.parse("1849-10-07"));

            authorsCollection.insertOne(new_author);
             */

            /*
            Bson filter = Filters.eq("name", "Edgar Allan Poe");
            Bson updates = Updates.set("biography", "Edgar's bio.");
            authorsCollection.updateOne(filter, updates);
             */

            /*
            Bson filter = Filters.eq("_id", new ObjectId("6245d211a03b973d83eeb695"));
            authorsCollection.deleteOne(filter);
             */

            FindIterable<Document> authors =  authorsCollection.find();
            for(Document author : authors) {
                System.out.println(author.get("name"));
                //System.out.println(author.get("born"));
                System.out.println(author.get("biography"));
                System.out.println("------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
