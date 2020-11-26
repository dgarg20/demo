package com.dgarg20.demo.application_manager.dependencies;

import com.dgarg20.demo.application_manager.ServiceConfiguration;
import com.dgarg20.demo.entities.BaseEntity;
import com.dgarg20.demo.entities.Category;
import com.dgarg20.demo.entities.Expense;
import com.dgarg20.demo.entities.User;
import com.google.common.collect.ImmutableList;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import org.reflections.Reflections;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import java.util.Set;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */
public class ApplicationHibernateBundle extends HibernateBundle<ServiceConfiguration> {
    //private static Reflections reflections = new Reflections(getEntityPackage());

    public ApplicationHibernateBundle() {
        super(getEntities(), new SessionFactoryFactory());
    }

    @Override
    public DataSourceFactory getDataSourceFactory(ServiceConfiguration configuration) {
        return configuration.getDataSource();
    }

    private static ImmutableList<Class<?>> getEntities() {
        return (ImmutableList)ImmutableList.builder().add(User.class, Expense.class, Category.class).build();
    }



//    public static String getEntityPackage() {
//        return BaseEntity.class.getPackage().getName();
//    }
//
//    /**
//     * List of all Hibernate entity classes, exception junction tables mapped using {@link JoinTable} annotation
//     */
//    public static Set<Class<?>> getEntityClasses() {
//        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
//        if (entityClasses.isEmpty()) {
//            throw new IllegalStateException("Couldn't get any entities through Reflections. Either your codebase does not have any database entities (in that case you add one) or Reflections is configured incorrectly.");
//        }
//        return entityClasses;
//    }
}
