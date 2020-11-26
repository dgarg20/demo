package com.dgarg20.demo.application_manager;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.*;
import org.hibernate.SessionFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ServiceConfiguration extends Configuration {
    private String serviceName;

//    @JsonProperty("database")
//    private DataSourceConfig dataSourceConfig;

    @JsonProperty("dataSource")
    @Valid
    @NotNull
    private DataSourceFactory dataSource;

    @Setter
    private SessionFactory sessionFactory;

    /*@Valid
    @NotNull
    private SwaggerBundleConfiguration swaggerBundleConfiguration;*/

    @Valid
    @NotNull
    private String oorFileName;

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

}
