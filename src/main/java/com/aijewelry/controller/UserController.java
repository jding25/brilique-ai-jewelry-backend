package com.aijewelry.controller;

import com.aijewelry.model.User;
import com.aijewelry.model.UserUploadRequest;
import com.aijewelry.service.UserService;
import com.aijewelry.service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserController {

    private final UserService service = new UserServiceImpl();

    @GET @Path("/ping") @Produces(MediaType.TEXT_PLAIN)
    public String ping() { return "pong"; }

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadUser(UserUploadRequest request) {
        try {
            service.saveUser(request);
            return Response.status(Response.Status.CREATED).entity("{\"message\":\"User saved\"}")
                    .type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Failed to save user\"}").build();
        }
    }

    @GET
    @Path("/retrieve")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@QueryParam("email") String email) {
        try {
            var user = service.getUser(email);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            return Response.ok(user).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve user").build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllUsers() {
        try{
            var userList = service.retrieveAllUsers();
            return Response.ok(userList, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Failed to retrieve all users\"}")
                    .type(MediaType.APPLICATION_JSON).build();
        }

    }

}



