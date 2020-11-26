package com.dgarg20.demo;


import com.dgarg20.demo.application_manager.dependencies.ApplicationHibernateBundle;
import com.dgarg20.demo.application_manager.dependencies.ApplicationModule;
import com.dgarg20.demo.application_manager.ServiceConfiguration;
import com.dgarg20.demo.application_manager.healthcheck.ApplicationHealthCheck;
import com.dgarg20.demo.exceptions.ResponseExceptionMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

@Slf4j
public class MainApp<T extends Configuration> extends Application<ServiceConfiguration> {

    /**
     * lombok plugin used if don't want it import "import org.slf4j.Logger;
     */
    //private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private ApplicationHibernateBundle applicationHibernateBundle = new ApplicationHibernateBundle();

    public static void main(String[] args)throws Exception {
        MDC.put("trans.id", UUID.randomUUID().toString());
        final MainApp mainApp = new MainApp();
        mainApp.run(args);
    }

    public void initialize(Bootstrap<ServiceConfiguration> bootstrap){
        super.initialize(bootstrap);

//        final HibernateBundle<ServiceConfiguration> hibernateBundle = new
//                ScanningHibernateBundle<ServiceConfiguration>(ApplicationHibernateBundle.getEntityPackage()) {
//                    @Override
//                    public PooledDataSourceFactory getDataSourceFactory(
//                            ServiceConfiguration configuration) {
//                        return configuration.getDataSource();
//                    }
//                };
//
//        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(applicationHibernateBundle);
        GuiceBundle<ServiceConfiguration> guiceBundle = GuiceBundle.<ServiceConfiguration>newBuilder()
                .addModule(new ApplicationModule())
                .setConfigClass(ServiceConfiguration.class)
                .enableAutoConfig(autoScanPackages())
                //enableGuiceEnforcer(false)   to be used with dropwizard juicer.
                .build();
        bootstrap.addBundle(guiceBundle);

        if(enableSwagger(Stage.DEVELOPMENT)) {
            bootstrap.addBundle(new SwaggerBundle<ServiceConfiguration>() {
                @Override
                protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ServiceConfiguration configuration) {
                    return configuration.swaggerBundleConfiguration;
                }
            });
        }
        initializeMain();
    }

    private Module[] getModules() {
        return new Module[] { new ApplicationModule()};
    }

    //Please Dont Edit this class custom initialization can be done in initializeMain Method
    public void run(final ServiceConfiguration serviceConfiguration, final Environment environment) throws Exception {

//        environment.jersey().register(new OpenApiResource()
//                .openApiConfiguration(getSwaggerConfiguration()));

        /**
         *Its Used to tell jackson that all json's received will be in snake case to map them to json class
         */
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        serviceConfiguration.setSessionFactory(applicationHibernateBundle.getSessionFactory());

        /**
         *
         *Registering the application health check
         */
        environment.healthChecks().register("application_check" ,new ApplicationHealthCheck());
        //environment.healthChecks().register("rotation" ,new RotationHealthCheck());

        /**
         *Registering request filters for the application. Basically Request interceptors
         */
        //environment.jersey().register("com.dgarg20.java_base.application_manager.filters");
        //Injector injector = Guice.createInjector(new ApplicationModule());
        //final ExpenseResource expenseResource = injector.getInstance(ExpenseResource.class);
        //environment.jersey().register(new ResponseExceptionMapper(environment.metrics()));
    }

    protected void initializeMain(){

    }

    private String[] autoScanPackages() {
        return new String[] { this.getClass().getPackage().getName() };
    }

    protected boolean enableSwagger(Stage stage){
        return true;
    }
}