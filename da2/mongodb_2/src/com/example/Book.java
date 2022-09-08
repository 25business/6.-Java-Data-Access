package com.example;

public class Book {
    private String title;
    private int published;

    public Book() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", published=" + published +
                '}';
    }
}
