package com.example.hibernate.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="authors")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authors_id;
    private String full_name;
    private String photo;
    private String biography;
    private LocalDate born;
    private LocalDate died;

    public Author() {}

    public int getAuthors_id() {
        return authors_id;
    }

    public void setAuthors_id(int authors_id) {
        this.authors_id = authors_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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

    @Override
    public String toString() {
        return "Author{" +
                "authors_id=" + authors_id +
                ", full_name='" + full_name + '\'' +
                ", photo='" + photo + '\'' +
                ", biography='" + biography + '\'' +
                ", born=" + born +
                ", died=" + died +
                '}';
    }
}
