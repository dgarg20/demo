package com.dgarg20.demo.exceptions;

import com.codahale.metrics.MetricRegistry;
import com.dgarg20.demo.contants.HeaderConstants;
import com.dgarg20.demo.response.ServiceExceptionResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by Deepanshu Garg on 15/01/20.
 */
@Slf4j
public class ResponseExceptionMapper implements ExceptionMapper<Exception> {
    // private final Meter exceptions;
    public ResponseExceptionMapper(MetricRegistry metrics) {
        log.info("Response Error Mapper Registered");
        //   exceptions = metrics.meter(name(getClass(), "exceptions"));
    }

    @Override
    public Response toResponse(Exception e) {
        //exceptions.mark();
        ServiceExceptionResponse serviceExceptionResponse = null;
        log.error("Exception Class" + e.getClass() + "Exception Message" + e.getMessage());

        if (e instanceof IllegalArgumentException) {
            IllegalArgumentException ex = (IllegalArgumentException) e;
            serviceExceptionResponse = new ServiceExceptionResponse(Response.Status.BAD_REQUEST.getStatusCode(), "wrong_arguments_passed", "The Resource you are trying to access is unavailable");
        } else if (e instanceof NotFoundException) {
            NotFoundException ex = (NotFoundException) e;
            serviceExceptionResponse = new ServiceExceptionResponse(Response.Status.NOT_FOUND.getStatusCode(), "no_resource_found", "The Resource you are trying to access is unavailable");
        } else if (e instanceof JsonMappingException) {
            JsonMappingException ex = (JsonMappingException) e;
            serviceExceptionResponse = new ServiceExceptionResponse(Response.Status.BAD_REQUEST.getStatusCode(), "json_parsing_exception", e.getMessage());
        }else if(e instanceof ServiceException) {
            ServiceException ex = (ServiceException) e;
            serviceExceptionResponse = new ServiceExceptionResponse(Response.Status.BAD_REQUEST.getStatusCode(), "service_error", e.getMessage());
        }
        else {
            serviceExceptionResponse = new ServiceExceptionResponse(Response.Status.OK.getStatusCode(), "internal_server_error", "Some Thing Went Wrong");
        }
        return Response.status(serviceExceptionResponse.getStatus())
                .header(HeaderConstants.traceIdHeaderOutgoing, MDC.get("trans.id"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(serviceExceptionResponse)
                .build();
    }


}