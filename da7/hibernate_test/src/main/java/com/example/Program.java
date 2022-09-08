package com.example;

import com.example.hibernate.HibernateUtil;
import com.example.hibernate.dao.BookDAO;
import com.example.hibernate.models.Book;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            ArrayList<Book> books = BookDAO.all();
            for(Book b : books) {
                System.out.println(b);
            }
            Book book = BookDAO.one(6);
            System.out.println(book);

            /*Book new_book = new Book();
            new_book.setTitle("Test");
            new_book.setCover("nocover.webp");
            BookDAO.save(new_book);*/

            book.setTitle("Updated Test");
            BookDAO.update(book);

            /*BookDAO.delete(book);
            BookDAO.delete(4);*/

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            HibernateUtil.closeAll();
        }
    }
}
