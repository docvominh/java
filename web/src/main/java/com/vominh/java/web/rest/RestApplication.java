package com.vominh.java.web.rest;

import com.vominh.java.web.rest.api.RestMovieApi;
import com.vominh.java.web.soap.api.SoapMovieApi;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestApplication {

    public static final String BASE_URI = "http://localhost:8086/";

    public static Server startServer() {

        // scan packages
        // final ResourceConfig config = new ResourceConfig().packages("com.mkyong");

        final ResourceConfig config = new ResourceConfig(RestMovieApi.class);
        final Server server = JettyHttpContainerFactory.createServer(URI.create(BASE_URI), config);

        return server;

    }

    public static void main(String[] args) {

        try {

            final Server server = startServer();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.stop();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    Logger.getLogger(RestApplication.class.getName()).log(Level.SEVERE, null, e);
                }
            }));

            System.out.println(
                    String.format("Application started.%nStop the application using CTRL+C"));

            // block and wait shut down signal, like CTRL+C
            Thread.currentThread().join();

            // alternative
            // Thread.sleep(Long.MAX_VALUE);       // sleep forever...
            // Thread.sleep(Integer.MAX_VALUE);    // sleep around 60+ years

        } catch (InterruptedException ex) {
            Logger.getLogger(RestApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
