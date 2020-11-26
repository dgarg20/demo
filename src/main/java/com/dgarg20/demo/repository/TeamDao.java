package com.dgarg20.demo.repository;

import com.dgarg20.demo.entities.Team;
import com.dgarg20.demo.entities.User;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
public class TeamDao extends AbstractDAO<Team> {

    @Inject
    public TeamDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Team> findByTeamId(String teamId) {
        Criteria criteria = this.currentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("teamId", teamId));
        List<Team> teams= criteria.setFetchSize(1).list();
        if(teams.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(teams.get(0));
    }

    public void save(Team team){
        persist(team).getId();
    }
}
