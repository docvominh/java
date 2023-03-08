package org.example.api.impl;

import jakarta.jws.WebService;
import org.example.api.MovieApi;
import org.example.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*

 */
@WebService(endpointInterface = "org.example.api.MovieApi")
public class MovieApiImpl implements MovieApi {

    private static List<Movie> movies;

    static {
        movies = new ArrayList<>();
        movies.add(new Movie("Big Bounce", "US"));
        movies.add(new Movie("Wu xia", "China"));
        movies.add(new Movie("Xóm Nước Đen", "Vietnam"));
    }

    @Override
    public Movie get(int id) {
        return movies.stream().filter(m -> m.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Movie> getAll() {
        return movies;
    }

    @Override
    public Movie update(int id, String name) {
        Optional<Movie> optional = movies.stream().filter(m -> m.getId() == id).findAny();
        Movie movie = null;
        if (optional.isPresent()) {
            movie = optional.get();
            movie.setName(name);

        }

        return movie;
    }

    @Override
    public boolean delete(int id) {
        Optional<Movie> optional = movies.stream().filter(m -> m.getId() == id).findAny();
        if (optional.isPresent()) {
            movies.remove(optional.get());
            return true;
        }

        return false;
    }

    @Override
    public Movie add(String name, String country) {
        Movie movie = new Movie(name, country);
        movies.add(movie);
        return movie;
    }
}
