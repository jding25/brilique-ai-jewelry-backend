package com.aijewelry.controller;

import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;
import com.aijewelry.service.DesignService;
import com.aijewelry.service.DesignServiceImpl;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


@Path("/designs")
public class DesignController {

    private final DesignService service = new DesignServiceImpl();

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadDesign(DesignUploadRequest request) {
        try {
            String imageUrl = service.saveDesign(request);
            System.out.println("imageUrl from DesignController, "+imageUrl);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("imageUrl", imageUrl);
            return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Collections.singletonMap("error", e.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDesignsByUser(@QueryParam("userId") String userId) {
        try {
            System.out.println("Received userId in controller: " + userId);
            List<Design> designs = service.getUserDesigns(userId);
            return Response.ok(designs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch designs: " + e.getMessage())
                    .build();
        }
    }

}
