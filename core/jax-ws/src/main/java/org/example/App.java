package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.api.impl.MovieApiImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8085/api/movie", new MovieApiImpl());

    }
}
