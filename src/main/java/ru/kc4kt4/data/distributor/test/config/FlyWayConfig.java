package ru.kc4kt4.data.distributor.test.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import ru.kc4kt4.data.distributor.test.config.properties.DataSourceProperties;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableConfigurationProperties
@Profile({"!test"})
public class FlyWayConfig {
    protected final DataSourceProperties properties;

    @Autowired
    public FlyWayConfig(DataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean(initMethod = "migrate")
    @DependsOn("dataSource")
    public Flyway flyway() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(org.postgresql.Driver.class.getName())
                .url(properties.getDataBaseUrl())
                .username(properties.getUser())
                .password(properties.getPassword())
                .build();
        return Flyway.configure().dataSource(dataSource).locations("migration").load();
    }
}
