package com.uade.tpo.gimnasio.config;

import com.uade.tpo.gimnasio.dto.CourseProjection;
import com.uade.tpo.gimnasio.dto.ReservaProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.getProjectionConfiguration().addProjection(CourseProjection.class);
        config.getProjectionConfiguration().addProjection(ReservaProjection.class);
    }
}
