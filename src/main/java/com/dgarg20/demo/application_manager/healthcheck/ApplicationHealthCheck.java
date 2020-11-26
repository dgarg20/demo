package com.dgarg20.demo.application_manager.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class ApplicationHealthCheck extends HealthCheck {
    //private final String template;

    public ApplicationHealthCheck() {
      //  this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = "TEST";
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}