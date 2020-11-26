package com.dgarg20.demo.application_manager.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

public class RotationHealthCheck extends HealthCheck {

    private String oorFilePath;

    @Inject
    public void RotationHealthCheck( @Named("oorFile")String oorFilePath){
        this.oorFilePath = oorFilePath;
    }

    @Override
    protected Result check() throws Exception {
        File f = new File(oorFilePath);
        if(f.exists())
            return Result.builder().healthy().build();
        else
            return Result.builder().unhealthy().build();
    }
}
