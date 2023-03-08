package org.example.model;

import java.util.Random;

public class Movie {
    private int id;
    private String name;
    private String country;

    public Movie() {

    }

    public Movie(String name, String country) {
        this.id = (new Random()).nextInt(100000);
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
