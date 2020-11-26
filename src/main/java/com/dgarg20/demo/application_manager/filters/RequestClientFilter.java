package com.dgarg20.demo.application_manager.filters;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

@Provider
@Slf4j
public class RequestClientFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext ctx) throws IOException {
        MDC.put("trans.id", UUID.randomUUID().toString());
        log.info("{} hh", ctx.getMethod());


//        requestContext.setProperty("test", "test client request filter");
    }
}