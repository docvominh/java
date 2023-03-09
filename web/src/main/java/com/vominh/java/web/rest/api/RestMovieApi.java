package com.vominh.java.web.rest.api;

import com.vominh.java.web.model.Movie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/movie")
public class RestMovieApi {

    private static List<Movie> movies;

    static {
        movies = new ArrayList<>();
        movies.add(new Movie("Big Bounce", "US"));
        movies.add(new Movie("Wu xia", "China"));
        movies.add(new Movie("Xóm Nước Đen", "Vietnam"));
    }

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello";
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie get(@PathParam("id") int id) {
        return movies.stream().filter(m -> m.getId() == id).findAny().orElse(null);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok()
                .entity(movies)
                .build();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("id") int id, @FormParam("id") String name) {
        Optional<Movie> optional = movies.stream().filter(m -> m.getId() == id).findAny();
        Movie movie = null;
        if (optional.isPresent()) {
            movie = optional.get();
            movie.setName(name);

        }
        return Response.ok()
                .entity(movie)
                .build();

    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Optional<Movie> optional = movies.stream().filter(m -> m.getId() == id).findAny();
        if (optional.isPresent()) {
            movies.remove(optional.get());
            Response.status(201).entity(true).build();
        }

        return Response.status(500).entity(false).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@FormParam("name") String name, @FormParam("country") String country) {
        Movie movie = new Movie(name, country);
        movies.add(movie);
        return Response.ok()
                .entity(movies)
                .build();
    }

}
