package com.dgarg20.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Deepanshu Garg on 16/01/20.
 */
@AllArgsConstructor
@Data
public class ServiceExceptionResponse {
    private int status;
    private String code;
    private String message;
}
