package com.dgarg20.demo.application_manager.dependencies;

import com.dgarg20.demo.MainApp;
import com.dgarg20.demo.application_manager.ServiceConfiguration;
import com.dgarg20.demo.service.UserService;
import com.dgarg20.demo.service.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.SessionFactory;
import org.reflections.Reflections;

public class ApplicationModule extends AbstractModule {
/*
    public void configure(Binder binder) {

    }*/

    private static final Reflections reflections = new Reflections(MainApp.class.getPackage().getName());

    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
    }


    @Provides
    DataSourceFactory providesDataSourceFactory(Provider<ServiceConfiguration> configuration) {
        return configuration.get().getDataSource();
    }

    @Provides
    SessionFactory providesSessionFactory(Provider<ServiceConfiguration> configurationProvider) {
        return configurationProvider.get().getSessionFactory();
    }



   /* @Provides
    @Named("oorFile")
    @Singleton
    public String getOORFile(final Provider<ServiceConfiguration> serviceConfigurationProvider){
        return serviceConfigurationProvider.get().getOorFileName();
    }*/
}
