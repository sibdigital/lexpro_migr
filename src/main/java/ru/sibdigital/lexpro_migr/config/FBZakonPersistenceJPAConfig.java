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
public class FBZakonPersistenceJPAConfig {
    private String driver = "org.postgresql.Driver";

    @Value("${spring.fb-datasource.url}")
    private String bdUrl;

    @Value("${spring.fb-datasource.username}")
    private String bdUsername;

    @Value("${spring.fb-datasource.password}")
    private String bdPassword;

    @Bean(name = "fbZakonEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean fbEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(fbDataSource());
        em.setPackagesToScan(new String[] { "ru.sibdigital.lexpro_migr.model.zakon" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource fbDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(bdUrl);
        dataSource.setUsername(bdUsername);
        dataSource.setPassword(bdPassword);
        return dataSource;
    }

    @Bean("fbTransactionManager")
    public PlatformTransactionManager fbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(fbEntityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean(name = "fbZakonEntityManager")
    public EntityManager entityManager() {
        return fbEntityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor fbExceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.FirebirdDialect");

        return properties;
    }
}
