package com.dgarg20.demo.application_manager.task;

import com.dgarg20.demo.entities.Alerts;
import com.dgarg20.demo.repository.AlertsDao;
import com.dgarg20.demo.service.TeamService;
import com.google.inject.Inject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
@Slf4j
public class AlertsPoller implements Runnable{
    private TeamService teamService;
    private AlertsDao alertsDao;

    @Inject
    public AlertsPoller(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Start Transaction
                Optional<Alerts> alert = alertsDao.changeAlertStatus();
                if (alert.isPresent()) {
                    alertsDao.changeAlertStatus(alert.get(), "Processing");
                    if (processAlert(alert.get())) {
                        alertsDao.changeAlertStatus(alert.get(), "Processed");
                    } else {
                        alertsDao.changeAlertStatus(alert.get(), "Failed");
                    }
                //End Transaction
                } else {
                    Thread.sleep(10000);
                }
            } catch (Exception ex) {
                log.error("Failed to send Alert ", ex);
            }
        }
    }

    public boolean processAlert(Alerts alerts) {
        return true;
    }
}
