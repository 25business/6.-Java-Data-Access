package com.example;

import java.io.Serializable;

public class Artikal implements Serializable {
    private String naziv;
    private double cena;

    public Artikal() {}

    public Artikal(String naziv, double cena) {
        this.naziv = naziv;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double cenaSaPorezom() {
        return cena * 1.2;
    }
    public double porez() {
        return cena * 0.2;
    }
}
