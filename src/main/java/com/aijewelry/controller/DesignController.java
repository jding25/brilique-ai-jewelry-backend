package com.aijewelry.controller;

import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;
import com.aijewelry.service.DesignService;
import com.aijewelry.service.DesignServiceImpl;
import jakarta.ws.rs.Produces;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;


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

    @Context
    private javax.ws.rs.core.UriInfo uriInfo;

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDesignsByUser(@QueryParam("userId") String userId) {
        try {
            System.out.println("Full request URI: " + uriInfo.getRequestUri());
            System.out.println("Received userId in controller: '" + userId + "'");

            if (userId == null || userId.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Collections.singletonMap("error", "Missing or empty userId"))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            List<Design> designs = service.getUserDesigns(userId);
            return Response.ok(designs).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Collections.singletonMap("error", "Failed to fetch designs: " + e.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/market")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarketDesigns() {
        try {
            List<Design> marketDesigns = service.getMarketDesigns();
            return Response.ok(marketDesigns, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Collections.singletonMap("error", "Cannot retrieve market designs")).build();
        }
    }
}
