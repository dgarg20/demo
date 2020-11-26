package com.dgarg20.demo.service;

import com.dgarg20.demo.requests.AddAlertsRequest;
import com.dgarg20.demo.requests.AddTeamRequest;

import java.util.List;


/**
 * Created by Deepanshu Garg on 26/11/20.
 */

public interface TeamService {
    void addTeam(AddTeamRequest addTeamRequest);

    void addUsersToTeam(String teamId, List<String> users)throws Exception;

    void addAlerts(AddAlertsRequest addAlertsRequest);
}
