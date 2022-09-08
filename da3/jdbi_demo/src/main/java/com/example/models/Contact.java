package com.example.models;

// Java Bean
// Obavezno:
// - implementira interfejs Serializible
// - da ima prazan konstruktor (moze naravno da ima i dodatne)
// - da ima get i set za sva polja i da su sva polja private/protected

import java.io.Serializable;

public class Contact implements Serializable {
    private Integer contacts_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_home;
    private String phone_work;

    public Contact() {}

    public Contact(String first_name, String last_name, String email, String phone_home, String phone_work) {
        this.contacts_id = null;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_home = phone_home;
        this.phone_work = phone_work;
    }

    public Integer getContacts_id() {
        return contacts_id;
    }

    public void setContacts_id(Integer contacts_id) {
        this.contacts_id = contacts_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_home() {
        return phone_home;
    }

    public void setPhone_home(String phone_home) {
        this.phone_home = phone_home;
    }

    public String getPhone_work() {
        return phone_work;
    }

    public void setPhone_work(String phone_work) {
        this.phone_work = phone_work;
    }
}
