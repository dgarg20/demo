package com.dgarg20.demo.application_manager.filters;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Slf4j
public class ResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext,
                           ContainerResponseContext responseContext) throws IOException {
        log.info("Server Response: {}", responseContext.getEntityClass());
        MDC.clear();
    }
}
