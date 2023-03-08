package org.example.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.example.model.Movie;

import java.util.List;

@WebService
public interface MovieApi {
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
