package com.aijewelry.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;


@Path("/designs")
public class DesignController {

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadDesign(Map<String, String> payload) {
        String base64 = payload.get("image");

        if (base64 == null || base64.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No image provided").build();
        }

        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64.replaceFirst("^data:image/[^;]+;base64,", ""));
            String fileName = UUID.randomUUID().toString() + ".png";
            java.nio.file.Path outputPath = java.nio.file.Paths.get("uploads", fileName);


            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, imageBytes);

            return Response.ok("uploads/" + fileName).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to save image").build();
        }
    }
}
