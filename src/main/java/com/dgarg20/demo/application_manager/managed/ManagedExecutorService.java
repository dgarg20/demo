package com.dgarg20.demo.application_manager.managed;

import com.dgarg20.demo.application_manager.ServiceConfiguration;
import com.dgarg20.demo.application_manager.task.AlertsPoller;
import com.google.inject.Inject;
import io.dropwizard.lifecycle.Managed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManagedExecutorService implements Managed {
    private ServiceConfiguration serviceConfiguration;
    private ExecutorService ex = Executors.newFixedThreadPool(10);
    private AlertsPoller alertsPoller;

    @Inject
    public void ManagedExecutorService(ServiceConfiguration serviceConfiguration, AlertsPoller alertsPoller){
        this.serviceConfiguration = serviceConfiguration;
        this.alertsPoller = alertsPoller;
    }

    @Override
    public void start() throws Exception {
        ex.submit(alertsPoller);
    }

    @Override
    public void stop() throws Exception {
        ex.shutdown();
    }
}
