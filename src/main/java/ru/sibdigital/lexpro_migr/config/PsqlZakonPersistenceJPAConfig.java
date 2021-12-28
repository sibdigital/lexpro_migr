package ru.sibdigital.lexpro_migr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PsqlZakonPersistenceJPAConfig {
    private String driver = "org.postgresql.Driver";

    @Value("${spring.psql-zakon-datasource.url}")
    private String bdUrl;

    @Value("${spring.psql-zakon-datasource.username}")
    private String bdUsername;

    @Value("${spring.psql-zakon-datasource.password}")
    private String bdPassword;

    @Bean(name = "psqlZakonEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean psqlEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(psqlZakonDataSource());
        em.setPackagesToScan(new String[] { "ru.sibdigital.lexpro_migr.model.zakon" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource psqlZakonDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(bdUrl);
        dataSource.setUsername(bdUsername);
        dataSource.setPassword(bdPassword);
        return dataSource;
    }

    @Bean("psqlZakonTransactionManager")
    public PlatformTransactionManager psqlZakonTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(psqlEntityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean(name = "psqlZakonEntityManager")
    public EntityManager entityManager() {
        return psqlEntityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor psqlZakonExceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return properties;
    }
}
