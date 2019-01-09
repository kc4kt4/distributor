package ru.kc4kt4.data.distributor.test.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.hql.spi.id.local.LocalTemporaryTableBulkIdStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kc4kt4.data.distributor.test.config.properties.DataSourceProperties;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@Profile({"!test"})
public class DataSourceConfig {
    protected final DataSourceProperties properties;

    @Autowired
    public DataSourceConfig(DataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean("dataSource")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(org.postgresql.Driver.class.getName());
        hikariConfig.setJdbcUrl(properties.getDataBaseUrl());
        hikariConfig.setUsername(properties.getUser());
        hikariConfig.setPassword(properties.getPassword());
        hikariConfig.setMinimumIdle(properties.getHikari().getMinPoolSize());
        hikariConfig.setMaximumPoolSize(properties.getHikari().getMaxPoolSize());
        hikariConfig.setConnectionTestQuery(properties.getHikari().getPreferredTestQuery());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            EntityManagerFactoryBuilder builder) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.HBM2DDL_AUTO, this.properties.getDdlAuto());
        properties.put(AvailableSettings.DIALECT, this.properties.getDialect());
        properties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY,
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        properties.put(AvailableSettings.DEFAULT_SCHEMA, this.properties.getSchema());
        properties.put(AvailableSettings.HQL_BULK_ID_STRATEGY, LocalTemporaryTableBulkIdStrategy.class.getName());

        return builder
                .dataSource(dataSource)
                .packages("ru.kc4kt4.data.distributor.entity", "org.springframework.data.jpa.convert.threeten")
                .persistenceUnit("kc4kt4")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean emFactory) {
        return new JpaTransactionManager(emFactory.getObject());
    }

    @Bean
    public JdbcTemplate amcJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
