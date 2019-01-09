package ru.kc4kt4.data.distributor.test.config;

import org.flywaydb.core.Flyway;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"ru.kc4kt4.data.distributor.repository"})
@Profile("test")
@ComponentScan(value = {"ru.kc4kt4.data.distributor.test"})
public class TestConfiguration {

    @Bean("dataSourceTest")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean(initMethod = "migrate")
    @DependsOn("dataSourceTest")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure().dataSource(dataSource).locations("migration").load();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf
                = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("ru.kc4kt4.data.distributor.entity", "ru.kc4kt4.data.distributor.repository");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(getHibernateProperties());
        emf.afterPropertiesSet();
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getHibernateProperties() {
        Properties ps = new Properties();
        ps.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        ps.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        ps.setProperty("hibernate.hbm2ddl.auto", "update");
        ps.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        ps.setProperty("hibernate.connection.charSet", "UTF-8");

        ps.setProperty(AvailableSettings.FORMAT_SQL, "true");
        ps.setProperty(AvailableSettings.SHOW_SQL, "true");
        return ps;
    }
}
