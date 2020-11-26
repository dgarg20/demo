package com.dgarg20.demo.resource;

import com.dgarg20.demo.requests.AddAlertsRequest;
import com.dgarg20.demo.requests.AddTeamRequest;
import com.dgarg20.demo.requests.AddUserRequest;
import com.dgarg20.demo.requests.UserList;
import com.dgarg20.demo.service.TeamService;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Api(tags = "UserAttributeResource")
public class TeamResource {

    private TeamService teamService;

    @Inject
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @GET
    @Path("/{team_id}")
    public Response getTeamDetails(@PathParam("team_id") String teamId) {
        return Response.accepted().build();
    }

    @POST
    public Response addNewTeam(AddTeamRequest addTeamRequest) {
        teamService.addTeam(addTeamRequest);
        return Response.ok().build();
    }

    @POST
    @Path("/{team_id}/users")
    public Response addUsersToTeam(UserList userList, @PathParam("team_id") String teamId) {
        try {
            teamService.addUsersToTeam(teamId, userList.getUserIds());
            return Response.ok().build();
        }
        catch (Exception ex) {
            return Response.ok().build();
        }
    }

    @POST
    @Path("/{team_id}/alerts")
    public Response addUsersToTeam(AddAlertsRequest addAlertsRequest, @PathParam("team_id") String teamId) {
        try {
            teamService.addAlerts(addAlertsRequest);
            return Response.ok().build();
        }
        catch (Exception ex) {
            return Response.ok().build();
        }
    }
}
