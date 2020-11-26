package com.dgarg20.demo.resource;

import com.dgarg20.demo.requests.AddUserRequest;
import com.dgarg20.demo.service.UserService;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by Deepanshu Garg on 26/11/20.
 */

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Api(tags = "UserAttributeResource")
public class UserResource {
    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @Path("/{user_id}")
    @GET
    @UnitOfWork
    @ApiOperation("Get User By email Id")
    public Response getUserById(@PathParam("user_id") String userId){
        try {
            AddUserRequest addUserRequest = userService.getUserById(userId);
            return Response.ok().entity(addUserRequest).build();
        }catch (Exception ex){
            return Response.ok().build();
        }
    }

    @POST
    @UnitOfWork
    @ApiOperation("Add new User")
    public Response addNewUser(AddUserRequest addUserRequest,
                               @HeaderParam("X-CURRENNT-USER") String currentUser){
        try {
            userService.AddUser(addUserRequest, currentUser);
            return Response.accepted().build();
        }catch (Exception ex){
            return Response.ok().build();
        }
    }
}

