package com.aijewelry;

import com.aijewelry.controller.DesignController;
import com.aijewelry.controller.UserController;
import com.aijewelry.util.CorsFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://0.0.0.0:8080/api/";

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);
        final ResourceConfig config = new ResourceConfig()
                .register(DesignController.class)
                .register(UserController.class)
                .register(provider)
                .register(CorsFilter.class);

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config, false);
        server.start();
        System.out.println("✅ Server running at " + BASE_URI);
        System.out.println("📂 Serving images at http://0.0.0.0:8080/uploads/<filename>.png");
        System.out.println("🔴 Press Ctrl+C to stop.");
        Thread.currentThread().join(); // keep server running
    }
}

