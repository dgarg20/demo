package com.dgarg20.demo.resource;


import com.codahale.metrics.annotation.Timed;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/expense")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@NoArgsConstructor
public class ExpenseResource {
    @GET
    @Timed

    public Response getExpenseByID(@QueryParam("id") int id){
        log.info("Hello How are you");

        return Response.ok().build();
    }
}
