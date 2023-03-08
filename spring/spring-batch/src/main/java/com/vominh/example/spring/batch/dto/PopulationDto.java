package com.vominh.example.spring.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PopulationDto {

    @JsonProperty("Year")
    private int year;
    @JsonProperty("Nation")
    private String nation;
    @JsonProperty("Population")
    private int population;

    public PopulationDto() {
    }

    public PopulationDto(int year, String nation, int population) {
        this.year = year;
        this.nation = nation;
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format("Nation: %s, Year %s, population: %s", nation, year, population);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
