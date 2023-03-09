package com.vominh.java.web.soap.api;

import com.vominh.java.web.model.Movie;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface SoapMovieApi {
    @WebMethod
    public Movie get(int id);

    @WebMethod
    public List<Movie> getAll();

    @WebMethod
    public Movie update(int id, String name);

    @WebMethod
    public boolean delete(int id);

    @WebMethod
    public Movie add(String name, String country);
}
