package com.aijewelry.controller;

import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;
import com.aijewelry.service.DesignService;
import com.aijewelry.service.DesignServiceImpl;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

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
    public Response uploadDesign(DesignUploadRequest request) {
        try {
            service.saveDesign(request);
            return Response.ok("Design saved successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDesignsByUser(@PathParam("userId") String userId) {
        try {
            List<Design> designs = service.getUserDesigns(userId);
            return Response.ok(designs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch designs: " + e.getMessage())
                    .build();
        }
    }

}
