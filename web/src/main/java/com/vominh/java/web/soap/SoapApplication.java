package com.vominh.java.web.soap;

import com.vominh.java.web.soap.api.impl.MovieApiImpl;
import jakarta.xml.ws.Endpoint;

/**
 * Hello world!
 */
public class SoapApplication {
    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8085/api/movie", new MovieApiImpl());

    }
}
