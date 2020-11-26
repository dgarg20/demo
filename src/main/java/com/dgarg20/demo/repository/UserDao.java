package com.dgarg20.demo.repository;

import com.dgarg20.demo.entities.User;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
public class UserDao extends AbstractDAO<User> {

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<User> getUserById(String id){
        Criteria criteria = this.currentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("emailId", id));
        List<User> users= criteria.list();
        if(users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }


    public List<User> getUserWithId(Set<String> userIds) {
        Criteria criteria = this.currentSession().createCriteria(User.class);
        criteria.add(Restrictions.in("emailId", userIds));
        return criteria.list();
    }


    public long create(User user) {
        return persist(user).getId();
    }

    public List<User> findAll() {
        return list(namedQuery("User.findAll"));
    }
}
