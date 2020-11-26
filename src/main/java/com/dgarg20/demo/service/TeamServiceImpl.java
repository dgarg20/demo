package com.dgarg20.demo.service;

import com.dgarg20.demo.entities.Alerts;
import com.dgarg20.demo.entities.Team;
import com.dgarg20.demo.entities.User;
import com.dgarg20.demo.repository.AlertsDao;
import com.dgarg20.demo.repository.TeamDao;
import com.dgarg20.demo.repository.UserDao;
import com.dgarg20.demo.requests.AddAlertsRequest;
import com.dgarg20.demo.requests.AddTeamRequest;
import com.google.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao;

    private UserDao userDao;

    private AlertsDao alertsDao;

    @Inject
    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public void addTeam(AddTeamRequest addTeamRequest) {
        Team team = new Team(addTeamRequest.getTeamId(), addTeamRequest.getTeamName());
        teamDao.save(team);
    }

    @Override
    public void addUsersToTeam(String teamId, List<String> usersIds)throws Exception {

        List<User> users = userDao.getUserWithId(new HashSet<String>(usersIds));
        Optional<Team> team  = teamDao.findByTeamId(teamId);
        if(!team.isPresent())
            throw  new Exception("Team Id not Found");
        team.get().setUsers(users);
    }

    @Override
    public void addAlerts(AddAlertsRequest addAlertsRequest) {
        Alerts alerts = Alerts.builder().description(addAlertsRequest.getDescription()).status("Pending").build();
        alertsDao.save(alerts);
    }
}
