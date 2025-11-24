package com.uade.tpo.gimnasio.config;


import javax.sql.DataSource;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

// agregado para pre carga de datos de prueba
@Component
public class InitializeData {

    private DataSource dataSource;

    public InitializeData(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        populator.addScript(new ClassPathResource("data.sql"));
        populator.setSeparator(";");              // MySQL default
        populator.setSqlScriptEncoding("UTF-8");  // Good practice
        populator.setIgnoreFailedDrops(true);     // Avoid errors on repeated runs

        populator.execute(dataSource);
    }
}