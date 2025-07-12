package com.aijewelry;

import com.aijewelry.controller.DesignController;
import com.aijewelry.util.CorsFilter;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static void main(String[] args) throws Exception {
        final ResourceConfig config = new ResourceConfig()
                .register(DesignController.class)
                .register(CorsFilter.class);

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config, false);

        // Serve /uploads from local disk folder named "uploads"
        HttpHandler staticHandler = new StaticHttpHandler("uploads/");
        server.getServerConfiguration().addHttpHandler(staticHandler, "/uploads");

        server.start();
        System.out.println("✅ Server running at " + BASE_URI);
        System.out.println("📂 Serving images at http://localhost:8080/uploads/<filename>.png");
        System.out.println("🔴 Press Ctrl+C to stop.");
        Thread.currentThread().join(); // keep server running
    }
}

