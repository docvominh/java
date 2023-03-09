package com.vominh.java.core.reflection.dto;

import java.util.List;

public class Country {
    private String name;
    private List<City> cities;
    private President president;
    private long populations;
    private Long area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public President getPresident() {
        return president;
    }

    public void setPresident(President president) {
        this.president = president;
    }
}
