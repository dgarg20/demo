package com.dgarg20.demo.application_manager.filters;


import com.dgarg20.demo.contants.HeaderConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

@Provider
@PreMatching
@Slf4j
public class RequestFilter{ //implements ContainerRequestFilter {

   // @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String traceId;
        if(!ctx.getHeaders().get(HeaderConstants.traceIdHeaderIncoming).isEmpty())
            traceId = (String) ctx.getHeaders().get(HeaderConstants.traceIdHeaderIncoming).get(0);
        else
            traceId = UUID.randomUUID().toString();
        MDC.put("trans.id", traceId);

        log.info("trace ID {}", traceId);
        log.info("{} {} \nheaders: {}, body: {}", ctx.getMethod(), ctx.getUriInfo().getAbsolutePath().getQuery(), ctx.getHeaders(), ctx.getEntityStream());

        if (ctx.getMethod().equals("DELETE")) {
           // LOG.info("\"Deleting request");
        }
    }
}